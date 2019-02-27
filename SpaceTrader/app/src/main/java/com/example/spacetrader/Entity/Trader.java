package com.example.spacetrader.Entity;

public class Trader extends Character implements TraderCapability {
    private double credits = Double.POSITIVE_INFINITY;
    public Trader(String name) {
        super(name, new Ship(ShipType.GNAT));
    }

    @Override
    public void setPrice(Good good) {
        GoodType gt = good.getGoodType();
        int min = gt.getMinPrice();
        int max = gt.getMaxPrice();
        boolean isNaturalGood = gt.getIsNaturalResource();
        SolarSystem ss = this.currentSolarSystem;
        TechLevel tl = ss.getTechLevel();

        int tlordinal = tl.ordinal();

        if(!isNaturalGood) {
            tlordinal = 8 - tlordinal;
        }

        double add = ((max - min) * (double)tlordinal)/8;
        good.setPrice(min + add);
    }

    @Override
    public double getPrice(Good good) {
        return good.getPrice();
    }

    @Override
    public boolean canSell(Good good) {
        return ship.hasGood(good);
    }

    @Override
    public boolean canBuy(Good good) {
        return good.getPrice() <= credits && ship.hasCargoSpace();
    }

    @Override
    public void buy(Good g) {
        credits -= g.getPrice();
        ship.addCargo(g);
    }

    @Override
    public void sell(Good g) {
        credits += g.getPrice();
        ship.removeCargo(g);
    }

    @Override
    public Good[] getCargo() {
        return ship.getCargo();
    }
}
