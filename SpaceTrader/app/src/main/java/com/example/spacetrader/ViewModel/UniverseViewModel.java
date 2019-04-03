package com.example.spacetrader.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Model.GameInteractor;
import com.example.spacetrader.Model.Model;

import java.util.HashSet;


public class UniverseViewModel extends AndroidViewModel {
    private final GameInteractor interactor;

    /**
     *
     * @param application
     */
    public UniverseViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getGameInteractor();
    }

    /**
     *
     * @return
     */
    public SolarSystem getCurrentSolarSystem() {
        return interactor.getPlayerSolarSystem();
    }

    /**
     *
     * @return
     */
    public int getCurrentSolarX() {
        return this.getCurrentSolarSystem().getLocation().getX();
    }

    /**
     *
     * @return
     */
    public int getCurrentSolarY() {
        return this.getCurrentSolarSystem().getLocation().getY();
    }

    /**
     *
     * @return
     */
    public Game getGame() {
        return interactor.getGame();
    }

    /**
     *
     * @return
     */
    public HashSet<SolarSystem> getSolarSystems() {
        return interactor.getSolarSystems();
    }

    /**
     *
     * @param solarSystem
     * @return
     */
    public boolean playerCanTravel(SolarSystem solarSystem) {
        return interactor.playerCanTravel(solarSystem);
    }

    /**
     *
     * @param solarSystem
     * @return
     */
    public boolean facilitateTravel(SolarSystem solarSystem) {
        return interactor.facilitateTravel(solarSystem);
    }

    /**
     *
     * @return
     */
    public double getFuel() {
        return interactor.getFuel();
    }

    /**
     *
     * @return
     */
    public double getPlayerCredits() {
        return interactor.getPlayerCredits();
    }
}
