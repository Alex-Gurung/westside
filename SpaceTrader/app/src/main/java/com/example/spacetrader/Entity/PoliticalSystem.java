package com.example.spacetrader.Entity;

import android.support.annotation.NonNull;

import java.util.Random;

/**
 * enum class that represents all possible political systems that a solar system can have
 */
public enum PoliticalSystem {
    ANARCHY("Anarchy"),
    CAPITALISTSTATE("Capitalist State"),
    COMMUNISTSTATE("Communist State"),
    CONFEDERACY("Confederacy"),
    CORPORATESTATE("Corporate State"),
    CYBERNETICSTATE("Cybernetic State"),
    DEMOCRACY("Democracy"),
    DICTATORSHIP("Dictatorship"),
    FASCISTSTATE("Fascist State"),
    FEUDALSTATE("Feudal State"),
    MILITARYSTATE("Military State"),
    MONARCHY("Monarchy"),
    PACIFISTSTATE("Pacifist State"),
    SOCIALISTSTATE("Socialist State"),
    STATEOFSATORI("State of Satori"),
    TECHNOCRACY("Technocracy"),
    THEOCRACY("Theocracy");

    private final String name;

    /**
     * constructor to instantiate the name of a single political system
     *
     * @param name of type String to represent the name of a single political system
     */
    PoliticalSystem(String name) {
        this.name = name;
    }

    /**
     * getter method that returns a random political system to be assigned to a random Solar System.
     * This should be consistent for the entirety of the existence of the current game
     *
     * @return a random political system
     */
    public static PoliticalSystem getRandomPoliticalSystem() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
