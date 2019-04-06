package com.example.spacetrader.Model;

import android.support.annotation.NonNull;
import com.example.spacetrader.Entity.*;

import android.util.Log;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.Entity.TraderCapability;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static android.support.constraint.Constraints.TAG;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class Repository implements Serializable {
    private Game game;
    private FirebaseActor firebaseActor;

    public Repository() {
    }

    public Repository(Game game){
        this.game = game;
    }


    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
        firebaseActor = new FirebaseActor(game);
    }

    public boolean playerCanTravel(SolarSystem solarSystem) {
        return getPlayer().canTravel(solarSystem);
    }

    public Player getPlayer() {return this.game.getPlayer();}

    public Good[] getCargo(TraderCapability trader) {return trader.getCargo();}

    public double getPlayerCredits() {
        return this.game.getPlayerCredits();
    }

    public void setPlayerCredits(double v) {
        this.game.setPlayerCredits(v);
    }
    public SolarSystem getRandomSolarSystem(){ return this.game.getRandomSolarSystem();}
    public void setSolarSystem() {
        this.game.setPlayerSolarSystem();
    }
    public void setSolarSystem(SolarSystem solarSystem) {
        this.game.setPlayerSolarSystem(solarSystem);
    }
    public SolarSystem getPlayerSolarSystem() {
        return this.game.getPlayerSolarSystem();
    }

    public boolean refuelMax(){
        return this.game.refuelMax();
    }

    public boolean canChangePlayerShip(ShipType upgrade) {
        return game.changePlayerShipType(upgrade);
    }

    public boolean changePlayerShipType(ShipType upgrade) {
        return game.changePlayerShipType(upgrade);
    }

    public SpacePort getSpacePort() {
        return this.game.getSpacePort();
    }

    public boolean facilitateTrade(Good toBuy, TraderCapability buyer, TraderCapability seller) {
        boolean facilitateTrade = this.game.facilitateTrade(toBuy, buyer, seller);
        firebaseActor.updateFire();
        return facilitateTrade;
    }

    public boolean facilitateTravel(SolarSystem solarSystem) {
        return this.game.facilitateTravel(solarSystem);
    }

    public HashSet<SolarSystem> getSolarSystems() {
        return this.game.getSolarSystems();
    }

    public double getFuel() {
        return this.game.getFuel();
    }
}
