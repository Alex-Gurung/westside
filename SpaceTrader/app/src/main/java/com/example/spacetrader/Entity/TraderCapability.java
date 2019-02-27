package com.example.spacetrader.Entity;

public interface TraderCapability {
    double getPrice(Good good);
    void setPrice(Good good);
    boolean canSell(Good good);
    boolean canBuy(Good good);
    void buy(Good good);
    void sell(Good good);
    Good[] getCargo();
}
