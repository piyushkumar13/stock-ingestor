/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.controller.v1.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Piyush Kumar.
 * @since 21/04/23.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScriptPriceDetailsResponse {

    private String scriptName;
    private int price;
    private OffsetDateTime updatedAt;
}
