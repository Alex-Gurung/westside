package com.example.spacetrader.Entity;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * a class that represents the location of the current solar system
 */
public class Location implements Serializable {

    private final int x;
    private final int y;
//    private static final int bound = 20;
//    private static final Random r = new Random();


    /**
     * constructor that instantiates the x and y coordinates of the current solar system
     *
     * @param x of type int that represents the location of the current solar system on the x axis
     * @param y of type int that represents the location of the current solar system on the y axis
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * method that gets the distance using the pythagorean theorem
     * @param l the location that you need to get the distance of
     * @return the distance
     */
    public double getDistance(Location l) {
        return Math.hypot(x-l.x, y-l.y);
    }

//    /**
//     * static getter method that returns a random x-y location for the current solar system
//     * (upon) creation of the universe for the current game
//     *
//     * @return a randomized location of the current solar system
//     */
//    public static Location getRandomLocation() {
//        return new Location((r.nextInt(bound) + 5), (r.nextInt(bound) + 5));
//    }

    /**
     * Overridden hashCode method to give the current Location object a special hashCode
     *
     * @return an int representation of the current Location's hashCode
     */
    @Override
    public int hashCode() {
        return ((x * 100) + y);
    }

    /**
     * overridden equals method that determines if two Objects have the same
     * coordinates
     *
     * @param obj of type Object to be compared to the current Location object
     * @return a boolean representation of whether the two Objects have the same coordinates
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Location)) return false;
        Location o = (Location) obj;
        return ((this.x == o.x) && (this.y == o.y));

    }

    /**
     * Overridden toString method to return the x and y coordinates of the current Solar System
     *
     * @return a String representation of the current Solar System's x and y coordinates
     */
    @NonNull
    @Override
    public String toString() {
        return "X: " + x + " Y: " + y;
    }

    /**
     * getter method for the x coordinate of the current Solar System
     *
     * @return an int representation of the x coordinate of the current Solar System
     */
    public int getX() {
        return x;
    }

//    /**
//     * setter method to set the x coordinate of the current Solar Systems
//     *
//     * @param x of type int to become the current Solar System's new x coordinate
//     */
//    public void setX(int x) {
//        this.x = x;
//    }

    /**
     * getter method for the y coordinate of the current Solar System
     *
     * @return an int representation of the y coordinate of the current Solar System
     */
    public int getY() {
        return y;
    }

//    /**
//     * setter method to set the y coordinate of the current Solar Systems
//     *
//     * @param y of type int to become the current Solar System's new y coordinate
//     */
//    public void setY(int y) {
//        this.y = y;
//    }
}
