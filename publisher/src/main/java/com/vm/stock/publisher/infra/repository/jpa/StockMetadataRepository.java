/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.infra.repository.jpa;

import com.vm.stock.publisher.infra.repository.dto.entity.StockMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
public interface StockMetadataRepository extends JpaRepository<StockMetadataEntity, Long> {

}
