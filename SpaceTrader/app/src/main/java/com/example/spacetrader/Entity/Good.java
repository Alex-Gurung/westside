package com.example.spacetrader.Entity;

public class Good {
    private GoodType goodtype;
    private double price;

    /**
     * constructor
     * @param goodtype
     */
    public Good (GoodType goodtype) {
        this.goodtype = goodtype;
        price = 0;
    }

    /**
     * sets the price for the good
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * returns the price of this good
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * returns the type of good this is
     * @return this Good's goodtype
     */
    public GoodType getGoodType() {
        return goodtype;
    }

    public int getMinTechLevelUse() {
        return goodtype.getMinTechLevelUse();
    }

    public int getMinTechLevelProduce() {
        return goodtype.getMinTechLevelProduce();
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof Good)) return false;
        Good g = (Good) o;
        return g.getGoodType() == this.goodtype;
    }

}
