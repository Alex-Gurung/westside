package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 *
 * interface that represents the capabilities of the player, trader NPCs, and space ports to be able
 * to trade goods
 */
public interface TraderCapability extends Serializable {
    /**
     * getter for the good's price
     * @param good the good that you want the price of
     * @return the price of the good
     */
    double getPrice(Good good);

    /**
     * default way to set price given only a good and a solar system
     * @param good the good whose price is being set
     * @param solarSystem the solar system in which the price is being set
     */
    default void setPrice(Good good, SolarSystem solarSystem) {
        setPrice(good, solarSystem.getTechLevel(), solarSystem.getResource());
    }

    /**
     * method that calculates the price of the given good based on the tech level and resource in
     * the current Solar
     * System
     *
     * @param good of type Good whose price will be calculated
     * @param tl of type TechLevel that partially determines the price of the given good
     * @param resource of type Resource that partially determines the price of the given good
     */
    default void setPrice(Good good, TechLevel tl, Resource resource) {
        GoodType gt = good.getGoodType();
        int min = gt.getMinPrice();
        int max = gt.getMaxPrice();
        boolean isNaturalGood = gt.getIsNaturalResource();

        int tlordinal = tl.ordinal();

        if(!isNaturalGood) {
            tlordinal = 8 - tlordinal;
        }

        double add = ((max - min) * (double)tlordinal)/8;
        double pr = min + add;
        if(resource.equals(gt.getHighCostResource())) {
            pr *= 2;
        }
        if(resource.equals(gt.getLowCostResource())) {
            pr *= 0.5;
        }
        good.setPrice(pr);
    }

    /**
     * method with no instantiation to be overridden in the classes that implement this interface
     *
     * @param good of type Good that may be sold
     * @return a boolean representation of whether the given good can be sold
     */
    boolean canSell(Good good);

    /**
     * method with no instantiation to be overridden in the classes that implement this interface
     *
     * @param good of type Good that may be bought
     * @return a boolean representation of whether the given good can be bought
     */
    boolean canBuy(Good good);

    /**
     * method with no instantiation to be overridden in the classes that implement this interface
     *
     * @param good of type Good to be bought
     */
    void buy(Good good);

    /**
     * method with no instantiation to be overridden in the classes that implement this interface
     *
     * @param good of type Good to be sold
     */
    void sell(Good good);

    /**
     * method with no instantiation to be overridden in the classes that implement this interface
     *
     * @return an array of Goods in the player's cargo hold
     */
    Good[] getCargo();
}
