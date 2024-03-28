package com.ilumusecase.random_generator.csv_formatters;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ilumusecase.random_generator.random_generator.ActionRecord;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Component
public class CsvWriter {
    public String writeCsvFromBean(String targetPath, String category, List<ActionRecord> actionRecordList) {

        Path path = null;
		try{
			path = Paths.get(System.getProperty("user.dir") + targetPath + "/" + category + "_" +
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'hh-mm-ss")) + ".csv"
            );
		}catch(Exception e){
			throw new RuntimeException(e);
		}


        
        try (Writer writer  = new FileWriter(path.toString())) {

            StatefulBeanToCsv<ActionRecord> sbc = new StatefulBeanToCsvBuilder<ActionRecord>(writer)
                .withQuotechar('\'')
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();

            sbc.write(actionRecordList);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return path.toString();
    }
}
