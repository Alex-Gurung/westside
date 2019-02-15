package com.example.spacetrader.ViewModel;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.Entity.GameDifficulty;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.View.ConfigurationActivity;

public class ConfigurationViewModel extends AndroidViewModel {

    public ConfigurationViewModel (@NonNull Application application) {
        super(application);
    }

    public void initializeGame(String name, int fighter, int engineer, int pilot, int trader, GameDifficulty gd) {
        Player player = new Player(pilot, fighter, engineer, trader, name);
        Game game = new Game(gd, player);
    }
}
