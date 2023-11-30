/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.scheduler;

import com.vm.stock.publisher.service.PublisherService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * @author Piyush Kumar.
 * @since 22/04/23.
 */

@Data
@Slf4j
@Component
public class StockPricePublisher {

    private final PublisherService publisherService;

    @Scheduled(cron = "${scheduler.cronExpression}")
    @SchedulerLock(name = "stock_price_publisher", lockAtLeastFor = "${scheduler.lockAtLeastFor}")
    public void publishStockPrice() {

        log.info("Publishing stock price to S3.");

        publisherService.publishPrice();

        log.info("Stock price published successfully to S3.");
    }

}