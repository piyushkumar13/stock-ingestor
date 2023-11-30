/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.infra.repository;

import com.vm.stock.publisher.infra.repository.dto.entity.StockMetadataEntity;
import java.util.List;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
public interface RepositoryManager {

    boolean shouldIngest();

    void ingestData(List<StockMetadataEntity> stockMetadataEntities);

}
