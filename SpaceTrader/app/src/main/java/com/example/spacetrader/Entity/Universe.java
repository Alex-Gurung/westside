package com.example.spacetrader.Entity;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.*;

/**
 * A Universe class that contains a HashSet of SolarSystems. The Universe is generated when the game
 * begins and will contain all Solar Systems that are initialized.
 */
class Universe implements Serializable {
    private final HashSet<SolarSystem> solarSystems;
    private final HashSet<Wormhole> wormholes;
    /**
     * @return a set of solar systems in this universe
     */
    public HashSet<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    public HashSet<Wormhole> getWormholes() {
        return wormholes;
    }
    //    /**
//     * sets the solar systems in this universe
//     * @param solarSystems the set of solar systems in this universe
//     */
//    public void setSolarSystems(HashSet<SolarSystem> solarSystems) {
//        this.solarSystems = solarSystems;
//    }

    /**
     * public constructor that initializes the Universe object with a set number of Solar Systems
     *
     * @param numSolarSystems of type int to represent the number of Solar System the Universe will
     *                        hold
     */
    public Universe(int numSolarSystems) {
        List<Location> allCombos = new ArrayList<>();
        for(int i = 2; i <= 33; i++) {
            for (int j = 2; j <= 33; j++) {
                allCombos.add(new Location(i,j));
            }
        }
        Collections.shuffle(allCombos);
        List<Location> locations = allCombos.subList(0,numSolarSystems);
        solarSystems = new HashSet<>();

        int solarSystemCount = 0;
        for (Location location: locations) {
            solarSystems.add(new SolarSystem(location, Names.solarSystemNames[solarSystemCount]));
            solarSystemCount++;
        }

        wormholes = new HashSet<>();
        Iterator<SolarSystem> solarSystemIterator = solarSystems.iterator();
        for(int i = 0; i < numSolarSystems/20; i++) {
            wormholes.add(new Wormhole(solarSystemIterator.next(), solarSystemIterator.next()));
        }
    }

//    /**
//     * default constructor to initialize a Universe object with 100 Solar Systems
//     */
//    public Universe() {
//        this(100);
//    }

    /**
     * toString method that overrides Object's toString method that returns a list of each Solar
     * System's name and location
     *
     * @return a String representation of each Solar System's name and location in the Universe
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (SolarSystem system: solarSystems) {
            str.append(system.toString());
        }
        return str.toString();
    }

    /**
     * method that gets a random solar system
     * @return the solar system that was randomly chosen
     */
    public SolarSystem getRandomSolarSystem() {
        return (SolarSystem)solarSystems.toArray()[0];
    }
}
