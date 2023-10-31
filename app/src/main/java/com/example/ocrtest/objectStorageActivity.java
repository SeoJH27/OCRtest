package com.example.ocrtest;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.ByteArrayInputStream;
import java.io.File;
/*
public class objectStorageActivity {
    final String endPoint = "http://sch.ocu.ksj.s3-website.kr.object.ncloudstorage.com";
    final String regionName = "fin-standard";
    final String accessKey = "T9bnSXulx2JEfCRxcYLK";
    final String secretKey = "NyRkN6c1Xn0AcCisXMMT3iPWNbaekJn3UHWlAO2l";

    // S3 client
    final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
            .build();

    String bucketName = "sample-bucket";

    // create folder
    String folderName = "sample-folder/";

    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(0L);
    objectMetadata.setContentType("application/x-directory");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);

    try {
        s3.putObject(putObjectRequest);
        System.out.format("Folder %s has been created.\n", folderName);
    } catch (AmazonS3Exception e) {
            e.printStackTrace();
    } catch (SdkClientException e) {
            e.printStackTrace();
    }

        // upload local file
        String objectName = "sample-object";
        String filePath = "/tmp/sample.txt";

    try {
            s3.putObject(bucketName, objectName, new File(filePath));
            System.out.format("Object %s has been created.\n", objectName);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        }
}
*/