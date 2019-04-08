package com.example.spacetrader.Model;

import com.example.spacetrader.Entity.FirebaseActor;
import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.ShipType;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.Entity.TraderCapability;


import java.io.Serializable;
import java.util.HashSet;

/**
 * repository that acts as data storage
 */
class Repository implements Serializable {
    private Game game;
    private FirebaseActor firebaseActor;

    /**
     * constructor for the repository
     */
    public Repository() {
    }

    /**
     * getter for the game
     * @return the game the user is playing
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * setter for the game
     * @param game the game the player wants to play
     */
    public void setGame(Game game) {
        this.game = game;
        firebaseActor = new FirebaseActor(game);
    }

    /**
     * method that sees if the player can travel to a given solar system
     * @param solarSystem the solar system the player wants to see if they can travel to
     * @return boolean that represents if the player can travel to the solar system
     */
    public boolean playerCanTravel(SolarSystem solarSystem) {
        return getPlayer().canTravel(solarSystem);
    }

    /**
     * getter method for the player
     * @return the player
     */
    public Player getPlayer() {return this.game.getPlayer();}

    /**
     * getter method for a trader's cargo
     * @param trader the trader whose cargo you want to get
     * @return the cargo of the trader
     */
    public Good[] getCargo(TraderCapability trader) {return trader.getCargo();}

    /**
     * getter method for the player's credits
     * @return a double representing the player's credits
     */
    public double getPlayerCredits() {
        return this.game.getPlayerCredits();
    }

    /**
     * setter method for the player's credits
     * @param v the amount of credits to give the player
     */
    public void setPlayerCredits(double v) {
        this.game.setPlayerCredits(v);
    }

    /**
     * setter method for the player's solar system
     * @param solarSystem the solar system to put the player into
     */
    public void setSolarSystem(SolarSystem solarSystem) {
        this.game.setPlayerSolarSystem(solarSystem);
    }

    /**
     * the getter for the player's solar system
     * @return the player's solar system
     */
    public SolarSystem getPlayerSolarSystem() {
        return this.game.getPlayerSolarSystem();
    }

    /**
     * method for refueling the player's ship to a full tank
     * @return a boolean that represents whether the player could completely refuel their ship
     */
    public boolean refuelMax(){
        return this.game.refuelMax();
    }

    /**
     * method that checks if the player can change their ship
     * @param upgrade the ship that the player wants to upgrade to
     * @return a boolean representing if the player can change their ship
     */
    public boolean canChangePlayerShip(ShipType upgrade) {
        return game.changePlayerShipType(upgrade);
    }

    /**
     * method that changes the player's ship type when they buy a new ship
     * @param upgrade the ship that the player wants to upgrade to
     * @return a boolean representing if the player successfully upgraded their ship
     */
    public boolean changePlayerShipType(ShipType upgrade) {
        return game.changePlayerShipType(upgrade);
    }

    /**
     * getter method for the space port
     * @return the space port the player is at
     */
    public SpacePort getSpacePort() {
        return this.game.getSpacePort();
    }

    /**
     *  method that allows a trader and sell to conduct a trade
     *
     * @param toBuy the good that is to be bought by the buyer
     * @param buyer the trader who is buying the good
     * @param seller the trader who is selling the good
     * @return whether or not the trade happened
     */
    public boolean facilitateTrade(Good toBuy, TraderCapability buyer, TraderCapability seller) {
        boolean facilitateTrade = Game.facilitateTrade(toBuy, buyer, seller);
        firebaseActor.updateFire();
        return facilitateTrade;
    }

    /**
     * Method to get the player to travel between solar systems
     * @param solarSystem the solar system the player will travel to
     * @return whether or not travel happened (or could happen)
     */
    public boolean facilitateTravel(SolarSystem solarSystem) {
        return this.game.facilitateTravel(solarSystem);
    }

    /**
     * Getter for the set of solar systems
     * @return the set of solar systems stored in universe
     */
    public HashSet<SolarSystem> getSolarSystems() {
        return this.game.getSolarSystems();
    }

    /**
     * Getter for the current player fuel
     * @return the current fuel the player has
     */
    public double getFuel() {
        return this.game.getFuel();
    }
}
