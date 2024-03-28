package com.ilumusecase.random_generator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ilumusecase.random_generator.csv_formatters.CsvWriter;
import com.ilumusecase.random_generator.random_generator.ActionRecord;
import com.ilumusecase.random_generator.random_generator.RandomRecordGeneator;


@SpringBootApplication
public class RandomGeneratorApplication {


	public static void main(String[] args) {
		SpringApplication.run(RandomGeneratorApplication.class, args);

		RandomRecordGeneator randomRecordGeneator = new RandomRecordGeneator();

		List<ActionRecord> actionRecords = new ArrayList<>();
		for(int i = 0; i < 100; i++){
			actionRecords.add(randomRecordGeneator.generateRandomActionRecord());
		}

		CsvWriter csvWriter = new CsvWriter();
		try{
			Path path = Paths.get(System.getProperty("user.dir") + "/csv/writtenBean.csv");
			System.out.println(path.toString());
			csvWriter.writeCsvFromBean(path, actionRecords);
		}catch(Exception e){
			System.out.println(e);
		}

	}

}
