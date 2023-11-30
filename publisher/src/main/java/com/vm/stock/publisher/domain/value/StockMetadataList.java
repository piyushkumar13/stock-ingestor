/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.domain.value;

import com.vm.stock.publisher.domain.entity.StockMetadata;
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
public class StockMetadataList {

    List<StockMetadata> stockMetadata;
}
