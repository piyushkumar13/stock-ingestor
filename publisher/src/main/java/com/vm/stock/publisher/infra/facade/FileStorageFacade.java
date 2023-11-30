/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.publisher.infra.facade;


import com.vm.stock.publisher.infra.facade.dto.Stock;
import java.util.Optional;

/**
 * stocks/scriptName/date.json
 *
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
public interface FileStorageFacade {

    void putObject(String objectPath, String content);

    boolean doesFolderExists(String objectPath);

    boolean doesObjectExists(String objectPath);

    Optional<Stock> getObject(String objectPath);

}
