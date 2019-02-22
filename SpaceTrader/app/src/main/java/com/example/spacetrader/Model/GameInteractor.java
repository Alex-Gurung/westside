package com.example.spacetrader.Model;

import android.util.Log;

import com.example.spacetrader.Entity.Game;

import java.util.List;

public class GameInteractor extends Interactor {
    public GameInteractor(Repository repo) {
        super(repo);
    }

    public Game getGame() {
        return getRepository().getGame();
    }

    public void setGame(Game game) {getRepository().setGame(game);}
}
