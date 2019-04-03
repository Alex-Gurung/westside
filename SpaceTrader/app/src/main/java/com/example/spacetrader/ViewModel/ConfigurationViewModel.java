package com.example.spacetrader.ViewModel;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.GameDifficulty;
import com.example.spacetrader.Entity.Ship;
import com.example.spacetrader.Entity.ShipType;
import com.example.spacetrader.Model.GameInteractor;
import com.example.spacetrader.Model.Model;

public class ConfigurationViewModel extends AndroidViewModel {
    private final GameInteractor gameInteractor;

    public ConfigurationViewModel (@NonNull Application application) {
        super(application);
        gameInteractor = Model.getInstance().getGameInteractor();
    }

    public void initializeGame(String name, int fighter, int engineer, int pilot, int trader, GameDifficulty gd) {
        Game game = new Game(gd);
        Player player = new Player(pilot, fighter, engineer, trader, name, new Ship(ShipType.GNAT), game.getRandomSolarSystem(), 1000);
        game.setPlayer(player);
        gameInteractor.setGame(game);
    }

    public void loadGame(Game game) {
         gameInteractor.setGame(game);
         gameInteractor.setPlayerSolarSystem(game.getPlayerSolarSystem());
    }

    public Game getGame() {
        return gameInteractor.getGame();
    }

}
