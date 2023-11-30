/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.service.mapper;

import com.vm.stock.publisher.config.StocksConfiguration;
import com.vm.stock.publisher.domain.entity.Stock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
@Mapper(componentModel = "spring")
public interface StockMapper {

    default Stock toStock(final StocksConfiguration.StockDetails stockDetails) {

        List<Stock.StockTimeLine> stockTimeLineList = new ArrayList<>();
        stockTimeLineList.add(toStockTimeLine(stockDetails));

        return Stock.builder()
                .scriptName(stockDetails.getScriptName())
                .stockTimeLines(stockTimeLineList)
                .latestPrice(stockDetails.getPrice())
                .updateAt(Instant.now().atOffset(ZoneOffset.UTC))
                .build();

    }

    @Mapping(target = "time", source = "listedAt")
    Stock.StockTimeLine toStockTimeLine(StocksConfiguration.StockDetails stockDetails);

    Stock toStock(final com.vm.stock.publisher.infra.facade.dto.Stock stock);
}
