package com.example.spacetrader.Entity;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
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
