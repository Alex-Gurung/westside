package com.example.spacetrader.Model;

import android.util.Log;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.TraderCapability;

import java.util.List;

public class GameInteractor extends Interactor {
    public GameInteractor(Repository repo) {
        super(repo);
    }

    public Game getGame() {
        return getRepository().getGame();
    }

    public void setGame(Game game) {
        getRepository().setGame(game);
    }

    public Player getPlayer() {return getRepository().getPlayer();}

    public Good[] getCargo(TraderCapability trader) {return getRepository().getCargo(trader);}

    public double getPlayerCredits() {
        return getRepository().getPlayerCredits();
    }

    public void setPlayerCredits(double v) {
        getRepository().setPlayerCredits(v);
    }
}
