package com.example.spacetrader.Entity;

public enum PoliticalSystem {
    ANARCHY("Anarchy"),
    CAPITALISTSTATE("Capitalist State"),
    COMMUNISTSTATE("Communist State"),
    CONFEDERACY("COnfederacy"),
    CORPORATESTATE("Corporate State"),
    CYBERNETICSTATE("Cybernetic State"),
    DEMOCRACY("Democracy"),
    DICTATORSHIP("Dictatorship"),
    FACISTSTATE("Facist State"),
    FEUDALSTATE("Feudal Stae"),
    MILITARYSTATE("Military State"),
    MONARCHY("Monarchy"),
    PACIFISTSTATE("Pacifist State"),
    SOCIALISTSTATE("Socialist State"),
    STATEOFSATORI("State of Satori"),
    TECHNOCRACY("Technocracy"),
    THEOCRACY("Theocracy");

    private String name;

    PoliticalSystem(String name) {
        this.name = name;
    }
}
