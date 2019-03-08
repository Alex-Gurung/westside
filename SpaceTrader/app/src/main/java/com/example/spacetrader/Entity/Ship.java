package com.example.spacetrader.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * a class tha represents the current player's current ship. Each ship object has a cargo hold, a
 * shield capacity, gadgets, weapons, and the possibility for mercenaries and an escape pods
 */
public class Ship {

    private ShipType shiptype;

    private double fuel;
    private Good[] cargo;
    private int numGoods;
    private Gadget[] gadgets;
    private Weapon[] weapons;
    private Shield[] shields;
    private Mercenary[] mercs;
    private boolean hasEscapePod;

    /**
     * constructor that instantiates a Ship object based on the shipType passed in
     *
     * @param shiptype enum value that contains all the info/attributes of the selected shipType
     */
    public Ship (ShipType shiptype) {
        this.shiptype = shiptype;
        gadgets = new Gadget[shiptype.getGadgetSlots()];
        weapons = new Weapon[shiptype.getWeaponSlots()];
        shields = new Shield[shiptype.getShieldSlots()];
        mercs = new Mercenary[shiptype.getNumMercenaries()];
        cargo = new Good[shiptype.getCargoHolds()];
        numGoods = 0;
        hasEscapePod = false;
        fuel = 1.0;
    }

    public double distanceLeft() {
        return fuel * (double)shiptype.getMaxDistance();
    }

    public void travel(double distance) {
        double fuelUsed = distance/(double)shiptype.getMaxDistance();
        fuel -= fuelUsed;
    }
    /**
     * Overridden toString method that returns the ShipType's toString method
     *
     * @return a String representation of the Ship's toString method
     */
    @Override
    public String toString() {
        return shiptype.toString();
    }

    /**
     * a method that refuels the player's ship
     *
     * @param fuelAdded int value that represents the fuel to be added to the ship's current fuel
     *                  level
     */
    public void refuel(double fuelAdded) {
        fuel = Math.min(fuel +  fuelAdded, 1.0);
    }

    /**
     * returns all of the cargo in the current ship
     *
     * @return tan array of Goods that represent the cargo hold
     */
    public Good[] getCargo(){
        return cargo;
    }

    /**
     * method that determines if the player's cargo ahs enough space for another good
     *
     * @return a boolean representation of if the ship has enough cargo for another good
     */
    public boolean hasCargoSpace() {
        return numGoods < cargo.length;
    }

    /**
     * method that adds cargo to the ships cargo hold
     *
     * @param good the good to be added to the cargo hold
     */
    public void addCargo(Good good) {
        cargo[numGoods] = good;
        numGoods++;
    }

    /**
     * method that determines if the ship's cargo hold has a particular good
     *
     * @param good of type Good that may or may not be in the cargo hold
     * @return a boolean representation of if the ship's cargo hold has the particular good
     */
    public boolean hasGood(Good good) {
        if(numGoods == 0) return false;
        return Arrays.asList(cargo).contains(good);
    }


    /**
     * a method that removes the specific good in the ship's cargo hold.
     *
     * @param good of type Good to be removed. Returns null if it's not in the cargo hold
     * @return the Good that is to be removed from the cargo hold. Return null if it's not there
     */
    public void removeCargo(Good good) {
        //Good removed = null;
        for(int i = 0; i < numGoods; i++) {
            if(cargo[i].equals(good)){
                //removed = cargo[i];
                numGoods--;
                cargo[i] = cargo[numGoods];
                cargo[numGoods] = null;
                return;
            }
        }
        //return removed;
    }

    public double getFuel() {
        return fuel;
    }
}
