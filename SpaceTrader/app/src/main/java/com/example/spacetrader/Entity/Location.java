package com.example.spacetrader.Entity;

import java.util.Random;

public class Location {

    private int x;
    private int y;
    private static int bound = 90;
    private static Random r = new Random();


    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Location getRandomLocation() {
        return new Location((r.nextInt(bound) + 5), (r.nextInt(bound) + 5));
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
    public String toString() {
        return "X: " + x + " Y: " + y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
