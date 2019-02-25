package com.example.spacetrader.Entity;

import java.util.Arrays;

public class Player extends Character implements TraderCapability{

    private int pilotSkillPoints;
    private int fighterSkillPoints;
    private int engineerSkillPoints;
    private int traderSkillPoints;
    private double credits;

//    private String name = "Bobert Waters";
//
//    private Ship ship = new Ship(ShipType.GNAT);

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
        super(name, new Ship(ShipType.GNAT));
        this.engineerSkillPoints = engineerSkillPoints;
        this.fighterSkillPoints = fighterSkillPoints;
        this.pilotSkillPoints = pilotSkillPoints;
        this.traderSkillPoints = traderSkillPoints;
        credits = 1000.00;

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
        s += "I have " + getCredits() + " credits" + "\n";
        return s;
    }

    @Override
    public double getPrice(Good good) {
        return good.getPrice();
    }

    @Override
    public void setPrice(Good good) {
        GoodType gt = good.getGoodType();
        int min = gt.getMinPrice();
        int max = gt.getMaxPrice();
        boolean isNaturalGood = gt.getIsNaturalResource();
        SolarSystem ss = this.currentSolarSystem;
        TechLevel tl = ss.getTechLevel();

        int tlordinal = tl.ordinal();

        if(!isNaturalGood) {
            tlordinal = 8 - tlordinal;
        }

        double add = ((max - min) * (double)tlordinal)/8;
        good.setPrice(min + add);
    }

    @Override
    public boolean canSell(Good good) {
        return ship.hasGood(good);
    }

    @Override
    public boolean canBuy(Good good) {
        return good.getPrice() <= credits && ship.hasCargoSpace();
    }

    @Override
    public void buy(Good g) {
        credits -= g.getPrice();
        ship.addCargo(g);
    }

    @Override
    public void sell(Good g) {
        credits += g.getPrice();
        ship.removeCargo(g);
    }


}
