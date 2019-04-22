package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 * wormhole class that has two different solar systems that a player can travel from/to
 */
public class Wormhole implements Serializable {
    private SolarSystem A;
    private SolarSystem B;

    /**
     * constructor that instantiates two SolarSystem objects that have wormholes on them
     *
     * @param A a Solar System object with a wormhole
     * @param B a Solar System object with a wormhole
     */
    public Wormhole(SolarSystem A, SolarSystem B) {
        this.A = A;
        this.B = B;
    }

    /**
     * method that ensures that the passed in SolarSystem has a wormhole
     *
     * @param solarSystem of type SolarSystem that is most likely the end destination for the user
     * @return a boolean that represents whether the desired SolarSystem has a wormhole
     */
    public boolean checkEndPoint (SolarSystem solarSystem) {
        return solarSystem.equals(A) || solarSystem.equals(B);
    }

    /**
     * method that returns other endpoint of travel. Can be the starting point of the destination
     *
     * @param solarSystem of type SolarSystem that is to be the current SolarSystem the Player is on
     * @return a SolarSystem object that represents the "other" SolarSystem. Can be the starting
     * point or the destination
     */
    public SolarSystem getOtherEndpoint (SolarSystem solarSystem) {
        if (solarSystem.equals(A)){
            return B;
        } else if (solarSystem.equals(B)) {
            return A;
        } else {
            return null;
        }
    }
}
