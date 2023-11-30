/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.service.mapper;

import com.vm.stock.publisher.domain.entity.StockMetadata;
import com.vm.stock.publisher.infra.repository.dto.entity.StockMetadataEntity;
import org.mapstruct.Mapper;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
@Mapper(componentModel = "spring")
public interface StockMetadataEntityMapper {

    StockMetadataEntity toStockEntity(StockMetadata stockMetadata);
}
