package com.example.spacetrader.Entity;

public class Player {

    private int pilotSkillPoints = 4;
    private int fighterSkillPoints = 4;
    private int engineerSkillPoints = 4;
    private int traderSkillPoints = 4;

    private String name = "Bobert Waters";

    private Ship ship = new Ship(ShipType.GNAT);

    private int credits = 1000;

    /**
     * constructor that instantiates a Player object with the given attributes
     *
     * @param pilotSkillPoints an int that represents the number of skill points the player added
     *                         towards the pilot skill set
     * @param fighterSkillPoints an int that represents the number of skill points the player added
     *                           towards the fighter skill set
     * @param engineerSkillPoints an int that represents the number of skill points the player added
     *                             towards the engineer skill set
     * @param traderSkillPoints an int tht represents the number of skill points the player added
     *                          towards the engineer skill set
     * @param name a String that represents the name that the player passed in when creating their
     *             player character
     */
    public Player(int pilotSkillPoints, int fighterSkillPoints, int engineerSkillPoints, int traderSkillPoints, String name) {
        this.engineerSkillPoints = engineerSkillPoints;
        this.fighterSkillPoints = fighterSkillPoints;
        this.pilotSkillPoints = pilotSkillPoints;
        this.traderSkillPoints = traderSkillPoints;
        this.name = name;
    }

    /**
     *  toString method that overrides the toString method in Object that returns the values of
     *  the attributes of the player that they entered in for themselves
     *
     * @return the String representation of the attributes of the players
     */
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
