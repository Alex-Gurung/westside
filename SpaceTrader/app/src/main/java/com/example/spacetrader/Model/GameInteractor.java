package com.example.spacetrader.Model;


import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.ShipType;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.Entity.TraderCapability;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Interactor for Game that has access to the repository
 */
public class GameInteractor extends Interactor {
    /**
     * constructor for Interactor that takes in a Repository parameter
     *
     * @param repo of type Repository that will instantiate the repository for the Game
     */
    public GameInteractor(Repository repo) {
        super(repo);
    }

    /**
     * getter method for the current player's game
     *
     * @return the Repository's current Game object
     */
    public Game getGame() {
        return getRepository().getGame();
    }

    /**
     * sets the current Player's Game to the game loaded from the Repository
     *
     * @param game of type Game to be retrieved for the player
     */
    public void setGame(Game game) {
        getRepository().setGame(game);
    }

    /**
     * sets the Player's Solar System to the passed in Solar System
     *
     * @param solarSystem of type SolarSystem to become the player's current Solar System
     */
    public void setPlayerSolarSystem(SolarSystem solarSystem) {
        getRepository().setSolarSystem(solarSystem);
    }

    /**
     * getter method that retrieves the player's current Solar System
     *
     * @return the player's current Solar System
     */
    public SolarSystem getPlayerSolarSystem() {
        return getRepository().getPlayerSolarSystem();
    }

    /**
     * getter method to retrieve the current Player of the Game from the Repository
     *
     * @return the Player of the current Game
     */
    public Player getPlayer() { return getRepository().getPlayer();}

    /**
     * a method for determining if the player can travel to a given solar system
     *
     * @param solarSystem the solar system the player wants to travel to
     * @return whether the player can travel to the solar system
     */
    public boolean playerCanTravel(SolarSystem solarSystem) {
        return getRepository().playerCanTravel(solarSystem);
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
        return getRepository().getCargo(trader);
    }

    /**
     * getter method to retrieve the player's current number of credits
     *
     * @return the player's current credit number
     */
    public double getPlayerCredits() {
        return getRepository().getPlayerCredits();
    }

    /**
     * sets the current Player's credits to the passed in parameter
     *
     * @param v of type double to represent the current Player's new number of credits
     */
    public void setPlayerCredits(double v) {
        getRepository().setPlayerCredits(v);
    }

    /**
     * getter method to return the current Solar System's Space Port
     *
     * @return the current Solar System's Space Port
     */
    public SpacePort getSpacePort() {
        return getRepository().getSpacePort();
    }

    /**
     * getter method to retrieve the current Universe's set of Solar Systems in the Game
     *
     * @return the current Universe's set of Solar Systems in the Game
     */
    public Set<SolarSystem> getSolarSystems() {
        return getRepository().getSolarSystems();
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
        return getRepository().facilitateTrade(toBuy, buyer, seller);
    }

    /**
     * method that determines if the Player is capable of trading ships
     *
     * @param upgrade of type ShipType that is to be the new Ship of the Player
     * @return a boolean that represents whether the Player has the means of changing their ship
     */
    public boolean canChangePlayerShip(ShipType upgrade) {
        return getGame().changePlayerShipType(upgrade);
    }

    /**
     * method that determines whether the player can change the type of their ship
     *
     * @param upgrade of type ShipType to be the Ship the Player upgrades to
     * @return a boolean that represents whether the Player can change their Ship Type
     */
    public boolean changePlayerShipType(ShipType upgrade) {
        return getGame().changePlayerShipType(upgrade);
    }

    /**
     * method that determines whether or not the Player has the means to travel to a different Solar
     * System
     *
     * @param solarSystem of type SolarSystem that is to possibly become the Player's new current
     *                    solar system
     * @return a boolean that represents whether or not the Player can travel
     */
    public boolean facilitateTravel(SolarSystem solarSystem) {
        return getRepository().facilitateTravel(solarSystem);
    }

    /**
     * getter method that retrieves the Player's current fuel level
     *
     * @return a double that represents the Player's current fuel level
     */
    public double getFuel() {
        return getRepository().getFuel();
    }

    /**
     * method that determines whether or not the player has enough credits to refuel their Ship
     *
     * @return a boolean that represents if the player has enough credits to refuel their Ship
     */
    public boolean refuelMax() {
        return getRepository().refuelMax();
    }
}