package com.example.spacetrader.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SpacePort implements TraderCapability {

    private TechLevel techlevel;
    private List<Good> goods;
    private Random random = new Random();
    private Resource resource;

    public SpacePort(TechLevel techlevel, Resource resource) {
        this.techlevel = techlevel;
        this.resource = resource;
        goods = new ArrayList<>();
        int numGoods = 25 + random.nextInt(25);
        populateGoods(numGoods);
    }

    private void populateGoods(int numGoods) {
        List<GoodType> producible = new ArrayList<>();
        for(GoodType gt : GoodType.values()) {
            if(gt.getMinTechLevelProduce() <= techlevel.ordinal()){
                producible.add(gt);
            }
        }
        for (int i = 0; i < numGoods; i++) {
            Good add = new Good(producible.get(random.nextInt(producible.size())));
            setPrice(add);
            goods.add(add);
        }
    }

    @Override
    public double getPrice(Good good) {
        return good.getPrice();
    }

    public void setPrice(Good good) {
        setPrice(good, techlevel, resource);
    }

    @Override
    public boolean canSell(Good good) {
        boolean tech = good.getMinTechLevelProduce() <= techlevel.ordinal();
        boolean inGoods = goods.contains(good);
        return tech && inGoods;
    }

    @Override
    public boolean canBuy(Good good) {
        return good.getMinTechLevelUse() <= techlevel.ordinal();
    }

    @Override
    public void buy(Good good) {
        goods.add(good);
    }

    @Override
    public void sell(Good good) {
        goods.remove(good);
    }

    public List<Good> getGoods() {
        return goods;
    }

    public Good[] getCargo() {
        Good[] ourCargo = new Good[goods.size()];
        for (int i = 0; i < goods.size(); i++) {
            ourCargo[i] = goods.get(i);
        }
        return ourCargo;
    }
}
