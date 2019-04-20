package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 * wormhole class that has two different solar systems that a player can travel from/to
 */
class Wormhole implements Serializable {
    private SolarSystem A;
    private SolarSystem B;

    public Wormhole(SolarSystem A, SolarSystem B) {
        this.A = A;
        this.B = B;
    }

    public boolean checkEndPoint (SolarSystem solarSystem) {
        return solarSystem.equals(A) || solarSystem.equals(B);
    }

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
