package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 * a class that represents a planet in a given solar system
 */
public class Planet implements Serializable {

    public String name;

    /**
     * constructor that instantiates a planet with a name for the planet
     *
     * @param name of type String to be set to the current Planet's name
     */
    public Planet(String name) {
        this.name = name;
    }

}
