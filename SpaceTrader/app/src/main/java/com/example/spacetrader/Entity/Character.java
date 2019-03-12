package com.example.spacetrader.Entity;

/**
 * an abstract class that represents the basic attributes of any given character in the Space Trader
 * game
 */
public abstract class Character {

    protected String name;
    protected SolarSystem currentSolarSystem;
    protected Ship ship;
    protected double credits = 1000.00;

    /**
     * constructor that instantiates a Character with a name and a ship
     *
     * @param name
     * @param ship
     */
    public Character (String name, Ship ship) {
        this.name = name;
        this.ship = ship;
    }

    public double getPriceOfReFuel() {
        return ship.getFuel() * ship.getShiptype().getFuelPrice() * ship.getShiptype().getMaxDistance();
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
