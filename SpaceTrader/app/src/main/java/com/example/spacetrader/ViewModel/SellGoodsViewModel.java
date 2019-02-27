package com.example.spacetrader.ViewModel;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.GameDifficulty;
import com.example.spacetrader.Entity.TraderCapability;
import com.example.spacetrader.Model.GameInteractor;
import com.example.spacetrader.Model.Model;

public class SellGoodsViewModel extends AndroidViewModel {
    private GameInteractor interactor;

    public SellGoodsViewModel (@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getGameInteractor();
    }

    public Game getGame() {
        return interactor.getGame();
    }

    public Player getPlayer() {return interactor.getPlayer();}

    public Good[] getCargo(TraderCapability trader) {return interactor.getCargo(trader);}

    public double getPlayerCredits() {
        return interactor.getPlayerCredits();
    }

    public void setPlayerCredits(double v) {
        interactor.setPlayerCredits(v);
    }
}
