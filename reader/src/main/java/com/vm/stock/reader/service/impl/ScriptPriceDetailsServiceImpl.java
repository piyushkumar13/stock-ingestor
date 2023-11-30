/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.service.impl;

import com.vm.stock.reader.domain.entity.ScriptPriceDetails;
import com.vm.stock.reader.domain.value.GetPaginatedStockPriceBtwDateRangeReq;
import com.vm.stock.reader.infra.repository.RepositoryManager;
import com.vm.stock.reader.infra.repository.dto.entity.ScriptPriceDetailsEntity;
import com.vm.stock.reader.service.ScriptPriceDetailsService;
import com.vm.stock.reader.service.mapper.ScriptPriceDetailsEntityMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author Piyush Kumar.
 * @since 23/04/23.
 */
@Data
@Slf4j
@Service
public class ScriptPriceDetailsServiceImpl implements ScriptPriceDetailsService {

    @Value("${predefinedDateRange.priceResult.pageSize}")
    private int pageSizeForPredefinedRange;

    private final RepositoryManager repositoryManager;
    private final ScriptPriceDetailsEntityMapper scriptPriceDetailsEntityMapper;

    @Override
    public Page<ScriptPriceDetails> fetchScriptPriceDetails(final GetPaginatedStockPriceBtwDateRangeReq req) {

        log.info("Processing GetPaginatedStockPriceBtwDateRangeReq={}", req);

        Pageable pageable = PageRequest.of(req.getPageNumber(), req.getPageSize(), Sort.Direction.valueOf(req.getSortMode().name()), "updatedAt");

        Page<ScriptPriceDetailsEntity> scriptPriceDetailsEntitiesPage = repositoryManager.fetchScriptPriceDetails(req.getScriptName(), req.getStartDate(), req.getEndDate(), pageable);

        return scriptPriceDetailsEntityMapper.toScriptPriceDetailsPage(scriptPriceDetailsEntitiesPage);
    }

    @Override
    public List<ScriptPriceDetails> fetchScriptDetailsForPreDefinedRange(final GetPaginatedStockPriceBtwDateRangeReq req) {

        log.info("Processing GetPaginatedStockPriceForPreDefinedRange={}", req);

        Pageable pageable = PageRequest.of(0, pageSizeForPredefinedRange, Sort.Direction.ASC, "updatedAt");

        Page<ScriptPriceDetailsEntity> scriptPriceDetailsEntitiesPage = repositoryManager.fetchScriptPriceDetails(req.getScriptName(), req.getStartDate(), req.getEndDate(), pageable);

        List<ScriptPriceDetails> scriptPriceDetailsList = scriptPriceDetailsEntityMapper.toScriptPriceDetailsList(scriptPriceDetailsEntitiesPage);

        findNextPageResultAndInflateList(scriptPriceDetailsList, req, scriptPriceDetailsEntitiesPage);

        return scriptPriceDetailsList;
    }

    private void findNextPageResultAndInflateList(final List<ScriptPriceDetails> list, final GetPaginatedStockPriceBtwDateRangeReq req, Page<ScriptPriceDetailsEntity> scriptPriceDetailsEntities){

        int i = 1;

        while ( i < scriptPriceDetailsEntities.getTotalPages()) {

            Pageable pageable = PageRequest.of(i, pageSizeForPredefinedRange, Sort.Direction.ASC, "updatedAt");

            Page<ScriptPriceDetailsEntity> scriptPriceDetailsEntitiesPage = repositoryManager.fetchScriptPriceDetails(req.getScriptName(), req.getStartDate(), req.getEndDate(), pageable);

            List<ScriptPriceDetails> scriptPriceDetailsList = scriptPriceDetailsEntityMapper.toScriptPriceDetailsList(scriptPriceDetailsEntitiesPage);

            list.addAll(scriptPriceDetailsList);

            i++;
        }
    }
}
