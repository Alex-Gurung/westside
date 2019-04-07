package com.example.spacetrader.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Model.GameInteractor;
import com.example.spacetrader.Model.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to define interactions between the universe activity and the model
 */
public class UniverseViewModel extends AndroidViewModel {
    private final GameInteractor interactor;

    /**
     * Constructor to instantiate the trading view model with an instance of the gameInteractor
     * @param application the application currently being used
     */
    public UniverseViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getGameInteractor();
    }

    /**
     * Getter for current solar system
     * @return the current solar system
     */
    public SolarSystem getCurrentSolarSystem() {
        return interactor.getPlayerSolarSystem();
    }

    /**
     * Getter for the current solar system's x coordinate
     * @return the current solar system's x coordinate
     */
    public int getCurrentSolarX() {
        return this.getCurrentSolarSystem().getLocation().getX();
    }

    /**
     * Getter for the current solar system's y coordinate
     * @return the current solar system's y coordinate
     */
    public int getCurrentSolarY() {
        return this.getCurrentSolarSystem().getLocation().getY();
    }

    /**
     * Getter for the current game
     * @return the current game
     */
    public Game getGame() {
        return interactor.getGame();
    }

    /**
     * Getter for the set of solar systems
     * @return the set of solar systems
     */
    public Set<SolarSystem> getSolarSystems() {
        return interactor.getSolarSystems();
    }

    /**
     * Method to dermine if the player can travel to a specific solar system
     * @param solarSystem the solar system to travel to
     * @return whether the player can travel to the solar system
     */
    public boolean playerCanTravel(SolarSystem solarSystem) {
        return interactor.playerCanTravel(solarSystem);
    }

    /**
     * Method to facilitate travel (if possible) to the given solar system
     * @param solarSystem the solar system to travel to
     * @return whether the player traveled to the solar system
     */
    public boolean facilitateTravel(SolarSystem solarSystem) {
        return interactor.facilitateTravel(solarSystem);
    }

    /**
     * Getter for the amount of fuel the player has
     * @return the amount of fuel the player has
     */
    public double getFuel() {
        return interactor.getFuel();
    }

    /**
     * Getter for the number of credits the player has
     * @return number of credits the player has
     */
    public double getPlayerCredits() {
        return interactor.getPlayerCredits();
    }
}
