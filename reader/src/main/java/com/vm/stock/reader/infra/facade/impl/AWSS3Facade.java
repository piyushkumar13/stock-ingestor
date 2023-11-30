/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.infra.facade.impl;

import static java.util.Objects.isNull;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vm.stock.reader.config.S3Configuration;
import com.vm.stock.reader.infra.facade.FileStorageFacade;
import com.vm.stock.reader.infra.facade.dto.Stock;
import java.io.IOException;
import java.util.Optional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
@Data
@Slf4j
@Component
public class AWSS3Facade implements FileStorageFacade {

    private final AmazonS3 s3Client;
    private final S3Configuration s3Config;
    private final ObjectMapper objectMapper;

    @Override
    public boolean doesFolderExists(String objectPath) {
        log.info("Check folder exists for objectPath, objectPath={}", objectPath);

        ListObjectsV2Result result = s3Client.listObjectsV2(s3Config.getBucketName(), objectPath);

        return result.getKeyCount() > 0;
    }

    @Override
    public boolean doesObjectExists(final String objectPath) {

        log.info("Check S3Object exists for objectPath, objectPath={}", objectPath);

        return s3Client.doesObjectExist(s3Config.getBucketName(), objectPath);
    }

    @Override
    public Optional<Stock> getObject(final String objectPath) {

        log.info("Fetching S3Object for objectPath, objectPath={}", objectPath);

        S3Object s3Object = null;

        try {
            s3Object = s3Client.getObject(s3Config.getBucketName(), objectPath);

        } catch (Exception e) {
            log.error("S3Object not found, objectPath={}", objectPath);
        }

        return getStockObject(s3Object);

    }

    private Optional<Stock> getStockObject(final S3Object s3Object) {

        if (isNull(s3Object) || isNull(s3Object.getObjectContent())) {

            log.info("S3Object or ObjectContent is not found, S3Object={}", s3Object);
            return Optional.empty();
        }

        log.info("Constructing stock object from S3Object, S3Object={}", s3Object);

        try {

            return Optional.of(objectMapper.readValue(s3Object.getObjectContent(), Stock.class));

        } catch (IOException e) {

            log.error("Error while deserializing S3Object");
            throw new IllegalArgumentException("Error while deserializing S3Object", e);
        }
    }
}
