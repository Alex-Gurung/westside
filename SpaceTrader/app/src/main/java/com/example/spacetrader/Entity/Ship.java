package com.example.spacetrader.Entity;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private ShipType shiptype;

    private int fuel;
    private double maxDistance;
    private Gadget[] gadgets;
    private Weapon[] weapons;
    private Shield[] shields;
    private Mercenary[] mercs;
    private boolean hasEscapePod;

    public Ship (ShipType shiptype) {
        this.shiptype = shiptype;
        maxDistance = (double) (shiptype.getMaxDistance());
        gadgets = new Gadget[shiptype.getGadgetSlots()];
        weapons = new Weapon[shiptype.getWeaponSlots()];
        shields = new Shield[shiptype.getShieldSlots()];
        mercs = new Mercenary[shiptype.getNumMercenaries()];
        hasEscapePod = false;
        fuel = 100;
    }

    public String toString() {
        return shiptype.toString();
    }

    public void refuel(int fuelAdded) {
        fuel = Math.min(fuel +  fuelAdded, 100);
        maxDistance = ((double)(fuel) / 100) * maxDistance;
    }

}
