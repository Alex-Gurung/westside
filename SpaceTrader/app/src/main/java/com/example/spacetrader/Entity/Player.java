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



}
