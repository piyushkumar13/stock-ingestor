/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.controller.v1.dto.validator;

import java.time.OffsetDateTime;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Piyush Kumar.
 * @since 23/04/23.
 */

@Data
@Slf4j
@Component
public class RequestValidator {


    public void validate(final OffsetDateTime startDate, final OffsetDateTime endDate){

        if (startDate.isEqual(endDate) || startDate.isAfter(endDate)){
            log.error("Provided dates are not valid. startDate is after endDate.");
            throw new IllegalArgumentException("Provided dates are not valid. startDate is after endDate.");
        }
    }
}
