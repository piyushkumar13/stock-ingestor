/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.controller.v1.dto.mapper;

import com.vm.stock.reader.controller.v1.dto.ScriptPriceDetailsResponse;
import com.vm.stock.reader.controller.v1.dto.SortMode;
import com.vm.stock.reader.domain.entity.ScriptPriceDetails;
import com.vm.stock.reader.domain.value.GetPaginatedStockPriceBtwDateRangeReq;
import java.time.OffsetDateTime;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

/**
 * @author Piyush Kumar.
 * @since 23/04/23.
 */
@Mapper(componentModel = "spring")
public interface ScriptPriceMapper {


    GetPaginatedStockPriceBtwDateRangeReq toGetPaginatedStockPriceBtwDateRangeReq(String scriptName,
                                                                                  OffsetDateTime startDate,
                                                                                  OffsetDateTime endDate,
                                                                                  SortMode sortMode,
                                                                                  Integer pageNumber,
                                                                                  Integer pageSize);

    default Page<ScriptPriceDetailsResponse> toScriptPriceDetailsResponsePage(Page<ScriptPriceDetails> scriptPriceDetailsPage){

        return scriptPriceDetailsPage.map(this::toScriptPriceDetailsResponse);
    }

    ScriptPriceDetailsResponse toScriptPriceDetailsResponse(ScriptPriceDetails scriptPriceDetails);

    List<ScriptPriceDetailsResponse> toScriptPriceDetailsResponse(List<ScriptPriceDetails> scriptPriceDetailsList);
}
