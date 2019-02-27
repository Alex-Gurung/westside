package com.example.spacetrader.Entity;

import android.util.Log;

import java.util.HashSet;

public class Game {
    private GameDifficulty gameDifficulty;
    private Player player;
    private Universe universe;

    public Game(Player player) {
        this(GameDifficulty.BEGINNER, player);
    }

    public Game(GameDifficulty gameDifficulty, Player player) {
        this.gameDifficulty = gameDifficulty;
        this.player = player;
        this.universe = new Universe(50);
        LogBig("\n" + this.universe.toString());
        Log.d("working", ("\nGame difficulty is " + gameDifficulty + "\n" + player.toString()));
    }

    private void LogBig(String s) {
        if(s.length() < 3000) {
            Log.d("universe: " , s);
            return;
        } else {
            Log.d("universe: ", s.substring(0, 3000));
            s = s.substring(3000);
            LogBig(s);
            return;
        }
    }

    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setPlayerSolarSystem() {
        player.setCurrentSolarSystem(universe.getRandomSolarSystem());
    }

    public void setPlayerSolarSystem(SolarSystem solarSystem) {
        player.setCurrentSolarSystem(solarSystem);
    }
    public SolarSystem getPlayerSolarSystem() {
        return player.getCurrentSolarSystem();
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public boolean facilitateTrade(Good good, TraderCapability buyer, TraderCapability seller) {
        if(!buyer.canBuy(good) || !seller.canSell(good)) {
            return false;
        } else {
            buyer.buy(good);
            seller.sell(good);
            return true;
        }
    }

    public double getPlayerCredits() {
        return this.player.getCredits();
    }

    public void setPlayerCredits(double v) {
        this.player.setCredits(v);
    }

    public SpacePort getSpacePort() {
        return this.player.getSpacePort();
    }

    public HashSet<SolarSystem> getSolarSystems() {
        return universe.getSolarSystems();
    }
}