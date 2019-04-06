package com.example.spacetrader.Entity;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Random;

/**
 * a class that represents a given solar system in the universe. The class includes the name,
 * location, political system, tech level, resources of the current Solar System, as well as the
 * Planets it has, and the Spaceport it has
 */
public class SolarSystem implements Serializable {

    private String name;
    private final Location location;
    private final PoliticalSystem politicalSystem;
    private final TechLevel techLevel;
    private Resource resource;
    private SpacePort spacePort;
    private static final ShipYard shipYard = new ShipYard();

    /**
     * constructor that takes in a randomized location
     *
     * @param l of type Location that is the SolarSystems random location.
     */
    public SolarSystem(Location l) {
        this(l, PoliticalSystem.getRandomPoliticalSystem(), TechLevel.getRandomTechLevel(), "");
    }

    /**
     * constructor that takes in a randomized location, and the name of the Solar System
     *
     * @param l f type Location that is the SolarSystems random location.
     * @param name of type String that represents the name of the current Solar System
     */
    public SolarSystem(Location l, String name) {
        this(l, PoliticalSystem.getRandomPoliticalSystem(), TechLevel.getRandomTechLevel(), name);
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
        Random random = new Random();
        this.name = name;
        location = l;
        politicalSystem = p;
        techLevel = t;
        resource = Resource.values()[random.nextInt(Resource.values().length - 1) + 1]; // will never be never
        this.spacePort = new SpacePort(techLevel, resource);
    }


    public void setResource(Resource r) {
        this.resource = r;
    }
    /**
     * getter method that returns the space port of the current Solar System
     *
     * @return a SpacePort representation of the current Solar System's space port
     */
    public SpacePort getSpacePort() {
        return spacePort;
    }

    /**=
     * @return the shipyard for this solar system
     */
    public ShipYard getShipYard() {
        return shipYard;
    }

    /**
     * gets distance between this and another solar system
     * @param solarSystem the second solar system whose distance you are measuring
     * @return the distance between the two solar systems
     */
    public double getDistance(SolarSystem solarSystem) {
        return this.location.getDistance(solarSystem.getLocation());
    }
    /**
     * setter method that sets the current Solar System's space port
     *
     * @param spacePort of type SPacePort to become the current Solar System's space port
     */
    public void setSpacePort(SpacePort spacePort) {
        this.spacePort = spacePort;
    }

    /**
     * getter method to return the name of the current Solar System
     *
     * @return a String representation of the name of the current Solar System
     */
    public String getName() {
        return name;
    }

    /**
     * setter method to set the name of the Current Solar System
     *
     * @param name of type String that will become the current Solar System's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter that returns the current Solar System's location
     *
     * @return the current Location of teh current Solar System
     */
    public Location getLocation() {
        return location;
    }

//    /**
//     * a setter method to set the current Solar System's name
//     *
//     * @param location of type Location that is to become the current Solar System's Location
//     */
//    public void setLocation(Location location) {
//        this.location = location;
//    }

//    /**
//     * a getter method that returns the current Solar System's political system
//     *
//     * @return the current Solar System's political system
//     */
//    public PoliticalSystem getPoliticalSystem() {
//        return politicalSystem;
//    }
//
//    /**
//     * a setter method to set the current Solar System's political system (mainly used in the
//     * instantiation of the Universe)
//     *
//     * @param politicalSystem of type PoliticalSystem that that is to become the current Solar
//     *                        System's political system
//     */
//    public void setPoliticalSystem(PoliticalSystem politicalSystem) {
//        this.politicalSystem = politicalSystem;
//    }

    /**
     * getter method to return the current Solar System's tech level
     *
     * @return the current Solar System's tech level
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

//    /**
//     * setter method to set the current Solar Systems tech level (mainly used in the instantiation
//     * of the Universe)
//     *
//     * @param techLevel of type TechLevel that is to become the current Solar System's tech level
//     */
//    public void setTechLevel(TechLevel techLevel) {
//        this.techLevel = techLevel;
//    }
//


    /**
     * getter method to return the Resource type of the current Solar System
     *
     * @return the Resource that exists in the current Solar System
     */
    public Resource getResource() {
        return resource;
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
     * @return a boolean value that represents whether {@code this} and obj have the same locations
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
     * of the current Solar System's location, name, tech level, political system, and resources
     *  available
     *
     * @return a String representation of the current Solar System's location
     */
    @NonNull
    @Override
    public String toString() {
        return "\nName: " + name + "\nLocation: " + location.toString() + "\nTech Level: " +
                techLevel + "\nPolitical System: " + politicalSystem + "\n" + "Resource: " + resource + "\n"
                + "------------------------------------------------";
    }
}