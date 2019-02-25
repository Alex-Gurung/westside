package com.example.spacetrader.Entity;

public enum GoodType {
    WATER(30, 54, true, false, 0, 0),
    FUR(250, 320, true, false, 0, 0),
    FOOD(105, 135, true, false, 1, 0),
    ORE(390, 490, true, false, 2, 2),
    ENTERTAINMENT(180, 240, false, false, 3, 1),
    FIREARM(725, 1175, false, true, 3, 1),
    MEDICINE(510, 630, false, false, 4, 1),
    MACHINE(690, 810, false, false, 4, 3),
    NARCOTIC(2620, 3500, false, true, 5, 0),
    ROBOT(3950, 4400, false, false, 6, 4);

    private boolean isNaturalResource;
    private boolean isIllegal;
    private int minPrice;
    private int maxPrice;
    private int minTechLevelProduce;
    private int minTechLevelUse;

    /**
     * constructor that instantiates a GoodType for the player/NPCS to gather, trade, or steal
     *
     * @param minPrice the minimum price of said good at any given moment in the universe
     * @param maxPrice the maximum price of said good at any given moment in the universe
     * @param isNaturalResource boolean value to determine if said resource is a natural resource
     * @param isIllegal boolean value to determine if said resource is an illegal resource
     */
    GoodType(int minPrice, int maxPrice, boolean isNaturalResource, boolean isIllegal, int minTechLevelProduce, int minTechLevelUse) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.isNaturalResource = isNaturalResource;
        this.isIllegal = isIllegal;
        this.minTechLevelProduce = minTechLevelProduce;
        this.minTechLevelUse = minTechLevelUse;
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

    public int getMinTechLevelProduce(){
        return minTechLevelProduce;
    }

    public int getMinTechLevelUse() {
        return minTechLevelUse;
    }
}
