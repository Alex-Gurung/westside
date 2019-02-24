package com.example.spacetrader.Entity;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private ShipType shiptype;

    private int fuel;
    private double maxDistance;
    private Good[] cargo;
    private Gadget[] gadgets;
    private Weapon[] weapons;
    private Shield[] shields;
    private Mercenary[] mercs;
    private boolean hasEscapePod;

    /**
     * constructor that instantiates a Ship object based on the shipType passed in
     *
     * @param shiptype enum value that containts all the info/attributes of the selected shipType
     */
    public Ship (ShipType shiptype) {
        this.shiptype = shiptype;
        maxDistance = (double) (shiptype.getMaxDistance());
        gadgets = new Gadget[shiptype.getGadgetSlots()];
        weapons = new Weapon[shiptype.getWeaponSlots()];
        shields = new Shield[shiptype.getShieldSlots()];
        mercs = new Mercenary[shiptype.getNumMercenaries()];
        cargo = new Good[shiptype.getCargoHolds()];
        hasEscapePod = false;
        fuel = 100;
    }

    /**
     * toString method that overrides the toString method in Object that returns the String
     * representation of all the attributes of ship
     *
     * @return the String representation of teh attributes of the ship and the ship type
     */
    @Override
    public String toString() {
        return shiptype.toString();
    }

    /**
     * a method with no return type that refuels the player's ship
     *
     * @param fuelAdded int value that represents the fuel to be added to the ship's current fuel
     *                  level
     */
    public void refuel(int fuelAdded) {
        fuel = Math.min(fuel +  fuelAdded, 100);
        maxDistance = ((double)(fuel) / 100) * maxDistance;
    }

    /**
     * returns all of the cargo in this ship
     * @return the cargo hold
     */
    public Good[] getCargo(){
        return cargo;
    }
}
