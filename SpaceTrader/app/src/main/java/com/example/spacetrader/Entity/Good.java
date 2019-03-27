package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 * A class that represents the attributes of each GoodType including price and tech level. Goods
 * are a part of the player's cargo, spaceport, and trader's cargo
 */
public class Good implements Serializable {
    private final GoodType goodtype;
    private double price;

    /**
     * constructor that takes in a GoodType to be instantiated to the instance field goodType
     *
     * @param goodtype of type GoodType that will be the good type of the current good
     */
    public Good (GoodType goodtype) {
        this.goodtype = goodtype;
        price = 0;
    }

    /**
     * sets the price for the good based on the current solar system's attributes
     *
     * @param price of type double to be the price of the good based on the current solar system's
     *              attributes
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * returns the price of the current good
     *
     * @return a double that represents the current good's price
     */
    public double getPrice() {
        return price;
    }

    /**
     * returns the type of the current good
     *
     * @return the current good's good type
     */
    public GoodType getGoodType() {
        return goodtype;
    }

    /**
     * getter method that returns the minimum tech level use of the current good (based on the
     * minTechLevelUse of the good's GoodType)
     *
     * @return an int that represents the minimum tech level use of the current good
     */
    public int getMinTechLevelUse() {
        return goodtype.getMinTechLevelUse();
    }

    /**
     * getter method that returns the minimum tech level produce of the current good (based on the
     * minTechLevelProduce of the good's GoodType)
     *
     * @return an int that represents the minimum tech level produce of the current good
     */
    public int getMinTechLevelProduce() {
        return goodtype.getMinTechLevelProduce();
    }

    /**
     * an equals method that determines if the current good is equal to the passed in Object
     *
     * @param o of type Object that is to be compared to the current Good
     * @return a booelan representation of whether {@code this} is equal in value to the param o
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (!(o instanceof Good)) return false;
        Good g = (Good) o;
        return g.getGoodType() == this.goodtype;
    }
}
