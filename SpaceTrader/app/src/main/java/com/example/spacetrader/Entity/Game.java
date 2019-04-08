package com.example.spacetrader.Entity;

import android.util.Log;

import java.io.Serializable;
import java.util.HashSet;

/**
 * A class that will control the main aspects of Space Trader, such as the player, the universe, the
 * solar systems, the planet(s), wtc.
 *
 */
public class Game implements Serializable {
    private final GameDifficulty gameDifficulty;
    private Player player;
    private final Universe universe;

    /**
     * Simple constructor that is passed in a Player object. Chains up to another constructor
     *
     * @param player of type Player that will be the player of the entire game of Space Trader
     */
    public Game(Player player) {
        this.gameDifficulty = GameDifficulty.BEGINNER;
        this.player = player;
        this.universe = new Universe(40);
        LogBig("\n" + this.universe.toString());
        Log.d("working", ("\nGame difficulty is " + gameDifficulty + "\n" + player.toString()));
    }
    /**
     * Constructor that creates a Game object starting with initializing the Player and the
     * Game Difficulty of the player
     *
     * @param gameDifficulty of enum type GameDifficulty that sets the game difficulty of the player
     *                       for the entirety of the game

     */
    public Game(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        this.universe = new Universe(40);
        LogBig("\n" + this.universe.toString());
        Log.d("working", ("\nGame difficulty is " + gameDifficulty + "\n"));
    }

    /**
     * simple method to log the universe in LOGCAT
     *
     * @param s of type string that will be displayed in the LOGCAT when the app is ran
     */
    private void LogBig(String s) {
        String s1 = s;
        if(s1.length() < 3000) {
            Log.d("universe: " , s1);
        } else {
            Log.d("universe: ", s1.substring(0, 3000));
            s1 = s1.substring(3000);
            LogBig(s1);
        }
    }

    /**
     * getter method to return the game difficulty of the player
     *
     * @return the game difficulty of the player
     */
    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

//     /**
//     * sets the game difficulty of the player for the game (this is only used at the config screen
//     * right?)
//     *
//     * @param gameDifficulty of type GameDifficulty that will determine how hard the game will be
//     *                       for the player
//     */
//    public void setGameDifficulty(GameDifficulty gameDifficulty) {
//        this.gameDifficulty = gameDifficulty;
//    }

    /**
     * getter method to return the current Player object
     *
     * @return the player of the current Game
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Changes the player's ship type
     * @param upgrade the ship to upgrade to
     * @return whether it worked
     */
    public boolean changePlayerShipType(ShipType upgrade) {
        return player.changeShipType(upgrade);
    }

    /**
     * setter method for the current Player object
     *
     * @param player the PLayer object passed in to be set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

//    /**
//     * getter method for the Universe object
//     *
//     * @return the current Universe object
//     */
//    public Universe getUniverse() {
//        return universe;
//    }

//    /**
//     * setter method for the Universe of the game (for when the player is continuing the game)
//     *
//     * @param universe og type Universe to be set upon relaunching the game to be continued on
//     */
//    public void setUniverse(Universe universe) {
//        this.universe = universe;
//    }

    /**
     * gets a random solar system for the player
     *
     * @return the random solar system
     */
    public SolarSystem getRandomSolarSystem(){
        return universe.getRandomSolarSystem();
    }

    /**
     *  setter method for the player's current solar system
     */
    public void setPlayerSolarSystem() {
        player.setCurrentSolarSystem(universe.getRandomSolarSystem());
    }

    /**
     * setter method for the player's current solar system with a passed in SolarSystem object
     *
     * @param solarSystem of type Solar System that will be the player's new current Solar System
     */
    public void setPlayerSolarSystem(SolarSystem solarSystem) {
        player.setCurrentSolarSystem(solarSystem);
    }

    /**
     * getter method for the current Solar System
     *
     * @return the player's current solar system
     */
    public SolarSystem getPlayerSolarSystem() {
        return player.getCurrentSolarSystem();
    }

    /**
     *
     * @return player's current shipyard
     */
    private ShipYard getPlayerShipYard() {
        return player.getCurrentShipYard();
    }

    /**
     * refuels the player's ship fully
     * @return whether or not it was able to refuel fully
     */
    public boolean refuelMax() {
        return ShipYard.refuelMax(player);
    }

    /**
     * method to facilitate the trading capabilities of the player and trader based on the
     *  availability of credits goods, and the dependency of the political system, resources, and
     *  tech level of the solar system
     *
     * @param good of type Good that may or may not be available for the player to buy from the
     *             trader, or for the player to sell to the trader
     * @param buyer of type Trader capability that determines whether or not the buyer is able to
     *              buy any goods
     * @param seller of type Trader capability that determines whether or not the seller is able to
     *               sell any goods
     * @return a boolean representation of if the transaction between the buyer and seller is valid
     */
    public static boolean facilitateTrade(Good good, TraderCapability buyer,
                                          TraderCapability seller) {
        Log.d("FACILITATING: ", "GOOD - "  + good.getGoodType() + " - " + good.getPrice());
        if(!buyer.canBuy(good) || !seller.canSell(good)) {
            return false;
        } else {
            buyer.buy(good);
            seller.sell(good);
            return true;
        }
    }

    /**
     * lets the player travel
     * @param solarSystem the SS player wants to travel to
     * @return whether or not the travelling was successful
     */
    public boolean facilitateTravel(SolarSystem solarSystem) {
        if(player.canTravel(solarSystem)) {
            player.travel(solarSystem);
            return true;
        } else {
            return false;
        }
    }

    /**
     * a getter method that returns the credits the payer currently has
     *
     * @return a double that represents the player's credits
     */
    public double getPlayerCredits() {
        return this.player.getCredits();
    }

    /**
     * setter method for the player's credits
     *
     * @param v of type double that will be the new value of credits the player has
     */
    public void setPlayerCredits(double v) {
        this.player.setCredits(v);
    }

    /**
     * getter method for the space port of the current solar system
     *
     * @return the current solar system of the player
     */
    public SpacePort getSpacePort() {
        return this.player.getSpacePort();
    }

    /**
     * getter method that returns a HashSet of all the solar systems in the current game
     *
     * @return a HashSet of all the solar systems in the current game
     */
    public HashSet<SolarSystem> getSolarSystems() {
        return (HashSet)universe.getSolarSystems();
    }

    /**
     * gets the player's fuel
     * @return the player's fuel
     */
    public double getFuel() {
        return player.getFuel();
    }

    /**
     * method that sees if the player can travel to a given solar system
     * @param solarSystem the solar system the player wants to see if they can travel to
     * @return boolean that represents if the player can travel to the solar system
     */
    public boolean playerCanTravel(SolarSystem solarSystem) {
        return this.player.canTravel(solarSystem);
    }

    /**
     * Gets the current players solar system stats
     * @return the current players solar system stats
     */
    public String getSolarSystemStats() {
        return this.player.getSolarSystemStats();
    }
}