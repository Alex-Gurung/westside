package com.example.spacetrader.Entity;

public class SolarSystem {

    Location location;
    PoliticalSystem politicalSystem;
    TechLevel techLevel;


    public SolarSystem(Location l, PoliticalSystem p, TechLevel t) {
        location = l;
        politicalSystem = p;
        techLevel = t;
    }

    public SolarSystem(Location l) {
        this(l, PoliticalSystem.getRandomPoliticalSystem(), TechLevel.getRandomTechLevel());
    }
}
