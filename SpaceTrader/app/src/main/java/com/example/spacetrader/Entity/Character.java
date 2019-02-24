package com.example.spacetrader.Entity;

public abstract class Character {

    private String name;
    private SolarSystem currentSolarSystem;
    private Ship myShip;

    public Character (String name, SolarSystem solarSystem, Ship ship) {
        this.name = name;
        currentSolarSystem = solarSystem;
        myShip = ship;
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
        return myShip;
    }

    public void setMyShip(Ship myShip) {
        this.myShip = myShip;
    }


}
