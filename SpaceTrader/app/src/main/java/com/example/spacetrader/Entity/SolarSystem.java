package com.example.spacetrader.Entity;

import java.util.Random;

public class SolarSystem {

    private String name;
    private Location location;
    private PoliticalSystem politicalSystem;
    private TechLevel techLevel;
    private Planet planet;
    private Resource resource;
    private SpacePort spacePort;
    private Random random;

    public SpacePort getSpacePort() {
        return spacePort;
    }

    public void setSpacePort(SpacePort spacePort) {
        this.spacePort = spacePort;
    }

    /**
     * public constructor to initialize a SolarSystem object that has a random location, tech level,
     * and political system.
     *
     * @param l of type Location that initializes the Location instance field to the given location
     * @param p of type PoliticalSystem that initializes the Political instance field to the given
     *          political system
     * @param t of type TechLevel that initializes a TechLevel instance field to the given tech level
     * @param name of type String that initializes the name of the Solar System
     */
    public SolarSystem(Location l, PoliticalSystem p, TechLevel t, String name) {
        random  = new Random();
        this.name = name;
        location = l;
        politicalSystem = p;
        techLevel = t;
        resource = Resource.values()[random.nextInt(Resource.values().length)];
        this.planet = new Planet(name);
        this.spacePort = new SpacePort(techLevel, resource);
    }

    /**
     * constructor that takes in a randomized location
     *
     * @param l of type Location that is the SolarSystems random location.
     */
    public SolarSystem(Location l) {
        this(l, PoliticalSystem.getRandomPoliticalSystem(), TechLevel.getRandomTechLevel(), "");
    }

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
        return "\nName: " + name + "\nLocation: " + location.toString() + "\nTech Level: " +
                techLevel + "\nPolitical System: " + politicalSystem + "\n" + "Resource: " + resource + "\n"
                + "------------------------------------------------";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PoliticalSystem getPoliticalSystem() {
        return politicalSystem;
    }

    public void setPoliticalSystem(PoliticalSystem politicalSystem) {
        this.politicalSystem = politicalSystem;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Resource getResource() {
        return resource;
    }
}
