package com.example.spacetrader.Entity;

public interface TraderCapability {
    public double getPrice(Good good);
    public void setPrice(Good good);
    public boolean canProduce(Good good);
    public boolean canUse(Good good);
}
