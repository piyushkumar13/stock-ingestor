/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.config;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
@Data
@Configuration
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "stocks")
public class StocksConfiguration {

    private List<StockDetails> stockDetailsList;
    private List<String> scriptNames;

    @Data
    @Configuration
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StockDetails {

        private String scriptName;
        private String company;
        private int price;
        private OffsetDateTime listedAt; /* Listing time. */
        private String currency;
    }
}
