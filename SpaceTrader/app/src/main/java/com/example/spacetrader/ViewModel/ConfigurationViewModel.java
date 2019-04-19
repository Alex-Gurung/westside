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

/**
 * activity that allows the user to input their character's initial information and begin the game
 */
public class ConfigurationViewModel extends AndroidViewModel {
    private final GameInteractor gameInteractor;

    /**
     * constructor for the view model that gets an instance of the game interactor
     * @param application the app
     */
    public ConfigurationViewModel (@NonNull Application application) {
        super(application);
        gameInteractor = Model.getInstance().getGameInteractor();
    }

    /**
     * method that sets up the game with the input from the player
     *
     * @param name the name the player chose
     * @param fighter an int representing the fighter skill points
     * @param engineer an int representing the engineer skill points
     * @param pilot an int representing the pilot skill points
     * @param trader an int representing the trader skill points
     * @param gd the game difficulty chosen by the player
     */
    public void initializeGame(String name, int fighter, int engineer, int pilot, int trader,
                               GameDifficulty gd) {
        Game game = new Game(gd);
        Player player = new Player(pilot, fighter, engineer, trader, name, new Ship(ShipType.GNAT),
                game.getRandomSolarSystem(), 1000);
        game.setPlayer(player);
        gameInteractor.setGame(game);
    }

    /**
     * method that sets the game with the initialization data and sets the player's solar system
     *
     * @param game the game the player wil play
     */
    public void loadGame(Game game) {
         gameInteractor.setGame(game);
         gameInteractor.setPlayerSolarSystem(game.getPlayerSolarSystem());
    }



}
