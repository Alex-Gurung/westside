package com.example.spacetrader.Entity;

public class Trader extends Character implements TraderCapability {
    private double credits = Double.POSITIVE_INFINITY;
    public Trader(String name) {
        super(name, new Ship(ShipType.GNAT));
    }

    public void setPrice(Good good) {
        setPrice(good, this.currentSolarSystem.getTechLevel(), this.currentSolarSystem.getResource());
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
