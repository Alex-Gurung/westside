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

    private boolean isNaturalResource;
    private boolean isIllegal;
    private int minPrice;
    private int maxPrice;

    /**
     * constructor that instantiates a GoodType for the player/NPCS to gather, trade, or steal
     *
     * @param minPrice the minimum price of said good at any given moment in the universe
     * @param maxPrice the maximum price of said good at any given moment in the universe
     * @param isNaturalResource boolean value to determine if said resource is a natural resource
     * @param isIllegal boolean value to determine if said resource is an illegal resource
     */
    GoodType(int minPrice, int maxPrice, boolean isNaturalResource, boolean isIllegal) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.isNaturalResource = isNaturalResource;
        this.isIllegal = isIllegal;
    }

    public int getMinPrice() {
        return minPrice;
    }
    public int getMaxPrice() {
        return maxPrice;
    }
    public boolean getIsNaturalResource() {
        return isNaturalResource;
    }
    public boolean getIsIllegal() {
        return isIllegal;
    }

}
