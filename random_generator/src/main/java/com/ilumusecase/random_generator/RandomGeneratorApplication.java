package com.ilumusecase.random_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ilumusecase.random_generator.random_generator.RandomRecordGeneator;

@SpringBootApplication
public class RandomGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomGeneratorApplication.class, args);

		RandomRecordGeneator randomRecordGeneator = new RandomRecordGeneator();

		System.out.println(randomRecordGeneator.generateRandomActionRecord().toString());
	}

}
