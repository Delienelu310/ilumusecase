package com.ilumusecase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Row;


public class Script1 
{
    public static void main( String[] args )
    {
        // System.setProperty("hadoop.home.dir", "c:/hadoop");
		Logger.getLogger("org.apache").setLevel(Level.WARN);

		SparkSession spark = SparkSession.builder()
				.appName("House Price Analysis")
				.config("spark.sql.warehouse.dir","file:///c:/tmp/")
				.master("local[*]").getOrCreate();
		
		// Dataset<Row> csvData = spark.read()
		// 		.option("header", true)
		// 		.option("inferSchema", true)
		// 		.csv("src/main/resources/csv/holdem6max_1.csv");

        // csvData.show(20);

        spark.close();
    }
}
