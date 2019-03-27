package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 * an abstract class that represents the basic attributes of any given character in the Space Trader
 * game
 */
abstract class Character implements Serializable {

    String name;
    SolarSystem currentSolarSystem;
    Ship ship;
    double credits = 1000.00;

    /**
     * constructor that instantiates a Character with a name and a ship
     *
     * @param name
     * @param ship
     */
    Character(String name, Ship ship) {
        this.name = name;
        this.ship = ship;
    }

    private double getMaxPriceOfReFuel() {
        return ((1.0 - ship.getFuel()) * (ship.getShiptype().getFuelPrice()) * (double)(ship.getShiptype().getMaxDistance()));
    }

    /**
     * returns if the player has enough credits to fully refuel
     * @return if the player can fully refuel
     */
    public boolean canRefuelMax() {
        return credits >= getMaxPriceOfReFuel();
    }

    /**
     * refules the ship to the max
     */
    public void refuelMax() {
        credits -= getMaxPriceOfReFuel();
        credits = Math.round(credits * 100.0) / 100.0;
        ship.refuel(1.00 - ship.getFuel());
    }

    /**
     * sees if the layer can refuel using x number credits
     * @param creditsAdded the number of credits of fuel wanting to be added
     * @return whether or not the fuel amount from above can be added
     */
    public boolean refuelByCredits(double creditsAdded) {
        if(creditsAdded > credits) return false;
        double fuelPercent = creditsAdded / (ship.getShiptype().getFuelPrice() * ship.getShiptype().getMaxDistance());
        if(fuelPercent > 1 - ship.getFuel()) {
            refuelMax();
        } else {
            ship.refuel(fuelPercent);
            credits -= creditsAdded;
        }
        return true;
    }
    /**
     * getter method that returns the name of the Character
     *
     * @return a String representation of the name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * setter method that sets the name of the Character
     *
     * @param name of type String that represents the Character's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method to return the current Solar System
     *
     * @return the current Solar System
     */
    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    /**
     * setter method to set the current solar system to the given solar system
     *
     * @param currentSolarSystem of type Solar System to be set to the current Solar System
     */
    public void setCurrentSolarSystem(SolarSystem currentSolarSystem) {
        this.currentSolarSystem = currentSolarSystem;
    }

    /**
     * getter method to return the current ship of the Character
     *
     * @return the current ship of the Character
     */
    public Ship getMyShip() {
        return ship;
    }

    /**
     * setter method to set the current ship of the Character to the given ship
     *
     * @param myShip of type Ship to be set to the Character's current ship
     */
    public void setMyShip(Ship myShip) {
        this.ship = myShip;
    }

    /**
     * getter method to return the current number of credits the Character has
     *
     * @return a double representation of the number of credits the Character has
     */
    public double getCredits() {
        return credits;
    }

    /**
     * setter method to set the Character's current number of credits to the given credits
     *
     * @param credits of type double to represent the Character's new number of credits
     */
    public void setCredits(double credits) {
        this.credits = credits;
    }
}
