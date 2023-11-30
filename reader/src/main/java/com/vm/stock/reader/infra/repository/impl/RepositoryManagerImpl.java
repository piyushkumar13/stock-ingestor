/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.infra.repository.impl;

import com.vm.stock.reader.infra.repository.RepositoryManager;
import com.vm.stock.reader.infra.repository.dto.entity.ScriptPriceDetailsEntity;
import com.vm.stock.reader.infra.repository.jpa.ScriptDetailsRepository;
import java.time.OffsetDateTime;
import java.util.Collection;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Piyush Kumar.
 * @since 21/04/23.
 */
@Data
@Slf4j
@Repository
public class RepositoryManagerImpl implements RepositoryManager {

    private final ScriptDetailsRepository scriptDetailsRepository;

    @Override
    public void saveScriptPriceDetails(final ScriptPriceDetailsEntity scriptPriceDetailsEntity) {

        log.info("Saving script price details, scriptPriceDetails={}", scriptPriceDetailsEntity);

        scriptDetailsRepository.save(scriptPriceDetailsEntity);

        log.info("Script price details is saved successfully, scriptPriceDetails={}", scriptPriceDetailsEntity);
    }

    @Override
    public void saveScriptPriceDetailsEntities(final Collection<ScriptPriceDetailsEntity> scriptPriceDetailsEntities) {

        log.info("Saving script price details, scriptPriceDetails={}", scriptPriceDetailsEntities);

        scriptDetailsRepository.saveAll(scriptPriceDetailsEntities);

        log.info("Script price details is saved successfully, scriptPriceDetails={}", scriptPriceDetailsEntities);
    }

    @Override
    public boolean existsScriptPriceDetailsEntityByScriptNameAndUpdatedAt(final String scriptName, final OffsetDateTime updatedAt) {

        log.info("Check whether scriptPriceDetailsEntity exists by scriptName and updatedAt time, scriptName={}, updatedAt={}", scriptName, updatedAt);

        boolean existsByScriptNameAndUpdatedAt = scriptDetailsRepository.existsByScriptNameAndUpdatedAt(scriptName, updatedAt);

        log.info("scriptPriceDetailsEntity exists by scriptName and updatedAt time, scriptName={}, updatedAt={}, existsByScriptNameAndUpdatedAt={}",scriptName, updatedAt, existsByScriptNameAndUpdatedAt);

        return existsByScriptNameAndUpdatedAt;
    }

    @Override
    public Page<ScriptPriceDetailsEntity> fetchScriptPriceDetails(String scriptName, OffsetDateTime startDate, OffsetDateTime endDate, Pageable pageable) {

        log.info("Fetching scriptPriceDetails, scriptName={}, startDate={}, endDate={}, pageRequest={}", scriptName, startDate, endDate, pageable);
        return scriptDetailsRepository.findByScriptNameAndUpdatedAtIsBetween(scriptName, startDate, endDate, pageable);
    }
}
