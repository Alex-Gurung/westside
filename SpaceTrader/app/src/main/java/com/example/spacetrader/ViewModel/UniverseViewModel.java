package com.example.spacetrader.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Model.GameInteractor;
import com.example.spacetrader.Model.Model;

import java.util.HashSet;


public class UniverseViewModel extends AndroidViewModel {
    private GameInteractor interactor;

    public UniverseViewModel (@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getGameInteractor();
    }
    public SolarSystem getCurrentSolarSystem() {
        return interactor.getPlayerSolarSystem();
    }
    public HashSet<SolarSystem> getSolarSystems() {
        return interactor.getSolarSystems();
    }
}