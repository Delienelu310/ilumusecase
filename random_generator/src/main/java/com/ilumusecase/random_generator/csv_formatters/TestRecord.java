package com.ilumusecase.random_generator.csv_formatters;

import java.util.ArrayList;
import java.util.List;

import com.ilumusecase.game.Card;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;

import lombok.Data;

@Data
public class TestRecord {
    @CsvBindByName
    String value;
    @CsvBindByName
    String title;
    @CsvBindByName
    Integer valueNumeric;
    @CsvBindAndSplitByName(elementType = List.class, writeDelimiter = ",")
    List<String> values = new ArrayList<>();
    {
        values.add("agfsdgf");
        values.add("agfsdgf");
        values.add("agfsdgf");
    }
    @CsvRecurse
    Card card = new Card(8, 3);

    public TestRecord(String value, String title, Integer valueNumeric) {
        this.value = value;
        this.title = title;
        this.valueNumeric = valueNumeric;
    }

}
