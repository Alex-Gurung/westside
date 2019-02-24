package com.example.spacetrader.Entity;

import android.util.Log;

public class Game {
    GameDifficulty gameDifficulty;
    Player player;
    Universe universe;

    public Game(Player player) {
        this(GameDifficulty.BEGINNER, player);
    }

    public Game(GameDifficulty gameDifficulty, Player player) {
        this.gameDifficulty = gameDifficulty;
        this.player = player;
        this.universe = new Universe(50);
        Log.d("universe: ", this.universe.toString());
        Log.d("working", ("\nGame difficulty is " + gameDifficulty + "\n" + player.toString()));
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

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public void facilitateTrade(Good good, Character buyer, Character seller) {
        double price = good.getPrice();
        boolean hasEnoughCredits = (buyer.getCredits() - price) > 0;

    }

}