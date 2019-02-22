package com.example.spacetrader.Entity;

import java.util.Random;

public enum TechLevel {
    PREAGRICULTURAL("Pre-Agricultural"),
    AGRICULTURAL("Agricultural"),
    MEDIEVAL("Medieval"),
    RENAISSANCE("Renaissance"),
    EARLYINDUSTRIAL("Early-Industrial"),
    INDUSTRIAL("Industrial"),
    POSTINDUSTRIAL("Post-Industrial"),
    HITECH("Hi-Tech");

    private String name;

    TechLevel(String name) {
        this.name = name;
    }

    public static TechLevel getRandomTechLevel() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }
}
