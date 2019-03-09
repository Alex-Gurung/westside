package com.example.spacetrader.Entity;

public class ShipYard {

    public static boolean refuelMax(Character character) {
        if(character.canRefuelMax()) {
            character.refuelMax();
            return true;
        }
        return false;
    }

    public static boolean refuelByCredits(Character character, double credits) {
        return character.refuelByCredits(credits);
    }

}
