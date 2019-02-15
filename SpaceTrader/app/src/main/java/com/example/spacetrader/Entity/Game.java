package com.example.spacetrader.Entity;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class Game {
    GameDifficulty gameDifficulty;
    Player player;

    public Game(Player player) {
        this(GameDifficulty.BEGINNER, player);
    }

    public Game(GameDifficulty gameDifficulty, Player player) {
        this.gameDifficulty = gameDifficulty;
        this.player = player;

        Log.d("working", ("\nGame difficulty is " + gameDifficulty + "\n" + player.toString()));
    }
}
