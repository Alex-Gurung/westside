package com.example.spacetrader.Entity;

public class Player {

    private int pilotSkillPoints = 4;
    private int fighterSkillPoints = 4;
    private int engineerSkillPoints = 4;
    private int traderSkillPoints = 4;

    private String name = "Bobert Waters";

    private Ship ship = new Ship(ShipType.GNAT);

    private int credits = 1000;

    public Player(int pilotSkillPoints, int fighterSkillPoints, int engineerSkillPoints, int traderSkillPoints, String name) {
        this.engineerSkillPoints = engineerSkillPoints;
        this.fighterSkillPoints = fighterSkillPoints;
        this.pilotSkillPoints = pilotSkillPoints;
        this.traderSkillPoints = traderSkillPoints;
        this.name = name;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Hello, my name is " + name + "\n";
        s += "I own a ship of model " + ship.toString() + "\n";
        s += "I have " + fighterSkillPoints + " fighter skill points" + "\n";
        s += "I have " + traderSkillPoints + " trader skill points" + "\n";
        s += "I have " + engineerSkillPoints + " engineer skill points" + "\n";
        s += "I have " + pilotSkillPoints + " pilot skill points" + "\n";
        s += "I have " + credits + " credits" + "\n";
        return s;
    }



}
