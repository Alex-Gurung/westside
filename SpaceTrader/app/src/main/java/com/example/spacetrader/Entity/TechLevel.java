package com.example.spacetrader.Entity;

import java.util.Random;

/**
 * enum class that contains all possible tech levels and name attribute of each tech level
 */
public enum TechLevel {
    PREAGRICULTURAL("Pre-Agricultural"),
    AGRICULTURAL("Agricultural"),
    MEDIEVAL("Medieval"),
    RENAISSANCE("Renaissance"),
    EARLYINDUSTRIAL("Early-Industrial"),
    INDUSTRIAL("Industrial"),
    POSTINDUSTRIAL("Post-Industrial"),
    HITECH("Hi-Tech");

    private String name;

    /**
     * constructor that instantiates a tech level enum and gives the tech level a name
     *
     * @param name of type String that represents the name of the current tech level
     */
    TechLevel(String name) {
        this.name = name;
    }

    /**
     * getter method to return a random Tech Level to be assigned to a Solar System for the entirety
     *
     * @return the randomized tech level of the Solar System
     */
    public static TechLevel getRandomTechLevel() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    @Override
    public String toString() {
        return name;
    }
}
