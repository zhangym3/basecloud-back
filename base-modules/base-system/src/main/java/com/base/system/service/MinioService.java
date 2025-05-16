package com.base.system.service;

import io.minio.ServerSideEncryption;
import io.minio.errors.MinioException;

import java.io.InputStream;
import java.util.Map;

public interface MinioService {
    public InputStream getObject(String bucketName, String objectName) throws MinioException;
    public void putObject(String bucketName, String objectName, InputStream stream, String contentType) throws  MinioException;
    public void removeObject(String bucketName, String objectName) throws MinioException;
    public void putObject(String bucketName, String objectName, InputStream stream, Long size,
                          Map<String, String> headerMap, ServerSideEncryption sse, String contentType) throws MinioException;
    public String getObjectUrl(String bucketName, String objectName);

    public boolean isFileExists(String bucketName, String objectName);

}
