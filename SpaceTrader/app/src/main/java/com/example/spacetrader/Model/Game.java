package com.example.spacetrader.Model;

import android.util.Log;

import com.example.spacetrader.Entity.GameDifficulty;

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
}
