/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.service.mapper;

import com.vm.stock.reader.domain.entity.ScriptPriceDetails;
import com.vm.stock.reader.infra.facade.dto.Stock;
import com.vm.stock.reader.infra.repository.dto.entity.ScriptPriceDetailsEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

/**
 * @author Piyush Kumar.
 * @since 21/04/23.
 */
@Mapper(componentModel = "spring")
public interface ScriptPriceDetailsEntityMapper {

    @Mapping(target = "updatedAt", source = "stockTimeLine.time")
    ScriptPriceDetailsEntity toScriptPriceDetailsEntity(String scriptName, Stock.StockTimeLine stockTimeLine);

    default Page<ScriptPriceDetails> toScriptPriceDetailsPage(Page<ScriptPriceDetailsEntity> scriptPriceDetailsEntityPage) {

        return scriptPriceDetailsEntityPage.map(this::toScriptPriceDetails);

    }

    ScriptPriceDetails toScriptPriceDetails(ScriptPriceDetailsEntity scriptPriceDetailsEntity);

    default List<ScriptPriceDetails> toScriptPriceDetailsList(Page<ScriptPriceDetailsEntity> scriptPriceDetailsEntityPage){
        List<ScriptPriceDetailsEntity> scriptPriceDetailsEntityList = scriptPriceDetailsEntityPage.getContent();
        return toScriptPriceDetailsList(scriptPriceDetailsEntityList);
    }


    List<ScriptPriceDetails> toScriptPriceDetailsList(List<ScriptPriceDetailsEntity> scriptPriceDetailsEntities);
}
