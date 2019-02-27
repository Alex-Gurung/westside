package com.example.spacetrader.Model;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.Entity.TraderCapability;

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

    public SpacePort getSpacePort() {
        return this.game.getSpacePort();
    }
}
