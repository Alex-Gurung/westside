package com.example.spacetrader.Entity;

public interface TraderCapability {
    double getPrice(Good good);

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
        good.setPrice(min + add);
    }

    boolean canSell(Good good);
    boolean canBuy(Good good);
    void buy(Good good);
    void sell(Good good);
    Good[] getCargo();
}
