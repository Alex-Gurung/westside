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

    public UniverseViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getGameInteractor();
    }

    public SolarSystem getCurrentSolarSystem() {
        return interactor.getPlayerSolarSystem();
    }

    public int getCurrentSolarX() {
        return this.getCurrentSolarSystem().getLocation().getX();
    }
    public int getCurrentSolarY() {
        return this.getCurrentSolarSystem().getLocation().getY();
    }

    public Game getGame() {
        return interactor.getGame();
    }

    public HashSet<SolarSystem> getSolarSystems() {
        return interactor.getSolarSystems();
    }

    public boolean playerCanTravel(SolarSystem solarSystem) {
        return interactor.playerCanTravel(solarSystem);
    }

    public boolean facilitateTravel(SolarSystem solarSystem) {
        return interactor.facilitateTravel(solarSystem);
    }

    public double getFuel() {
        return interactor.getFuel();
    }


    public double getPlayerCredits() {
        return interactor.getPlayerCredits();
    }
}
