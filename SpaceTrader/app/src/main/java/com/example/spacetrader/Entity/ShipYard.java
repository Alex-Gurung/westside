package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 * Shipyard class, does some things to help the player out (mostly just refuel)
 */
public class ShipYard implements Serializable {

    /**
     * Refuels the ship to the maximum amount allowed
     * @param character is the character whose ship is being refuelled fully
     * @return whether or not the ship was able to refuel fully
     */
    public static boolean refuelMax(Character character) {
        if(character.canRefuelMax()) {
            character.refuelMax();
            return true;
        }
        return false;
    }

    /**
     * Refuels the ship based on how many credits you pay
     * @param character the character whose ship is being refuelled
     * @param credits the aount of credits of fuel the caharcter is buying
     * @return whether or not the transaction was successful
     */
    public static boolean refuelByCredits(Character character, double credits) {
        return character.refuelByCredits(credits);
    }
}
