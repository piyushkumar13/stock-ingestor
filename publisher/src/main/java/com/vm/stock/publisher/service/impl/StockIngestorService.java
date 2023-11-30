/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.service.impl;

import com.vm.stock.publisher.domain.value.StockMetadataList;
import com.vm.stock.publisher.infra.repository.RepositoryManager;
import com.vm.stock.publisher.infra.repository.dto.entity.StockMetadataEntity;
import com.vm.stock.publisher.service.IngestorService;
import com.vm.stock.publisher.service.mapper.StockMetadataEntityMapper;
import java.util.List;
import java.util.stream.Collectors;
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
public class StockIngestorService implements IngestorService {

    private final RepositoryManager repositoryManager;
    private final StockMetadataEntityMapper stockMetadataEntityMapper;

    @Override
    public boolean shouldIngest() {

        log.info("Checking should initial ingestion to be performed or not.");
        return repositoryManager.shouldIngest();
    }

    @Override
    public void ingestData(final StockMetadataList stockMetadataList) {

        List<StockMetadataEntity> stockMetadataEntityList = stockMetadataList.getStockMetadata()
                .stream()
                .map(stockMetadataEntityMapper::toStockEntity)
                .collect(Collectors.toList());

        repositoryManager.ingestData(stockMetadataEntityList);
    }
}
