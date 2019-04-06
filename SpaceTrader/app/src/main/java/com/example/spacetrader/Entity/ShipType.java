package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 * an enum class that contains each ship type, as well as the attributes and methods for each ship
 * type
 */
public enum ShipType implements Serializable {
    //name, max distance, cargo holds, fuel price
    FLEA("Flea", 20, 4, 10),
    GNAT("Gnat", 14, 15, 11),
    FIREFLY("Firefly",17, 20, 11),
    MOSQUITO("Mosquito", 13, 15, 12),
    BUMBLEBEE("Bumblebee", 15, 20, 12),
    BEETLE("Beetle", 14, 50, 13),
    HORNET("Hornet", 16, 20, 13),
    GRASSHOPPER("Grasshopper", 15, 30, 14),
    TERMITE("Termite", 13, 60, 15),
    WASP("Wasp", 14, 35, 15);
    private final String name;
    private final int maxDistance;
    private final int cargoHolds;
    private final int price;
    private final double fuelPrice;

    /**
     *  constructor to generate a ship type and all the attributes that come with the specific ship
     *
     * @param name the name of the the ship
     * @param maxDistance the maximum distance the ship can go on a full tank
     * @param cargoHolds the number of cargo slots the ship can hold
     */
    ShipType(String name, int maxDistance, int cargoHolds, double fuelPrice) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.cargoHolds = cargoHolds;
        this.price = this.calculatePrice();
        this.fuelPrice = fuelPrice;
    }

    /**
     * @return the price of the fuel for this ship     */
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
        cost += fuelPrice * maxDistance;
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
     * getter for the price of the ship type
     * @return the price of the ship type
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
                + "Price: " + price;
    }
}
