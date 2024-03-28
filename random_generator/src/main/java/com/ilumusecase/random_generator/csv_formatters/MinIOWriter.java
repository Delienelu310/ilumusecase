package com.ilumusecase.random_generator.csv_formatters;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ilumusecase.random_generator.RandomGeneratorApplication;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

@Component
public class MinIOWriter {

    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.username}")
    private String username;
    @Value("${minio.password}")
    private String password;

    private MinioClient minioClient;

    private Logger logger = LoggerFactory.getLogger(RandomGeneratorApplication.class);

    private void initiateMinioClient(){
        logger.info("Trying to connect");
        minioClient = MinioClient.builder()
            .endpoint(endpoint)
            .credentials(username, password)
            .build();

        logger.info("Connected to minio successfully");
    }

    public void sendCsv(String bucket, String srcFilePath, String targetFilePath, String targetName){

        if(minioClient == null) initiateMinioClient();

        try{
            FileInputStream fileInputStream = new FileInputStream(srcFilePath);

            logger.info("Trying to put object");


            minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(targetFilePath + "/" + targetName + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'hh-mm-ss"))+ ".csv"
                )
                .stream(fileInputStream, fileInputStream.available(), -1)
                .build());

            logger.info("Put object successfully");
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
       
}
