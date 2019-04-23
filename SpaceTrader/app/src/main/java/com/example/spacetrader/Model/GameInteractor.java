package com.example.spacetrader.Model;

import com.example.spacetrader.Entity.*;

import java.io.Serializable;
import java.util.HashSet;

/**
 * The GameInteractor for Game that has access to the repository
 */
public class GameInteractor implements Serializable{
    private Game game;
    private FirebaseActor firebaseActor;
    private static final GameInteractor instance = new GameInteractor();

    public static GameInteractor getInstance() {
        return instance;
    }
    /**
     * constructor for GameInteractor that takes in a Repository parameter
     */
    private GameInteractor() {}

    /**
     * getter method for the current player's game
     * @return the Repository's current Game object
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * sets the current Player's Game to the game loaded from the Repository
     * @param game of type Game to be retrieved for the player
     */
    public void setGame(Game game){
        this.game = game;
    }

    /**
     * sets the Player's Solar System to the passed in Solar System
     * @param solarSystem of type SolarSystem to become the player's current Solar System
     */
    public void setPlayerSolarSystem(SolarSystem solarSystem) {
        this.game.setPlayerSolarSystem(solarSystem);
    }

    /**
     * getter method that retrieves the player's current Solar System
     *
     * @return the player's current Solar System
     */
    public SolarSystem getPlayerSolarSystem() {
        return this.game.getPlayerSolarSystem();
    }

    /**
     * getter method to retrieve the current Player of the Game from the Repository
     *
     * @return the Player of the current Game
     */
    public Player getPlayer() {
        return this.game.getPlayer();
    }

    /**
     * a method for determining if the player can travel to a given solar system
     * @param solarSystem the solar system the player wants to travel to
     * @return whether the player can travel to the solar system
     */
    public boolean playerCanTravel(SolarSystem solarSystem) {
        return this.game.playerCanTravel(solarSystem);
    }

    /**
     * getter method that retrieves the current Solar System's Trader cargo hold
     *
     * @param trader of type TraderCapability that the Player will trade with at spaceports
     *               and in between traveling
     * @return an array of type Good that represents the cargo hold of the current Solar System's
     * space port trader
     */
    public Good[] getCargo(TraderCapability trader) {
        return trader.getCargo();
    }

    /**
     * getter method to retrieve the player's current number of credits
     *
     * @return the player's current credit number
     */
    public double getPlayerCredits() {
        return this.game.getPlayerCredits();
    }

    /**
     * sets the current Player's credits to the passed in parameter
     *
     * @param v of type double to represent the current Player's new number of credits
     */
    public void setPlayerCredits(double v){
        game.setPlayerCredits(v);
    }

    /**
     * getter method to return the current Solar System's Space Port
     *
     * @return the current Solar System's Space Port
     */
    public SpacePort getSpacePort() {
        return this.game.getSpacePort();
    }

    /**
     * Getter for the set of solar systems
     * @return the set of solar systems stored in universe
     */
    public HashSet<SolarSystem> getSolarSystems() {
        return this.game.getSolarSystems();
    }

    /**
     * method to facilitate the trade between the buyer and the seller
     *
     * @param toBuy of type Good that is to be bought
     * @param buyer of type TraderCapability to buy the good
     * @param seller of type TraderCapability to sell the good
     * @return a boolean that represents whether the trade was facilitated
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
     * method that checks to see if the Player was able to successfully travel via wormhole
     *
     * @param solarSystem of type SolarSystem that is to be the SolarSystem the Player intends to
     *                    travel to
     * @return a boolean that represents whether the Player was able to successfully travel
     */
    public boolean facilitateTravelWormhole(SolarSystem solarSystem) {
        return this.game.facilitateTravelWormhole(solarSystem);
    }

    /**
     * getter method that returns the current Wormhole of the Universe
     *
     * @return a Wormhole object that is the current Wormhole in the Universe
     */
    public Wormhole getWormhole() {
        return this.game.getWormhole();
    }

    /**
     * getter method that retrieves the Player's current fuel level
     *
     * @return a double that represents the Player's current fuel level
     */
    public double getFuel() {
        return game.getFuel();
    }

    /**
     * method for refueling the player's ship to a full tank
     * @return a boolean that represents whether the player could completely refuel their ship
     */
    public boolean refuelMax(){
        return this.game.refuelMax();
    }

    /**
     * getter for the game difficulty
     * @return an int representing the game difficulty
     */
    public int getGameDifficulty(){
        return this.game.getGameDifficulty().ordinal();
    }

    /**
     * Gets the current players solar system stats
     * @return the current players solar system stats
     */
    public String getSolarSystemStats() {
        return this.game.getSolarSystemStats();
    }

    /**
     * method that checks if the player can change their ship
     * @param upgrade the ship that the player wants to upgrade to
     * @return a boolean representing if the player can change their ship
     */
    public boolean canChangePlayerShip(ShipType upgrade) {
        return this.game.changePlayerShipType(upgrade);
    }
}