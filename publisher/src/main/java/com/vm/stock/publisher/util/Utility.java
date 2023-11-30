/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vm.stock.publisher.domain.entity.Stock;
import java.time.OffsetDateTime;
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
public class Utility {

    private final ObjectMapper objectMapper;

    public String getFileName(final OffsetDateTime todaysDate) {

        String year = String.valueOf(todaysDate.getYear());
        String monthValue = String.valueOf(todaysDate.getMonthValue());
        String dayOfMonth = String.valueOf(todaysDate.getDayOfMonth());

        return String.join("-", year, monthValue, dayOfMonth) + ".json";
    }

    public String getObjectContent(final Stock stock) {

        try {

            return objectMapper.writeValueAsString(stock);

        } catch (JsonProcessingException e) {
            log.error("Exception while json serializing stock details object");
            throw new RuntimeException("Exception while json serializing stock details object", e);
        }
    }

    public int getNewPrice(int currentPrice) {

        int randomNum = (int) Math.round(Math.random());

        if (randomNum == 0) {
            currentPrice = (int) Math.round(currentPrice * 0.99);

        } else if (randomNum == 1) {
            currentPrice = (int)Math.round(currentPrice * 1.01);

        }
        return currentPrice;
    }
}