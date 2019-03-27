package com.example.spacetrader.Entity;

import java.io.Serializable;

/**
 * a class that represents the current player playing the game and is inclusive of all attributes of
 * the player, including the ability to trade
 */
public class Player extends Character implements TraderCapability, Serializable {

    private final int pilotSkillPoints;
    private final int fighterSkillPoints;
    private final int engineerSkillPoints;
    private final int traderSkillPoints;

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
    }

    public void travel(SolarSystem solarSystem) {
        double dist = this.currentSolarSystem.getDistance(solarSystem);
        this.ship.travel(dist);
        this.currentSolarSystem = solarSystem;
        Good[] goods = ship.getCargo();
        for(int i = 0; i < ship.getNumGoods(); i++) {
            this.setPrice(goods[i], this.currentSolarSystem);
        }
    }

    public boolean canTravel(SolarSystem solarSystem) {
        return this.currentSolarSystem.getDistance(solarSystem) <= this.getShipDistance();
    }

    private double getShipDistance() {
        return ship.distanceLeft();
    }
    /**
     * Overridden getter method (from its Trader Capability interface) that gets the price of the
     * good
     *
     * @param good of type Good that will return its price
     * @return a double representation of the price of the good
     */
    @Override
    public double getPrice(Good good) {
        return good.getPrice();
    }

    /**
     * setter method to set the price of the good based on the current Solar System's tech level
     * amnd resource availability
     *
     * @param good of type good whose price will be determined based on the current solar system's
     *             attributtes
     */
    public void setPrice(Good good) {
        setPrice(good, this.currentSolarSystem.getTechLevel(), this.currentSolarSystem.getResource());
    }

    /**
     * Overridden boolean method (from its Trader Capability interface) that returns a boolean value
     * of whether or not the player can sell their goods
     *
     * @param good of type Good that could or could not be sold depending on if the player has that
     *             good
     * @return a boolean representation of whether or not the player can sell the current good
     */
    @Override
    public boolean canSell(Good good) {
        return ship.hasGood(good);
    }

    /**
     * Overridden boolean method (from its Trader Capability interface) that returns a boolean value
     * of whether or not the player can buy any goods
     * @param good of type Good that could or could not be bought depending on if the player has
     *             enough credits
     * @return a boolean representation of whether or not the player can buy the current good
     */
    @Override
    public boolean canBuy(Good good) {
        return good.getPrice() <= credits && ship.hasCargoSpace();
    }

    /**
     * Overridden method (from its Trader Capability interface) that allows the player to buy goods
     *
     * @param g of type Good that is the good to be bought
     */
    @Override
    public void buy(Good g) {
        credits -= g.getPrice();
        ship.addCargo(g);
    }

    /**
     * Overridden method (from its Trader Capability interface) that allows the player to sell goods
     *
     * @param g of type Good that is the good to be sold
     */
    @Override
    public void sell(Good g) {
        credits += g.getPrice();
        ship.removeCargo(g);
    }

    /**
     * Overridden method (from its Trader Capability interface) that gets the array representation
     * of the player's cargo hold of Goods
     *
     * @return an array of Goods that the ship of the player contains
     */
    @Override
    public Good[] getCargo() {
        return ship.getCargo();
    }

    /**
     * getter method to return the current Solar System of the player
     *
     * @return the current Solar System of the player
     */
    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    /**
     * setter method for the player's current solar system. This is mainly used for traveling b/n
     * solar systems
     *
     * @param currentSolarSystem the Solar System object to become the player's new current Solar
     *                           System
     */
    public void setCurrentSolarSystem(SolarSystem currentSolarSystem) {
        this.currentSolarSystem = currentSolarSystem;
    }

    /**
     * getter method for the current Solar System's space port
     *
     * @return the current Solar System's space port
     */
    public SpacePort getSpacePort() {
        return this.currentSolarSystem.getSpacePort();
    }

    /**
     * Overridden toString method that returns the name, ship, skill point distribution, and credits
     * of the current player
     *
     * @return a String represntation of all the above attributes of the current player
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
        s += "I have " + getCredits() + " credits" + "\n";
        return s;
    }

    public double getFuel() {
        return this.ship.getFuel();
    }
}
