package com.example.spacetrader.ViewModel;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.GameDifficulty;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Model.GameInteractor;
import com.example.spacetrader.Model.Model;

public class ConfigurationViewModel extends AndroidViewModel {
    private final GameInteractor gameInteractor;

    public ConfigurationViewModel (@NonNull Application application) {
        super(application);
        gameInteractor = Model.getInstance().getGameInteractor();
    }

    public void initializeGame(String name, int fighter, int engineer, int pilot, int trader, GameDifficulty gd) {
        Player player = new Player(pilot, fighter, engineer, trader, name);
        Game game = new Game(gd, player);
        gameInteractor.setGame(game);
        gameInteractor.setPlayerSolarSystem();
    }

    public void loadGame(Game game) {
         gameInteractor.setGame(game);
         gameInteractor.setPlayerSolarSystem();
    }

    public Game getGame() {
        return gameInteractor.getGame();
    }

}
