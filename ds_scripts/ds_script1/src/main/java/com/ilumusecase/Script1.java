package com.ilumusecase;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.ml.feature.OneHotEncoder;

import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.api.java.UDF4;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataTypes;

import com.ilumusecase.game.Card;
import com.ilumusecase.game.Round;
import com.ilumusecase.game.pokergametypes.holdem6.Holdem6CalculateRelativePower; 


public class Script1 
{
    public static void main( String[] args )
    {
        // System.setProperty("hadoop.home.dir", "c:/hadoop");
		Logger.getLogger("org.apache").setLevel(Level.WARN);

		SparkSession spark = SparkSession.builder()
				.appName("Poker ML: relative power based")
				.config("spark.sql.warehouse.dir","file:///c:/tmp/")
				.master("local[*]").getOrCreate();
		
		Dataset<Row> csvData = spark.read()
				.option("header", true)
				.option("delimiter", "\'")
				.option("inferSchema", true)
				.csv("src/main/resources/csv/holdem6max_1.csv");


		UDF1<String, Integer> getCardsNumber =  new UDF1<String, Integer>() {
            public Integer call(String str) throws Exception {
				if(str == null) return 0;
                return str.split(",").length;
			}
		};
		spark.udf().register("getCardsNumber", getCardsNumber, DataTypes.IntegerType);

		UDF4<Integer, Integer, Integer, String, Double> getBankChances = new UDF4<Integer,Integer,Integer,String,Double>() {

			@Override
			public Double call(Integer bank, Integer actionSize, Integer toCall, String actionType) throws Exception {
				switch (actionType) {
					case "BET":
					case "RAISE":
						return Math.round(bank * 100.0 / actionSize) * 1.0 / 100;
					case "CALL":
					case "FOLD":
						if(toCall.equals(0)) return 10.0;
						return Math.round(bank * 100.0 / toCall) * 1.0 / 100;
					case "CHECK":
						return 10.0;
					default:
						throw new RuntimeException();
				}
			}
			
		};
		spark.udf().register("getBankChances", getBankChances, DataTypes.DoubleType);


		UDF2<String, String, Double> getRelativePower = new UDF2<>(){

			@Override
			public Double call(String cardsStr, String handStr) throws Exception {

				if(cardsStr == null) cardsStr = "";

				Holdem6CalculateRelativePower calculateRelativePower = new Holdem6CalculateRelativePower();

				Round round = new Round();
				
				List<Card> hand = new ArrayList<>();
				for(String cardStr : handStr.split(",")){
					Integer rank = Integer.parseInt(cardStr.split("_")[0]);
					Integer suit = Integer.parseInt(cardStr.split("_")[1]);
					hand.add(new Card(rank, suit));
				}

				List<Card> tableCards = new ArrayList<>();
				for(String cardStr : cardsStr.split(",")){
					Integer rank = Integer.parseInt(cardStr.split("_")[0]);
					Integer suit = Integer.parseInt(cardStr.split("_")[1]);
					tableCards.add(new Card(rank, suit));
				}
				round.setTableCards(tableCards);


				if(tableCards.size() == 5) return Math.round(calculateRelativePower.calculateRelativePower(round, hand) * 1000) * 1.0 / 1000;
				else return Math.round(calculateRelativePower.calculateRelativePotentialPower(round, hand) * 1000) * 1.0 / 1000;

			}

		};
		spark.udf().register("getRelativePower", getRelativePower, DataTypes.DoubleType);

		
		
		csvData.createOrReplaceTempView("csvData");
		

		String selectStatement = "SELECT actionType, getCardsNumber(tableCards) as cardsNumber, " + 
			" getBankChances(bank, actionSize, MAXPREVIOUSBETSIZE - PLAYERPREVIOUSBETSIZE, actionType) as bankChances, " + 
			" getRelativePower(tableCards, hand) as relativePower " + 
			" FROM csvData WHERE getCardsNumber(tableCards) > 2";

		Dataset<Row> cleanData = spark.sql(selectStatement);
		

				
		StringIndexer actionTypeIndex = new StringIndexer();
		actionTypeIndex.setInputCol("actionType");
		actionTypeIndex.setOutputCol("actionTypeIndex");
		
		StringIndexer cardsNumberIndex = new StringIndexer();
		cardsNumberIndex.setInputCol("cardsNumber");
		cardsNumberIndex.setOutputCol("cardsNumberIndex");

		cleanData = actionTypeIndex.fit(cleanData).transform(cleanData);
        cleanData = cardsNumberIndex.fit(cleanData).transform(cleanData);


		OneHotEncoder encoder = new OneHotEncoder();
		encoder.setInputCols(new String[] {"actionTypeIndex","cardsNumberIndex"});
		encoder.setOutputCols(new String[] {"actionTypeVector","cardsNumberVector"});
		cleanData = encoder.fit(cleanData).transform(cleanData);


				
		VectorAssembler vectorAssembler = new VectorAssembler()
				.setInputCols(new String[] {"bankChances","actionTypeVector","cardsNumberVector"})
				.setOutputCol("features");
		
		Dataset<Row> modelInputData = vectorAssembler.transform(cleanData)
				.select("relativePower","features")
				.withColumnRenamed("relativePower", "label");
		
		// modelInputData.show();
		
		Dataset<Row>[] dataSplits = modelInputData.randomSplit(new double[] {0.8, 0.2});
		Dataset<Row> trainingAndTestData = dataSplits[0];
		Dataset<Row> holdOutData = dataSplits[1];
		
		LinearRegression linearRegression = new LinearRegression(); 

		LinearRegressionModel model = linearRegression.fit(trainingAndTestData);
			
		System.out.println("The training data r2 value is " + model.summary().r2() + " and the RMSE is " + model.summary().rootMeanSquaredError());
		
		//model.transform(testData).show();
		
		System.out.println("The test data r2 value is " + model.evaluate(holdOutData).r2() + " and the RMSE is " + model.evaluate(holdOutData).rootMeanSquaredError());
		
		System.out.println("coefficients : " + model.coefficients() + " intercept : " + model.intercept());
		System.out.println("reg param : " + model.getRegParam() + " elastic net param : " + model.getElasticNetParam());
		



        spark.close();
    }
}
