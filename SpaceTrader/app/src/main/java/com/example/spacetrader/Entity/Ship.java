package com.example.spacetrader.Entity;

public class Ship {

    private ShipType shiptype;

    public Ship (ShipType shiptype) {
        this.shiptype = shiptype;
    }

    public String toString() {
        return shiptype.toString();
    }
}
