/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.config;

import static com.google.common.base.Preconditions.checkArgument;
import static com.vm.stock.publisher.config.StocksConfiguration.StockDetails;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import com.vm.stock.publisher.config.mapper.StockMetadataMapper;
import com.vm.stock.publisher.service.IngestorService;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class StockIngestor {

    private final StocksConfiguration stocksConfiguration;
    private final StockMetadataMapper stockMetadataMapper;
    private final IngestorService ingestorService;

    @PostConstruct
    public void ingestStockDetails() {

        if (!ingestorService.shouldIngest()) {
            log.info("Skipping stock initial ingestion since already populated.");
            return;
        }

        log.info("Stock initial ingestion started.");

        List<StockDetails> stockDetailsList = stocksConfiguration.getStockDetailsList();

        checkArgument(isNotEmpty(stockDetailsList), "Provided list in config is empty.");

        ingestorService.ingestData(stockMetadataMapper.toStocksList(stockDetailsList));

        log.info("Stock initial ingestion is completed successfully.");
    }
}
