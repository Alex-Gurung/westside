package com.example.spacetrader.Entity;

public class SolarSystem {

    private String name;
    private Location location;
    PoliticalSystem politicalSystem;
    TechLevel techLevel;
    Planet planet;


    public SolarSystem(Location l, PoliticalSystem p, TechLevel t, String name) {
        this.name = name;
        location = l;
        politicalSystem = p;
        techLevel = t;
        this.planet = new Planet(name);
    }

    public SolarSystem(Location l) {
        this(l, PoliticalSystem.getRandomPoliticalSystem(), TechLevel.getRandomTechLevel(), "");
    }
    public SolarSystem(Location l, String name) {
        this(l, PoliticalSystem.getRandomPoliticalSystem(), TechLevel.getRandomTechLevel(), name);
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
        return "Name: " + name + " Location: " + location.toString() + "\n";
    }
}
