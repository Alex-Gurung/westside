package com.example.spacetrader.Model;

import com.example.spacetrader.Entity.Game;

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

}
