package com.example.spacetrader.Entity;

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
}
