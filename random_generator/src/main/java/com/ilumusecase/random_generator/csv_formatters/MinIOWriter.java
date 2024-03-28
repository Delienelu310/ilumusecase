package com.ilumusecase.random_generator.csv_formatters;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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


    private MinioClient minioClient = MinioClient.builder()
        .endpoint(endpoint)
        .credentials(username, password)
        .build();

    public void sendCsv(String bucket, String srcFilePath, String targetFilePath) throws Exception{

        FileInputStream fileInputStream = new FileInputStream(srcFilePath);

        minioClient.putObject(PutObjectArgs.builder()
            .bucket(bucket)
            .object(targetFilePath)
            .stream(fileInputStream, fileInputStream.available(), -1)
            .build());
    }
}
