package com.example.spacetrader.Entity;

public enum ShipType {
    //name, weapon slot, shield, gadget, escape pod, cargo bay, distance
    GNAT("Gnat");
    public String name;

    ShipType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }




}
