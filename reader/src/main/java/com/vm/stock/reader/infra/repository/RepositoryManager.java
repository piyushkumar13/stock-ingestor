/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.infra.repository;

import com.vm.stock.reader.infra.repository.dto.entity.ScriptPriceDetailsEntity;
import java.time.OffsetDateTime;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Piyush Kumar.
 * @since 21/04/23.
 */
public interface RepositoryManager {

    void saveScriptPriceDetails(ScriptPriceDetailsEntity scriptPriceDetailsEntity);

    void saveScriptPriceDetailsEntities(Collection<ScriptPriceDetailsEntity> scriptPriceDetailsEntities);

    boolean existsScriptPriceDetailsEntityByScriptNameAndUpdatedAt(String scriptName, OffsetDateTime updatedAt);

    Page<ScriptPriceDetailsEntity> fetchScriptPriceDetails(String scriptName, OffsetDateTime startDate, OffsetDateTime endDate, Pageable pageable);
}
