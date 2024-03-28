package com.ilumusecase.random_generator.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ilumusecase.random_generator.random_generator.ActionRecord;
import com.ilumusecase.random_generator.csv_formatters.CsvWriter;
import com.ilumusecase.random_generator.csv_formatters.MinIOWriter;
import com.ilumusecase.random_generator.random_generator.RandomRecordGeneator;


@RestController
public class GenerationController {

    @Autowired
    private RandomRecordGeneator randomRecordGeneator;

    @Autowired
    private CsvWriter csvWriter;

    @Autowired
    private MinIOWriter minIOWriter;
    

    @GetMapping("/generate/{count}")
    public String generate(@PathVariable("count") Integer count){

        List<ActionRecord> actionRecords = new ArrayList<>();

        for(int i = 0; i < count; i++){
            actionRecords.add(randomRecordGeneator.generateRandomActionRecord());
        }

        String srcPath = csvWriter.writeCsvFromBean("/csv", "holdem6max", actionRecords);

        minIOWriter.sendCsv("raw-data", srcPath, "", "holdem6max");

        return "success";
    }
}
