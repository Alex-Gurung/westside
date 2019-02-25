package com.example.spacetrader.Entity;

public interface TraderCapability {
    public double getPrice(Good good);
    public void setPrice(Good good);
    public boolean canSell(Good good);
    public boolean canBuy(Good good);
}
