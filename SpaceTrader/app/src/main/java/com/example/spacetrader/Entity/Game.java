package com.example.spacetrader.Entity;

public class Game {
    GameDifficulty gameDifficulty;
    Player player;

    public Game(Player player) {
        this(GameDifficulty.BEGINNER, player);
    }
    public Game(GameDifficulty gameDifficulty, Player player) {
        this.gameDifficulty = gameDifficulty;
        this.player = player;
    }

}
