package com.example.spacetrader.Entity;

public enum GoodType {
    WATER(30, 54, true, false),
    FUR(250, 320, true, false),
    FOOD(105, 135, true, false),
    ORE(390, 490, true, false),
    ENTERTAINMENT(180, 240, false, false),
    FIREARM(725, 1175, false, true),
    MEDICINE(510, 630, false, false),
    MACHINE(690, 810, false, false),
    NARCOTIC(2620, 3500, false, true),
    ROBOT(3950, 4400, false, false);

    boolean isNaturalResource;
    boolean isIllegal;
    int minPrice;
    int maxPrice;

    GoodType(int minPrice, int maxPrice, boolean isNaturalResource, boolean isIllegal) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.isNaturalResource = isNaturalResource;
        this.isIllegal = isIllegal;
    }

}
