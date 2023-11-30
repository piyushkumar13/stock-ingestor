/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.infra.repository.impl;

import static org.apache.commons.lang3.BooleanUtils.negate;

import com.vm.stock.publisher.infra.repository.RepositoryManager;
import com.vm.stock.publisher.infra.repository.dto.entity.StockMetadataEntity;
import com.vm.stock.publisher.infra.repository.jpa.StockMetadataRepository;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
@Data
@Slf4j
@Repository
public class RepositoryManagerImpl implements RepositoryManager {

    private final StockMetadataRepository stockMetadataRepository;

    @Override
    public boolean shouldIngest() {

        log.info("Checking whether data store contains any entry or not");
        return negate(stockMetadataRepository.existsById(1L));
    }

    @Override
    public void ingestData(final List<StockMetadataEntity> stockMetadataEntities) {

        log.info("Ingesting stock metadata.");

        stockMetadataRepository.saveAll(stockMetadataEntities);

        log.info("Ingestion of stock metadata is completed successfully");
    }
}
