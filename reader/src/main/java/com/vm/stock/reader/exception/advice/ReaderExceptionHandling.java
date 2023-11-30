/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.exception.advice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Rest apis error handler.
 *
 * @author Piyush Kumar.
 * @since 10/04/23.
 */
@Slf4j
@RestControllerAdvice
public class ReaderExceptionHandling {


    /* ============================= In-built Exception Handling ===========================*/

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handle(IllegalArgumentException e, WebRequest request) {

        log.error("IllegalArgumentException caught in exception handler", e);

        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception e, WebRequest request) {

        log.error("Exception caught in exception handler", e);

        return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
    }
}
