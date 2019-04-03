package com.example.spacetrader.Model;

import android.support.annotation.NonNull;
import com.example.spacetrader.Entity.*;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static android.support.constraint.Constraints.TAG;

import java.io.Serializable;
import java.util.HashSet;


class Repository implements Serializable {
    private Game game;
    private String scoreString = "";
    private DatabaseReference myRef;

    public Repository() {
        setUpFirebase();
    }

    public Repository(Game game){
        this.game = game;
        setUpFirebase();
    }

    private void setUpFirebase() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("scores");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                updateScore(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void updateScore(String value) {
        if (this.game != null) {
            String my_score = "" + game.getPlayerCredits();
            scoreString = value;
            if (scoreString != null) {
                String[] scores = scoreString.split(", ");
                String new_scores = "";
                boolean anyMatch = false;
                for (String s : scores) {
                    if (s.length() > 0) {
                        if (s.equals(my_score)) {
                            anyMatch = true;
                        }
                        new_scores += s + ", ";
                    }
                }
                if (!anyMatch) {
                    new_scores += my_score;
                } else {
                    new_scores = new_scores.substring(0, new_scores.length() - 2);
                }
                myRef.setValue(new_scores);
            } else {
                myRef.setValue(my_score);
            }
            Log.d(TAG, "New High Score: " + value);
        }
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {this.game = game;}

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
        return game.canChangePlayerShip(upgrade);
    }

    public boolean changePlayerShipType(ShipType upgrade) {
        return game.changePlayerShipType(upgrade);
    }

    public SpacePort getSpacePort() {
        return this.game.getSpacePort();
    }

    public boolean facilitateTrade(Good toBuy, TraderCapability buyer, TraderCapability seller) {
        boolean facilitateTrade = this.game.facilitateTrade(toBuy, buyer, seller);
        updateScore(scoreString);
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
