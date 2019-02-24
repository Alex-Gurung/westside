package com.example.spacetrader.Entity;

public abstract class Character {

    protected String name;
    protected SolarSystem currentSolarSystem;
    protected Ship ship;
    private double credits = 1000;

    public Character (String name, Ship ship) {
        this.name = name;
        this.ship = ship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    public void setCurrentSolarSystem(SolarSystem currentSolarSystem) {
        this.currentSolarSystem = currentSolarSystem;
    }

    public Ship getMyShip() {
        return ship;
    }

    public void setMyShip(Ship myShip) {
        this.ship = myShip;
    }


    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }
}
