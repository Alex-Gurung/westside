package com.example.spacetrader.Entity;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private ShipType shiptype;

    private int fuel;
    private double maxDistance;
    private List<Gadget> gadgets;
    private List<Weapon> weapons;
    private List<Shield> shields;
    private boolean hasEscapePod;

    public Ship (ShipType shiptype) {
        this.shiptype = shiptype;
        maxDistance = (double) (shiptype.getMaxDistance());
        gadgets = new ArrayList(shiptype.getGadgetSlots());
        weapons = new ArrayList(shiptype.getWeaponSlots());
        shields = new ArrayList(shiptype.getShieldSlots());
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
