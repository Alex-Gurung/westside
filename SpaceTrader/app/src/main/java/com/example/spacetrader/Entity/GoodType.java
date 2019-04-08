package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 * an enum class that contains the attributes and methods necessary to make up each type of GoodType
 */
public enum GoodType {
    WATER(30, 54, true, 0, 0, Resource.LOTSOFWATER, Resource.DESERT),
    FUR(250, 320, true, 0, 0, Resource.RICHFAUNA, Resource.LIFELESS),
    FOOD(105, 135, true, 1, 0, Resource.RICHSOIL, Resource.POORSOIL),
    ORE(390, 490, true, 2, 2, Resource.MINERALRICH, Resource.MINERALPOOR),
    ENTERTAINMENT(180, 240, false, 3, 1, Resource.ARTISTIC, Resource.NEVER),
    FIREARM(725, 1175, false, 3, 1, Resource.WARLIKE, Resource.NEVER),
    MEDICINE(510, 630, false, 4, 1, Resource.LOSTSOFHERBS, Resource.NEVER),
    MACHINE(690, 810, false, 4, 3, Resource.NEVER, Resource.NEVER),
    NARCOTIC(2620, 3500, false, 5, 0, Resource.WIERDMUSHROOMS, Resource.NEVER),
    ROBOT(3950, 4400, false, 6, 4, Resource.NEVER, Resource.NEVER);

    private final int minPrice;
    private final int maxPrice;
    private final boolean isNaturalResource;
    private final int minTechLevelProduce;
    private final int minTechLevelUse;
    private final Resource lowCostResource;
    private final Resource highCostResource;

    /**
     * constructor that instantiates a GoodType with all its necessary attributes
     *
     * @param minPrice of type int that represents the minimum price of said good at any given
     *                 moment in the universe
     * @param maxPrice of type int that represents the maximum price of said good at any given
     *                 moment in the universe
     * @param isNaturalResource of type boolean that represents whether said resource is a natural
     *                          resource
     * @param lowCostResource of type Resource that represents the cost of said resource based on
     *                        positively affecting environmental conditions of the solar
     *                        systems/planet
     * @param highCostResource of type Resource that represents the cost of said resource based on
     *                         negatively affecting environmental conditions of the solar
     *                         systems/planet
     */
    GoodType(int minPrice, int maxPrice, boolean isNaturalResource,
             int minTechLevelProduce, int minTechLevelUse,
             Resource lowCostResource, Resource highCostResource) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.isNaturalResource = isNaturalResource;
        this.minTechLevelProduce = minTechLevelProduce;
        this.minTechLevelUse = minTechLevelUse;
        this.lowCostResource = lowCostResource;
        this.highCostResource = highCostResource;
    }

    /**
     * getter method to return the low cost resource
     *
     * @return the low cost resource
     */
    public Resource getLowCostResource() {
        return lowCostResource;
    }

    /**
     * getter method to return the high cost resource
     *
     * @return the high cost resource
     */
    public Resource getHighCostResource() {
        return highCostResource;
    }

    /**
     * getter method for the minimum price of the current good type at any given moment of the game
     *
     *
     * @return an int representation of the minimum price of the current good
     */
    public int getMinPrice() {
        return minPrice;
    }

    /**
     * getter method for the maximum price of the current goof type at any given moment of the game
     *
     * @return an int representation of the maximum price of the current good
     */
    public int getMaxPrice() {
        return maxPrice;
    }

    /**
     * getter method for whether the current good type is a natural resource
     *
     * @return a boolean representation of whether the current good type is a natural resource
     */
    public boolean getIsNaturalResource() {
        return isNaturalResource;
    }

    /**
     * a getter method for the minimum tech level produce of the current good type
     *
     * @return an int representation of the current good's minimum tech level produce
     */
    public int getMinTechLevelProduce() {
        return minTechLevelProduce;
    }

    /**
     * a getter method for the minimum tech level use of the current good type
     *
     * @return an int representation of the current good's minimum tech level use
     */
    public int getMinTechLevelUse() {
        return minTechLevelUse;
    }
}
