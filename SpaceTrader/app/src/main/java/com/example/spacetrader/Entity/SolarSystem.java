package com.example.spacetrader.Entity;

public class SolarSystem {

    private Location location;
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



    @Override
    public int hashCode() {
        return this.location.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof SolarSystem)) return false;
        SolarSystem o = (SolarSystem) obj;
        return (this.location.equals(o.location));
    }
    public String toString() {
        return location.toString();
    }
}
