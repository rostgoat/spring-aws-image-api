package com.rm.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    public AmazonS3 S3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
            "AKIAIQ4FOMM3ACOFFCIQ", 
            "knrLgXHaMjKSQjlEvUJiCHEqOrBzneX7YxLMHuJa"
        );

        return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }
}