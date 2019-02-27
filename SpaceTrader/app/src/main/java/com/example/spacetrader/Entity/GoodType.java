package com.example.spacetrader.Entity;

import com.example.spacetrader.R;

public enum GoodType {
    WATER(30, 54, true, false, 0, 0, Resource.LOTSOFWATER, Resource.DESERT),
    FUR(250, 320, true, false, 0, 0, Resource.RICHFAUNA, Resource.LIFELESS),
    FOOD(105, 135, true, false, 1, 0, Resource.RICHSOIL, Resource.POORSOIL),
    ORE(390, 490, true, false, 2, 2, Resource.MINERALRICH, Resource.MINERALPOOR),
    ENTERTAINMENT(180, 240, false, false, 3, 1, Resource.ARTISTIC, Resource.NEVER),
    FIREARM(725, 1175, false, true, 3, 1, Resource.WARLIKE, Resource.NEVER),
    MEDICINE(510, 630, false, false, 4, 1, Resource.LOSTSOFHERBS, Resource.NEVER),
    MACHINE(690, 810, false, false, 4, 3, Resource.NEVER, Resource.NEVER),
    NARCOTIC(2620, 3500, false, true, 5, 0, Resource.WIERDMUSHROOMS, Resource.NEVER),
    ROBOT(3950, 4400, false, false, 6, 4, Resource.NEVER, Resource.NEVER);

    private boolean isNaturalResource;
    private boolean isIllegal;
    private int minPrice;
    private int maxPrice;
    private int minTechLevelProduce;
    private int minTechLevelUse;
    private Resource lowCostResource;
    private Resource highCostResource;

    /**
     * constructor that instantiates a GoodType for the player/NPCS to gather, trade, or steal
     *
     * @param minPrice the minimum price of said good at any given moment in the universe
     * @param maxPrice the maximum price of said good at any given moment in the universe
     * @param isNaturalResource boolean value to determine if said resource is a natural resource
     * @param isIllegal boolean value to determine if said resource is an illegal resource
     */
    GoodType(int minPrice, int maxPrice, boolean isNaturalResource,
             boolean isIllegal, int minTechLevelProduce, int minTechLevelUse,
             Resource lowCostResource, Resource highCostResource) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.isNaturalResource = isNaturalResource;
        this.isIllegal = isIllegal;
        this.minTechLevelProduce = minTechLevelProduce;
        this.minTechLevelUse = minTechLevelUse;
        this.lowCostResource = lowCostResource;
        this.highCostResource = highCostResource;
    }

    public Resource getLowCostResource() {
        return lowCostResource;
    }

    public Resource getHighCostResource() {
        return highCostResource;
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
