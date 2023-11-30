/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.listener;

import static com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import static io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy.ON_SUCCESS;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.amazonaws.services.s3.event.S3EventNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vm.stock.reader.service.EventProcessor;
import java.util.Optional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
@Data
@Slf4j
@Component
public class StockPriceSQSListener {

    private final static String EVENT_NAME = "ObjectCreated:Put";
    private final EventProcessor eventProcessor;
    private final ObjectMapper objectMapper;

    @SqsListener(value = "${aws.sqs.stockPriceQueue}", deletionPolicy = ON_SUCCESS)
    public void receiveMsg(final S3EventNotification s3EventNotification) throws JsonProcessingException {

        if (!isValidEvent(s3EventNotification)){
            return;
        }

        S3EventNotification.S3ObjectEntity s3ObjectEntity = s3EventNotification.getRecords().get(0).getS3().getObject();

        log.info("Processing s3EventNotification, s3ObjectEntity={}", objectMapper.writeValueAsString(s3ObjectEntity));

        eventProcessor.processEvent(s3ObjectEntity.getKey());

        log.info("Processing s3EventNotification is successful.");
    }

    private boolean isValidEvent(final S3EventNotification s3EventNotification){

        log.info("Received event, validating event");

        S3EventNotificationRecord s3EventRecord = Optional.ofNullable(s3EventNotification)
                .map(S3EventNotification::getRecords)
                .map(s3EventRecords -> s3EventRecords.get(0))
                .orElse(null);


        if (isNull(s3EventRecord) || !EVENT_NAME.equals(s3EventRecord.getEventName())) {

            log.info("Event received is either null or eventName is not matching, eventName={}",
                    (nonNull(s3EventRecord) ? s3EventRecord.getEventName() : null));

            return false;
        }

        if (isNull(s3EventRecord.getS3()) || isNull(s3EventRecord.getS3().getObject())) {
            log.info("Event received is not having s3Entity or s3ObjectEntity");
            return false;
        }

        return true;
    }
}
