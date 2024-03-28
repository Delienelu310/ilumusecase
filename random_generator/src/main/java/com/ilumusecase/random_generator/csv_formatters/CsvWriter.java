package com.ilumusecase.random_generator.csv_formatters;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ilumusecase.random_generator.random_generator.ActionRecord;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Component
public class CsvWriter {
    public void writeCsvFromBean(Path path, List<ActionRecord> actionRecordList) throws Exception {

        
        try (Writer writer  = new FileWriter(path.toString())) {

            StatefulBeanToCsv<ActionRecord> sbc = new StatefulBeanToCsvBuilder<ActionRecord>(writer)
                .withQuotechar('\'')
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();

            sbc.write(actionRecordList);
        }
    }
}
