package com.rm.awsimageupload.bucket;

public enum BucketName {

    PROFILE_IMAGE("aws-image-upload-spring-test-rm");

    private final String bucketName;

    BucketName(String buckeName) {
        this.bucketName = buckeName;
    }

    public String getBucketName() {
        return bucketName;
    }
    
}