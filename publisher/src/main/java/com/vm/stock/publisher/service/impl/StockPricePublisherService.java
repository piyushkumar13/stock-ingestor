/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.service.impl;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vm.stock.publisher.config.StocksConfiguration;
import com.vm.stock.publisher.domain.entity.Stock;
import com.vm.stock.publisher.infra.facade.FileStorageFacade;
import com.vm.stock.publisher.service.PublisherService;
import com.vm.stock.publisher.service.mapper.StockMapper;
import com.vm.stock.publisher.util.Utility;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
@Data
@Slf4j
@Service
public class StockPricePublisherService implements PublisherService {

    private static final String OBJECT_PATH_FORMAT = "%s/%s";
    private static final String SLASH = "/";

    private final StocksConfiguration stocksConfig;
    private final FileStorageFacade fileStorageFacade;
    private final ObjectMapper objectMapper;
    private final StockMapper stockMapper;
    private final Utility utility;

    @Override
    public void publishPrice() {

        stocksConfig.getStockDetailsList()
                .forEach(this::processStockDetails);
    }

    private void processStockDetails(final StocksConfiguration.StockDetails stockDetails) {

        String scriptName = stockDetails.getScriptName();

        OffsetDateTime todaysDateTime = Instant.now().atOffset(ZoneOffset.UTC);
        OffsetDateTime yesterdaysDateTime = todaysDateTime.minus(1, ChronoUnit.DAYS);

        String objectPathForTodaysDate = format(OBJECT_PATH_FORMAT, scriptName, utility.getFileName(todaysDateTime));
        String objectPathForYesterdaysDate = format(OBJECT_PATH_FORMAT, scriptName, utility.getFileName(yesterdaysDateTime));

        checkAndInjestStockDetails(stockDetails, objectPathForTodaysDate, objectPathForYesterdaysDate, todaysDateTime);
    }

    private void checkAndInjestStockDetails(final StocksConfiguration.StockDetails stockDetails,
                                            final String objectPathForTodaysDate,
                                            final String objectPathForYesterdaysDate,
                                            final OffsetDateTime currentDateTime) {

        log.info("Checking stock details in S3, objectPathForTodaysDate={}, objectPathForYesterdaysDate={}, currentDateTime={}, stockDetails={}",
                objectPathForTodaysDate, objectPathForYesterdaysDate, currentDateTime, stockDetails);

        boolean doesFolderExistsForScriptName = fileStorageFacade.doesFolderExists(stockDetails.getScriptName() + SLASH);

        if (!doesFolderExistsForScriptName) {
            log.info("scriptName folder does not exists in s3, creating one, scriptName={}", stockDetails.getScriptName());

            fileStorageFacade.putObject(objectPathForTodaysDate, utility.getObjectContent(stockMapper.toStock(stockDetails)));
        } else {

            boolean doesObjectExistsForTodaysDate = fileStorageFacade.doesObjectExists(objectPathForTodaysDate);

            if (doesObjectExistsForTodaysDate) {

                log.info("S3 object exists for objectPathForTodaysDate, objectPathForTodaysDate={}", objectPathForTodaysDate);
                putStockDetails(currentDateTime, objectPathForTodaysDate, EMPTY);

            } else {
                /* Means day has passed and move to next date. */
                log.info("S3 object does not exists for objectPathForTodaysDate, fetching previous day S3 object.");
                putStockDetails(currentDateTime, objectPathForTodaysDate, objectPathForYesterdaysDate);
            }
        }
    }

    private void putStockDetails(final OffsetDateTime currentDateTime, final String todaysDateObjectPath, final String yesterdaysDateObjectPath) {

        com.vm.stock.publisher.infra.facade.dto.Stock facadeStock = isNotBlank(yesterdaysDateObjectPath)
                ? fileStorageFacade.getObject(yesterdaysDateObjectPath).orElse(null)
                : fileStorageFacade.getObject(todaysDateObjectPath).orElse(null);

        Stock stock = stockMapper.toStock(facadeStock);

        if (isNull(stock)) {
            return;
        }

        int newPrice = utility.getNewPrice(stock.getLatestPrice());

        stock.getStockTimeLines().add(Stock.StockTimeLine.builder().price(newPrice).time(currentDateTime).build());
        stock.setLatestPrice(newPrice);
        stock.setUpdateAt(currentDateTime);

        fileStorageFacade.putObject(todaysDateObjectPath, utility.getObjectContent(stock));
    }
}
