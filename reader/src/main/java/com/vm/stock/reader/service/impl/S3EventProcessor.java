/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.vm.stock.reader.infra.facade.FileStorageFacade;
import com.vm.stock.reader.infra.facade.dto.Stock;
import com.vm.stock.reader.infra.repository.RepositoryManager;
import com.vm.stock.reader.infra.repository.dto.entity.ScriptPriceDetailsEntity;
import com.vm.stock.reader.service.EventProcessor;
import com.vm.stock.reader.service.mapper.ScriptPriceDetailsEntityMapper;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
@Data
@Slf4j
@Service
public class S3EventProcessor implements EventProcessor {

    private final FileStorageFacade fileStorageFacade;
    private final RepositoryManager repositoryManager;
    private final ScriptPriceDetailsEntityMapper scriptPriceDetailsEntityMapper;

    @Override
    public void processEvent(final String eventKey) {

        checkArgument(isNotBlank(eventKey), "eventKey is blank.");

        String[] eventKeySegments = eventKey.split("/");

        if (eventKeySegments.length != 2) { /* When only folder is created with scriptName like "VMW/" */
            log.info("Skipping processing since only folder with scriptName is present.");
            return;
        }

        fileStorageFacade.getObject(eventKey)
                .map(this::findNotProcessedTimeLines)
                .filter(scriptPriceDetailsEntities -> !scriptPriceDetailsEntities.isEmpty())
                .ifPresentOrElse(repositoryManager::saveScriptPriceDetailsEntities,
                        () -> log.info("No s3 object is present for eventKey or scriptPriceDetailsEntities set is empty, eventKey={}", eventKey));

    }


    private Set<ScriptPriceDetailsEntity> findNotProcessedTimeLines(final Stock stock){

        String scriptName = stock.getScriptName();

        Set<ScriptPriceDetailsEntity> scriptPriceDetailsEntities = stock.getStockTimeLines()
                .stream()
                .filter(stockTimeLine -> isFalse(repositoryManager.existsScriptPriceDetailsEntityByScriptNameAndUpdatedAt(scriptName, stockTimeLine.getTime())))
                .map(stockTimeLine -> scriptPriceDetailsEntityMapper.toScriptPriceDetailsEntity(scriptName, stockTimeLine))
                .collect(Collectors.toSet());

        log.info("scriptPriceDetailsEntities to be saved, scriptPriceDetailsEntities={}", scriptPriceDetailsEntities);

        return scriptPriceDetailsEntities;
    }
}
