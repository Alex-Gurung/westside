package com.example.spacetrader.ViewModel;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.GameDifficulty;
import com.example.spacetrader.Model.GameInteractor;
import com.example.spacetrader.Model.Model;

public class ConfigurationViewModel extends AndroidViewModel {
    private GameInteractor interactor;

    public ConfigurationViewModel (@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getGameInteractor();
    }

    public void initializeGame(String name, int fighter, int engineer, int pilot, int trader, GameDifficulty gd) {
        Player player = new Player(pilot, fighter, engineer, trader, name);
        Game game = new Game(gd, player);
        interactor.setGame(game);
    }

    public Game getGame() {
        return interactor.getGame();
    }
}
