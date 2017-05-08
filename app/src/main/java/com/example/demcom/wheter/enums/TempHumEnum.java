package com.example.demcom.wheter.enums;

/**
 * Created by Demcom on 5/7/2017.
 */

public enum TempHumEnum {
    WINE_TEMP(16),
    WINE_HUM(70),
    BEER_TEMP(16),
    BEER_HUM(16);

    private final int temp;

    TempHumEnum(int temp) {
        this.temp = temp;
    }

    public int value(){
        return temp;
    }

}
