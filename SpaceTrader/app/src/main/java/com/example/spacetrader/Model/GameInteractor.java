package com.example.spacetrader.Model;

import android.util.Log;

import com.example.spacetrader.Entity.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

/**
 * The Interactor for Game that has access to the repository
 */
public class GameInteractor extends Interactor implements Serializable {
    /**
     * 
     * @param repo
     */
    public GameInteractor(Repository repo) {
        super(repo);
    }

    public Game getGame() {
        return getRepository().getGame();
    }

    public void setGame(Game game) {
        getRepository().setGame(game);
    }
    public void setPlayerSolarSystem() {
        getRepository().setSolarSystem();
    }
    public void setPlayerSolarSystem(SolarSystem solarSystem) {
        getRepository().setSolarSystem(solarSystem);
    }
    public SolarSystem getPlayerSolarSystem() {
        return getRepository().getPlayerSolarSystem();
    }

    public Player getPlayer() { return getRepository().getPlayer();}

    public boolean playerCanTravel(SolarSystem solarSystem) {
        return getRepository().playerCanTravel(solarSystem);
    }

    public Good[] getCargo(TraderCapability trader) {return getRepository().getCargo(trader);}

    public double getPlayerCredits() {
        return getRepository().getPlayerCredits();
    }

    public void setPlayerCredits(double v) {
        getRepository().setPlayerCredits(v);
    }

    public SpacePort getSpacePort() {
        return getRepository().getSpacePort();
    }
    public HashSet<SolarSystem> getSolarSystems() { return getRepository().getSolarSystems(); }

    public boolean facilitateTrade(Good toBuy, TraderCapability buyer, TraderCapability seller) {
        return getRepository().facilitateTrade(toBuy, buyer, seller);
    }

    public boolean canChangePlayerShip(ShipType upgrade) {
        return getGame().canChangePlayerShip(upgrade);
    }

    public boolean changePlayerShipType(ShipType upgrade) {
        return getGame().changePlayerShipType(upgrade);
    }

    public boolean facilitateTravel(SolarSystem solarSystem) {
        return getRepository().facilitateTravel(solarSystem);
    }

    public double getFuel() {
        return getRepository().getFuel();
    }

    public boolean refuelMax() {
        return getRepository().refuelMax();
    }

    public void firebaseSave() {
        getRepository().firebaseSave();
    }
}