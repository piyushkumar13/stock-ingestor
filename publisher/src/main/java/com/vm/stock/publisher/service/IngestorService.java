/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.service;

import com.vm.stock.publisher.domain.value.StockMetadataList;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */

public interface IngestorService {

    boolean shouldIngest();

    void ingestData(StockMetadataList stockMetadataList);
}
