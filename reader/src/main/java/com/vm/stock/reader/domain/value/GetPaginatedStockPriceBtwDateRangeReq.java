/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.domain.value;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Piyush Kumar.
 * @since 23/04/23.
 */

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPaginatedStockPriceBtwDateRangeReq {

    private String scriptName;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private SortMode sortMode;
    private int pageNumber;
    private int pageSize;

}
