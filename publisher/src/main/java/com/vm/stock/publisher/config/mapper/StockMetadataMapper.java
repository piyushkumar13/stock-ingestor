/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.config.mapper;

import com.vm.stock.publisher.config.StocksConfiguration.StockDetails;
import com.vm.stock.publisher.domain.entity.StockMetadata;
import com.vm.stock.publisher.domain.value.StockMetadataList;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
@Mapper(componentModel = "spring")
public interface StockMetadataMapper {

    default StockMetadataList toStocksList(final List<StockDetails> stockDetailsList) {
        List<StockMetadata> stockMetadata = stockDetailsList.stream().map(this::toStock).collect(Collectors.toList());
        return StockMetadataList.builder().stockMetadata(stockMetadata).build();
    }

    StockMetadata toStock(StockDetails stockDetails);
}
