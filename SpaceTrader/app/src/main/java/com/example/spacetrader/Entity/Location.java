package com.example.spacetrader.Entity;

import java.util.Random;

public class Location {

    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location getRandomLocation() {
        Random r = new Random();
        return new Location(r.nextInt(100), r.nextInt(100));
    }

    @Override
    public int hashCode() {
        return (x * 100 + y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Location)) return false;
        Location o = (Location) obj;
        return (this.x == o.x && this.y == o.y);
    }
}
