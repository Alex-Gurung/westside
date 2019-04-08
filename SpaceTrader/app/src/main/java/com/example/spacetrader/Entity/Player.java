package com.example.spacetrader.Entity;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * a class that represents the current player playing the game and is inclusive of all attributes of
 * the player, including the ability to trade
 */
public class Player extends Character implements TraderCapability {

    private final int pilotSkillPoints;
    private final int fighterSkillPoints;
    private final int engineerSkillPoints;
    private final int traderSkillPoints;


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
     * @param traderSkillPoints an int that represents the number of skill points the player added
     *                          towards the engineer skill set
     * @param name a String that represents the name that the player passed in when creating their
     *             player character
     * @param ship the ship the player starts with
     * @param solarSystem the solarSystem the player starts with
     * @param credits an int that represents the number of credits the player is initialized with
     */
    public Player(int pilotSkillPoints, int fighterSkillPoints, int engineerSkillPoints,
                  int traderSkillPoints, String name, Ship ship, SolarSystem solarSystem,
                  double credits) {
        super(name, ship, solarSystem, credits);
        this.engineerSkillPoints = engineerSkillPoints;
        this.fighterSkillPoints = fighterSkillPoints;
        this.pilotSkillPoints = pilotSkillPoints;
        this.traderSkillPoints = traderSkillPoints;
    }

    /**
     * moves the player to a new solar system and changes the fuel and
     * price of goods on the ship accordingly
     * @param solarSystem the new solar system the player is on
     */
    public void travel(SolarSystem solarSystem) {
        double dist = this.currentSolarSystem.getDistance(solarSystem);
        this.ship.travel(dist);
        this.setCurrentSolarSystem(solarSystem);
        Good[] goods = ship.getCargo();
        for(int i = 0; i < ship.getNumGoods(); i++) {
            this.setPrice(goods[i], this.currentSolarSystem);
        }
    }

    /**
     * @param solarSystem the solar system the player wants to go to
     * @return whether or not the player can go there
     */
    public boolean canTravel(SolarSystem solarSystem) {
        return this.currentSolarSystem.getDistance(solarSystem) <= this.getShipDistance();
    }

    /**
     * getter for the distance the ship can still travel
     * @return the distance the ship can travel
     */
    public double getShipDistance() {
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
     * and resource availability
     *
     * @param good of type good whose price will be determined based on the current solar system's
     *             attributes
     */
    public void setPrice(Good good) {
        setPrice(good, this.currentSolarSystem.getTechLevel(),
                this.currentSolarSystem.getResource());
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
        return (good.getPrice() <= credits) && ship.hasCargoSpace();
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
        if (g != null) {
            credits += g.getPrice();
            ship.removeCargo(g);
        }
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
    @Override
    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    /**
     *
     * @return the player's current shipyard
     */
    public ShipYard getCurrentShipYard() {
        return currentSolarSystem.getShipYard();
    }

    /**
     * method to check if a player can upgrade their ship
     * @param upgrade the ship to check if you can upgrade to
     * @return true if you can change your ship to the upgrade ship
     */
    public boolean canChangeShip(ShipType upgrade) {
        return ship.getCargo().length <= upgrade.getCargoHolds();
    }

    private double getShipUpgradePrice(ShipType upgrade) {
        double currentShipPrice = ship.getPrice();
        double newShipPrice = upgrade.getPrice();
        return newShipPrice - currentShipPrice;
    }

    /**
     * method that changes the player's ship to the upgrade type
     * @param upgrade the ship type that the player is upgrading to
     * @return whether or not the ship is successfully upgraded
     */
    public boolean changeShipType(ShipType upgrade) {
        if(canChangeShip(upgrade) && (upgrade.getPrice() <= credits)) {
            Ship newShip = new Ship(upgrade);
            for(Good cargo : ship.getCargo()) {
                newShip.addCargo(cargo);
            }
            credits = credits - getShipUpgradePrice(upgrade);
            this.ship = newShip;
            return true;
        } else {
            return false;
        }
    }

    /**
     * setter method for the player's current solar system. This is mainly used for traveling b/n
     * solar systems
     *
     * @param currentSolarSystem the Solar System object to become the player's new current Solar
     *                           System
     */
    @Override
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
     * @return a String representation of all the above attributes of the current player
     */
    @NonNull
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

    /**
     * @return the fuel left in the ship
     */
    public double getFuel() {
        return this.ship.getFuel();
    }
}
