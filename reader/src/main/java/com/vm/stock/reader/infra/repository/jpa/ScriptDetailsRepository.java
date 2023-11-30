/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.infra.repository.jpa;

import com.vm.stock.reader.infra.repository.dto.entity.ScriptPriceDetailsEntity;
import java.time.OffsetDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Piyush Kumar.
 * @since 21/04/23.
 */
public interface ScriptDetailsRepository extends JpaRepository<ScriptPriceDetailsEntity, Long> {

    boolean existsByScriptNameAndUpdatedAt(String scriptName, OffsetDateTime updatedAt);

    Page<ScriptPriceDetailsEntity> findByScriptNameAndUpdatedAtIsBetween(String scriptName, OffsetDateTime startDate, OffsetDateTime endDate, Pageable pageable);
}
