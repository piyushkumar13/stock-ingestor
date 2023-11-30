/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.controller.v1;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.vm.stock.reader.controller.v1.dto.ScriptPriceDetailsResponse;
import com.vm.stock.reader.controller.v1.dto.mapper.ScriptPriceMapper;
import com.vm.stock.reader.controller.v1.dto.validator.RequestValidator;
import com.vm.stock.reader.controller.v1.dto.SortMode;
import com.vm.stock.reader.domain.entity.ScriptPriceDetails;
import com.vm.stock.reader.service.ScriptPriceDetailsService;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Piyush Kumar.
 * @since 23/04/23.
 */
@Data
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class StockController {

    private final RequestValidator requestValidator;
    private final ScriptPriceDetailsService scriptPriceDetailsService;
    private final ScriptPriceMapper scriptPriceMapper;

    @RequestMapping(value = "stocks/{script-name}/price", method = GET, produces = APPLICATION_JSON_VALUE)
    public Page<ScriptPriceDetailsResponse> getScriptDetails(@PathVariable("script-name") String scriptName,
                                                             @RequestParam("start-date") @NotNull OffsetDateTime startDate,
                                                             @RequestParam("end-date") OffsetDateTime endDate,
                                                             @RequestParam(name = "sort", defaultValue = "DESC") SortMode sortMode,
                                                             @RequestParam("page") @PositiveOrZero int pageNumber,
                                                             @RequestParam("limit") @Positive int pageSize) {

        log.info("Received request to getStockPrice between date range, scriptName={}, startDate={}, endDate={}, sortMode={}, pageNumber={}, pageSize={}",
                scriptName,
                startDate,
                endDate,
                sortMode,
                pageNumber,
                pageSize);

        requestValidator.validate(startDate, endDate);

        Page<ScriptPriceDetails> scriptPriceDetailsPage = scriptPriceDetailsService.fetchScriptPriceDetails(scriptPriceMapper.toGetPaginatedStockPriceBtwDateRangeReq(
                scriptName.toUpperCase(),
                startDate,
                endDate,
                sortMode,
                pageNumber,
                pageSize));

        return scriptPriceMapper.toScriptPriceDetailsResponsePage(scriptPriceDetailsPage);
    }


    @RequestMapping(value = "stocks/{script-name}", method = GET, produces = APPLICATION_JSON_VALUE)
    public List<ScriptPriceDetailsResponse> getScriptDetails(@PathVariable("script-name") String scriptName,
                                                             @RequestParam(name = "range-option", defaultValue = "WEEK") RangeOption rangeOption) {

        log.info("Received request to getStockPrice between date range, scriptName={}", scriptName);

        OffsetDateTime currentDateTime = Instant.now().atOffset(ZoneOffset.UTC);

        OffsetDateTime startDate = currentDateTime.minus(rangeOption.getNumOfDays(), ChronoUnit.DAYS);

        List<ScriptPriceDetails> scriptPriceDetailsList = scriptPriceDetailsService.fetchScriptDetailsForPreDefinedRange(scriptPriceMapper.toGetPaginatedStockPriceBtwDateRangeReq(
                scriptName.toUpperCase(),
                startDate,
                currentDateTime,
                SortMode.ASC,
                null,
                null));

        return scriptPriceMapper.toScriptPriceDetailsResponse(scriptPriceDetailsList);
    }
}
