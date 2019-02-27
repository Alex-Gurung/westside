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
        double pr = min + add;
        if(resource.equals(gt.getHighCostResource())) {
            pr *= 2;
        }
        if(resource.equals(gt.getLowCostResource())) {
            pr *= 0.5;
        }
        good.setPrice(pr);
    }

    boolean canSell(Good good);
    boolean canBuy(Good good);
    void buy(Good good);
    void sell(Good good);
    Good[] getCargo();
}
