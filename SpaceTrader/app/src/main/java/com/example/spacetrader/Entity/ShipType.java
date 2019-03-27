package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 * an enum class that contains each ship type, as well as the attributes and methods for each ship
 * type
 */
public enum ShipType implements Serializable {
    //name, max distance, cargo holds, weapons, shields, gadgets, mercs, hull strength, fuel price
    FLEA("Flea", 20, 4, 0, 0, 0, 0, 1, 10),
    GNAT("Gnat", 14, 15, 1, 0, 1, 0, 2, 11),
    FIREFLY("Firefly",17, 20, 1, 1, 1, 0, 2, 11),
    MOSQUITO("Mosquito", 13, 15, 2, 1, 1, 0,3, 12),
    BUMBLEBEE("Bumblebee", 15, 20, 1, 2, 2, 1, 2, 12),
    BEETLE("Beetle", 14, 50, 0, 1, 1, 3, 1, 13),
    HORNET("Hornet", 16, 20, 3, 2, 1, 2, 3, 13),
    GRASSHOPPER("Grasshopper", 15, 30, 2, 2, 3, 3, 2, 14),
    TERMITE("Termite", 13, 60, 1, 3, 2, 3, 3, 15),
    WASP("Wasp", 14, 35, 3, 2, 2, 3, 3, 15);
    private final String name;
    private final int maxDistance;
    private final int cargoHolds;
    private final int weaponSlots;
    private final int shieldSlots;
    private final int gadgetSlots;
    private final int numMercenaries;
    private final int hullStrength; //1 weak, 2 avg, 3 strong
    private final int price;
    private final double fuelPrice;

    /**
     *  constructor to generate a ship type and all the attributes that come with the specific ship
     *
     * @param name the name of the the ship
     * @param maxDistance the maximum distance the ship can go on a full tank
     * @param cargoHolds the number of cargo slots the ship can hold
     * @param weaponSlots the number of weapon slots the ship can hold
     * @param shieldSlots the number of shield slots the ship comes with
     * @param gadgetSlots the number of gadget slots the ship can have
     * @param numMercenaries the number of mercenaries that the ship can hold
     * @param hullStrength the number that represents how many hits your ship can take
     */
    ShipType(String name, int maxDistance, int cargoHolds, int weaponSlots, int shieldSlots, int gadgetSlots, int numMercenaries, int hullStrength, double fuelPrice) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.cargoHolds = cargoHolds;
        this.weaponSlots = weaponSlots;
        this.shieldSlots = shieldSlots;
        this.gadgetSlots = gadgetSlots;
        this.numMercenaries = numMercenaries;
        this.hullStrength = hullStrength;
        this.price = this.calculatePrice();
        this.fuelPrice = fuelPrice;
    }

    /**
     * @return the price of the fuel for this ship
     */
    public double getFuelPrice() {
        return fuelPrice;
    }

    /**
     * method to calculate the the total price of a ship based on its attributes
     *
     * @return an int value that represents the total cost of a ship based on its attributes
     */
    private int calculatePrice(){
        int cost = 1000;
        cost += 100 * maxDistance;
        cost += 50 * cargoHolds;
        cost += 500 * weaponSlots;
        cost += 500 * gadgetSlots;
        cost += 500 * shieldSlots;
        cost += 1000 * numMercenaries;
        cost += 1500 * (hullStrength - 1);
        return cost;
    }

    /**
     * method to get the max distance the ship can travel at max fuel
     *
     * @return an int to represent the max distance the ship can travel at max fuel
     */
    public int getMaxDistance() {
        return maxDistance;
    }

    /**
     * method that returns the number of cargo slots the ship has
     *
     * @return an int to represent the number of cargo slots the ship has
     */
    public int getCargoHolds() {
        return cargoHolds;
    }

    /**
     * method that returns the number of weapon slots a ship has
     *
     * @return an int that represents the number of weapon slots a ship has
     */
    public int getWeaponSlots() {
        return weaponSlots;
    }

    /**
     * a method that returns the number of shield slots the ship has
     *
     * @return an int that represents the number of shield slots the ship has
     */
    public int getShieldSlots() {
        return shieldSlots;
    }

    /**
     * a method that returns the number of gadget slots the ship has
     *
     * @return in int that represents the number of gadget slots the ship has
     */
    public int getGadgetSlots() {
        return gadgetSlots;
    }

    /**
     * a method that returns the number of mercenaries the ship can hold
     *
     * @return an int that represents the number of mercs the ship can hold
     */
    public int getNumMercenaries() {
        return numMercenaries;
    }

    /**
     * a method that returns the hull strength of the ship
     *
     * @return an int the represents the hull strength of the ship
     */
    public int getHullStrength() {
        return hullStrength;
    }

    /**
     * a method that returns the total price of the ship based on its attributes
     *
     * @return an int value that represents the total price of the ship based on its attributes
     */
    public int getPrice() {
        return price;
    }

    /**
     *  a toString method that prints out all the attributes of the ship
     *
     * @return a String representation of all the information of the attributes of the ship
     */
    public String toString() {
        return "Name: " + name
                + "Distance: " + maxDistance + "\n"
                + "Cargo Slots: " + cargoHolds + "\n"
                + "Weapon Slots: " + weaponSlots + "\n"
                + "Shield Slots: " + shieldSlots + "\n"
                + "Gadget Slots: " + gadgetSlots + "\n"
                + "Number of Mercenaries: " + numMercenaries + "\n"
                + "Hull Strength: " + hullStrength + "\n"
                + "Price: " + price;
    }
}
