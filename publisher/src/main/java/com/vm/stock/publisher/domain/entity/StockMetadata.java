/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.domain.entity;

import java.time.OffsetDateTime;
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
public class StockMetadata {

    private String scriptName;
    private String company;
    private int price;
    private OffsetDateTime listedAt;
    private String currency;
}
