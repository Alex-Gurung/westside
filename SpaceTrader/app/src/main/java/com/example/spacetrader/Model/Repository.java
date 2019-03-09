package com.example.spacetrader.Model;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.Entity.TraderCapability;

import java.util.HashSet;

public class Repository {
    private Game game;

    public Repository() {}

    public Repository(Game game){
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {this.game = game;}

    public boolean playerCanTravel(SolarSystem solarSystem) {
        return getPlayer().canTravel(solarSystem);
    }

    public Player getPlayer() {return this.game.getPlayer();}

    public Good[] getCargo(TraderCapability trader) {return trader.getCargo();}

    public double getPlayerCredits() {
        return this.game.getPlayerCredits();
    }

    public void setPlayerCredits(double v) {
        this.game.setPlayerCredits(v);
    }
    public void setSolarSystem() {
        this.game.setPlayerSolarSystem();
    }
    public SolarSystem getPlayerSolarSystem() {
        return this.game.getPlayerSolarSystem();
    }

    public SpacePort getSpacePort() {
        return this.game.getSpacePort();
    }

    public boolean facilitateTrade(Good toBuy, TraderCapability buyer, TraderCapability seller) {
        return this.game.facilitateTrade(toBuy, buyer, seller);
    }

    public boolean facilitateTravel(SolarSystem solarSystem) {
        return this.game.facilitateTravel(solarSystem);
    }

    public HashSet<SolarSystem> getSolarSystems() {
        return this.game.getSolarSystems();
    }

    public double getFuel() {
        return this.game.getFuel();
    }
}
