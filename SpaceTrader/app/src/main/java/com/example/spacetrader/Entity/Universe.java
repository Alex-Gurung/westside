package com.example.spacetrader.Entity;

import java.util.HashSet;
import java.util.Set;

public class Universe {
    private HashSet<SolarSystem> solarSystems;

    public Universe(int numSolarSystems) {
        Set<Location> locations = new HashSet<>();
        for (int i = 0; i < numSolarSystems; i++) {
            if(!locations.add(Location.getRandomLocation())) {
                i--;
            }
        }
        for (Location location: locations) {
            solarSystems.add(new SolarSystem(location));
        }
    }
    public Universe() {
        this(100);
    }
    public String toString() {
        String str = "";
        for (SolarSystem system: solarSystems) {
            str += system.toString();
        }
        return str;
    }
}
