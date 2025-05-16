package com.base.system.service.impl;


import com.base.system.service.MinioService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MinioServiceImpl implements MinioService {

    @Autowired
    MinioClient minioClient;

    @Override
    public InputStream getObject(String bucketName, String objectName) throws MinioException{
        // TODO Auto-generated method stub

        try {
            if (isFileExists(bucketName, objectName)) {

                return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());

            }else{

                return null;

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new MinioException(e.getMessage());

        }
    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream stream, String contentType) throws  MinioException{
        // TODO Auto-generated method stub
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            this.minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, Long.valueOf(stream.available()), PutObjectArgs.MAX_PART_SIZE).contentType(contentType).build());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new MinioException(e.getMessage());

        }

    }

    @Override
    public void removeObject(String bucketName, String objectName) throws MinioException {
        // TODO Auto-generated method stub
        try {
            if (isFileExists(bucketName, objectName)) {

                minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new MinioException(e.getMessage());

        }
    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream stream, Long size,
                          Map<String, String> headerMap, ServerSideEncryption sse, String contentType)
            throws MinioException {
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(stream, size, PutObjectArgs.MAX_PART_SIZE)
                    .extraHeaders(headerMap)
                    .contentType(contentType)
                    .build());
        } catch (Exception e) {
            throw new MinioException();
        }

    }

    @Override
    public String getObjectUrl(String bucketName, String objectName){
        // TODO Auto-generated method stub
        String url = null;
        try {

            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName).object(objectName).expiry(120, TimeUnit.MINUTES).build());

        } catch (InvalidKeyException | ErrorResponseException | IllegalArgumentException | InsufficientDataException
                 | InternalException | InvalidResponseException
                 | NoSuchAlgorithmException | ServerException | XmlParserException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return url;

    }

    public boolean isFileExists(String bucketName, String objectName) {

        boolean res = true;
        try {

            minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());

        }catch (Exception e) {
            // TODO: handle exception

            res = false;
        }
        return res;

    }
}