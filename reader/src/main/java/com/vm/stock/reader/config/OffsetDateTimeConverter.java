/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.config;

import static java.lang.String.format;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Piyush Kumar.
 * @since 23/04/23.
 */
@Slf4j
@Component
public class OffsetDateTimeConverter implements Converter<String, OffsetDateTime> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public OffsetDateTime convert(String source) {

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        try {

            Date parseDate = formatter.parse(source);

            return parseDate.toInstant().atOffset(ZoneOffset.UTC)
                    .plus(5, HOURS)
                    .plus(30, MINUTES);

        } catch (ParseException e) {

            log.error("Exception while formatting date, given date={}", source);
            throw new IllegalArgumentException(format("Exception while formatting date, given date=%s", source),e);
        }
    }
}
