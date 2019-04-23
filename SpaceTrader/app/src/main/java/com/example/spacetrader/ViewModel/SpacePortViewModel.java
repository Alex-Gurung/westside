package com.example.spacetrader.ViewModel;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.example.spacetrader.Entity.*;
import com.example.spacetrader.Model.GameInteractor;

/**
 * Class to facilitate interactions from the Trading View to the model
 */
public class SpacePortViewModel extends AndroidViewModel {
    private static final GameInteractor interactor = GameInteractor.getInstance();

    /**
     * Constructor to instantiate the trading view model with an instance of the gameInteractor
     * @param application the application currently being used
     */
    public SpacePortViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Getter for the game
     * @return the current game state
     */
    public static Game getGame() {
        return interactor.getGame();
    }

    /**
     * Getter for the player
     * @return the current player object
     */
    public Player getPlayer() {return interactor.getPlayer();}

    /**
     * Getter for the cargo a specific trader has
     * @param trader the 3
     * @return the cargo that trader has
     */
    public Good[] getCargo(TraderCapability trader) {return interactor.getCargo(trader);}

    /**
     * Getter for player credits
     * @return the number of credits a player has
     */
    public double getPlayerCredits() {
        return interactor.getPlayerCredits();
    }

    /**
     * Setter for player credits
     * @param v the credits the player will have
     */
    public void setPlayerCredits(double v) {
        interactor.setPlayerCredits(v);
    }

    /**
     * Getter for the current spacePort
     * @return the space port
     */
    public SpacePort getSpacePort() {
        return interactor.getSpacePort();
    }

    /**
     * Method to facilitate trade of a good between a buyer and a seller
     * @param toBuy The good to be bought
     * @param buyer The trader buying it
     * @param seller The trader selling it
     * @return Whether the good was traded
     */
    public boolean facilitateTrade(Good toBuy, TraderCapability buyer, TraderCapability seller) {
        return interactor.facilitateTrade(toBuy, buyer, seller);
    }

    public boolean upgradeShip(ShipType shipType) {
        return getGame().changePlayerShipType(shipType);
    }

    public static double getUpgradePrice(ShipType shipType) {
        return getGame().getUpgradePrice(shipType);
    }

    /**
     * Method that attempts to refuel the player's ship
     * @return a boolean that represents if the player has enough credits to refuel their Ship
     */
    public boolean refuelMax() {
        return interactor.refuelMax();
    }

    /**
     * getter for the game difficulty
     * @return an int representing the game difficulty
     */
    public int getGameDifficulty(){
        return interactor.getGameDifficulty();
    }

    /**
     * Gets the current players solar system stats
     * @return the current players solar system stats
     */
    public String getSolarSystemStats() {
        return interactor.getSolarSystemStats();
    }

    public SolarSystem getCurrentSolarSystem() { return interactor.getPlayerSolarSystem();}
}
