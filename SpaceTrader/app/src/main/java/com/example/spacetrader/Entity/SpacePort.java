package com.example.spacetrader.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpacePort implements TraderCapability {

    private TechLevel techlevel;
    private List<Good> goods;
    Random random = new Random();

    public SpacePort(TechLevel techlevel) {
        this.techlevel = techlevel;
        goods = new ArrayList<>();

        int numGoods = 25 + random.nextInt(25);
        populateGoods(numGoods);
    }

    private void populateGoods(int numGoods) {
        List<GoodType> producable = new ArrayList<>();
        for(GoodType gt : GoodType.values()) {
            if(canProduce(new Good(gt))){
                producable.add(gt);
            }
        }

        for (int i = 0; i < numGoods; i++) {
            Good add = new Good(producable.get(random.nextInt(producable.size())));
            goods.add(add);
        }
    }

    @Override
    public double getPrice(Good good) {
        return 0;
    }

    @Override
    public void setPrice(Good good) {
        GoodType gt = good.getGoodType();
        int min = gt.getMinPrice();
        int max = gt.getMaxPrice();
        boolean isNaturalGood = gt.getIsNaturalResource();
        int tlordinal = techlevel.ordinal();

        if(!isNaturalGood) {
            tlordinal = 8 - tlordinal;
        }

        double add = ((max - min) * (double)tlordinal)/8;
        good.setPrice(min + add);
    }

    @Override
    public boolean canProduce(Good good) {
        return good.getMinTechLevelProduce() <= techlevel.ordinal();
    }

    @Override
    public boolean canUse(Good good) {
        return good.getMinTechLevelUse() <= techlevel.ordinal();
    }
}
