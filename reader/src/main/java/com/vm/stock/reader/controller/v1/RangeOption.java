/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.vm.stock.reader.controller.v1;

/**
 * @author Piyush Kumar.
 * @since 23/04/23.
 */
public enum RangeOption {

    WEEK(7),
    WEEK_52(364),
    MONTH(30),
    MONTH_6(180),
    YEAR_5(1825);

    private int numOfDays;

    RangeOption(int numOfDays){
        this.numOfDays = numOfDays;
    }

    public int getNumOfDays(){
        return this.numOfDays;
    }
}
