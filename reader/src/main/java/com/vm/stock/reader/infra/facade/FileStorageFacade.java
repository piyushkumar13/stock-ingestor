/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.infra.facade;


import com.vm.stock.reader.infra.facade.dto.Stock;
import java.util.Optional;

/**
 * stocks/scriptName/date.json
 *
 * @author Piyush Kumar.
 * @since 22/04/23.
 */
public interface FileStorageFacade {

    boolean doesFolderExists(String objectPath);

    boolean doesObjectExists(String objectPath);

    Optional<Stock> getObject(String objectPath);

}
