package com.example.spacetrader.Model;

import com.example.spacetrader.Entity.Location;
import com.example.spacetrader.Entity.SolarSystem;

import java.util.HashSet;
import java.util.Set;

public class Universe {
    private HashSet<SolarSystem> solarSystems;

    public Universe(int numSolarSystems) {
        Set<Location> locations = new HashSet<>();
        int i = 0;
        while (i < numSolarSystems) {
            boolean added = locations.add(Location.getRandomLocation());
            if (added) {
                i++;
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
