package com.example.spacetrader.ViewModel;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.Entity.TraderCapability;
import com.example.spacetrader.Model.GameInteractor;
import com.example.spacetrader.Model.Model;

public class TradingViewModel extends AndroidViewModel {
    private final GameInteractor interactor;

    /**
     *
     * @param application
     */
    public TradingViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getGameInteractor();
    }

    /**
     *
     * @return
     */
    public Game getGame() {
        return interactor.getGame();
    }

    /**
     *
     * @return
     */
    public Player getPlayer() {return interactor.getPlayer();}

    /**
     *
     * @param trader
     * @return
     */
    public Good[] getCargo(TraderCapability trader) {return interactor.getCargo(trader);}

    /**
     *
     * @return
     */
    public double getPlayerCredits() {
        return interactor.getPlayerCredits();
    }

    /**
     *
     * @param v
     */
    public void setPlayerCredits(double v) {
        interactor.setPlayerCredits(v);
    }

    /**
     *
     * @return
     */
    public SpacePort getSpacePort() {
        return interactor.getSpacePort();
    }

    /**
     *
     *
     * @param toBuy
     * @param buyer
     * @param seller
     * @return
     */
    public boolean facilitateTrade(Good toBuy, TraderCapability buyer, TraderCapability seller) {
        return interactor.facilitateTrade(toBuy, buyer, seller);
    }

    /**
     *
     * @return
     */
    public boolean refuelMax() {
        return interactor.refuelMax();
    }
}
