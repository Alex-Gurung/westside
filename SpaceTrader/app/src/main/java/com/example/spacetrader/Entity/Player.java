package com.example.spacetrader.Entity;

public class Player {

    private int pilotSkillPoints;
    private int fighterSkillPoints;
    private int engineerSkillPoints;
    private int traderSkillPoints;

    private String name;

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
