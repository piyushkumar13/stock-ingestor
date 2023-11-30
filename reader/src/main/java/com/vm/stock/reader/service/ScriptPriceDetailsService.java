/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.service;

import com.vm.stock.reader.domain.entity.ScriptPriceDetails;
import com.vm.stock.reader.domain.value.GetPaginatedStockPriceBtwDateRangeReq;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 * @author Piyush Kumar.
 * @since 23/04/23.
 */
public interface ScriptPriceDetailsService {

    Page<ScriptPriceDetails> fetchScriptPriceDetails(GetPaginatedStockPriceBtwDateRangeReq request);


    List<ScriptPriceDetails> fetchScriptDetailsForPreDefinedRange(GetPaginatedStockPriceBtwDateRangeReq request);

}
