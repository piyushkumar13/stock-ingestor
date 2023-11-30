/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.config;

import static java.lang.String.format;

import com.vm.stock.reader.controller.v1.RangeOption;
import com.vm.stock.reader.controller.v1.dto.SortMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Piyush Kumar.
 * @since 23/04/23.
 */
@Slf4j
@Component
public class RangeOptionConverter implements Converter<String, RangeOption> {

    @Override
    public RangeOption convert(final String source) {
        try {
            return RangeOption.valueOf(source.toUpperCase());

        } catch (IllegalArgumentException e) {

            log.error("Provided rangeOption value is not a legit value. Accepted values are : WEEK, WEEK_52, MONTH, MONTH_6, YEAR, providedValue={}", source);
            throw new IllegalArgumentException(format("Provided rangeOption value is not a legit value. Accepted values are : WEEK, WEEK_52, MONTH, MONTH_6, YEAR, providedValue=%s", source));
        }
    }
}