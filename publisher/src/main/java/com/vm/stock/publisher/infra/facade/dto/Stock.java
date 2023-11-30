/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.infra.facade.dto;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    private String scriptName;
    private List<StockTimeLine> stockTimeLines;
    private OffsetDateTime updateAt;
    private Integer latestPrice;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StockTimeLine {

        private int price;
        private OffsetDateTime time;
    }
}
