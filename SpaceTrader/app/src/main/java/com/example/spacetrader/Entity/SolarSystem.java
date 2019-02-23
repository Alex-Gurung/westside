package com.example.spacetrader.Entity;

public class SolarSystem {

    private String name;
    private Location location;
    PoliticalSystem politicalSystem;
    TechLevel techLevel;
    Planet planet;

    /**
     * public constructor to initialize a SolarSystem object that has a random location, tech level,
     * and political system.
     *
     * @param l of type Location that initializes the Location instance field to the given location
     * @param p of type PoliticalSystem that initializes the Political instance field to the given
     *          political system
     * @param t of type TechLevel that initializes a TechLevel instance field to the given tech level
     * @param name the name of the SolarSystem used to name the planet
     */
    public SolarSystem(Location l, PoliticalSystem p, TechLevel t, String name) {
        this.name = name;
        location = l;
        politicalSystem = p;
        techLevel = t;
        this.planet = new Planet(name);
    }

    /**
     * constructor that takes in a randomized location
     *
     * @param l of type Location that is the SolarSystems random location.
     */
    public SolarSystem(Location l) {
        this(l, PoliticalSystem.getRandomPoliticalSystem(), TechLevel.getRandomTechLevel(), "");
    }

    /**
     * constructor that takes in a randomized location and unique name
     *
     * @param l of type Location that is the SolarSystems random location
     * @param name the name of the SolarSystem
     */
    public SolarSystem(Location l, String name) {
        this(l, PoliticalSystem.getRandomPoliticalSystem(), TechLevel.getRandomTechLevel(), name);
    }


    /**
     * hashCode methods that gets the hashCode of the location instance field
     *
     * @return an int value that represent the hashCode of the location instance field
     */
    @Override
    public int hashCode() {
        return this.location.hashCode();
    }

    /**
     * equals method that checks to see if the locations between {@code this} and the passed in object are
     * equal in value
     *
     * @param obj the object to be compared to {@code this}
     * @return a boolean value that represents wherter {@code this} and obj have the same locations
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof SolarSystem)) return false;
        SolarSystem o = (SolarSystem) obj;
        return (this.location.equals(o.location));
    }

    /**
     * toString method that overrides Object's toString method that returns a String representation
     * of the current Solar System's location
     *
     * @return a String representation of the current Solar System's location
     */
    @Override
    public String toString() {
        return "Name: " + name + " Location: " + location.toString() + "\n";
    }
}
