package com.example.spacetrader.Entity;

import android.util.Log;

import java.util.ArrayList;

public class Game {
    GameDifficulty gameDifficulty;
    Player player;
    Universe universe;

    public Game(Player player) {
        this(GameDifficulty.BEGINNER, player);
    }

    public Game(GameDifficulty gameDifficulty, Player player) {
        this.gameDifficulty = gameDifficulty;
        this.player = player;
        this.universe = new Universe(50);
        Log.d("universe: ", this.universe.toString());
        Log.d("working", ("\nGame difficulty is " + gameDifficulty + "\n" + player.toString()));
    }

    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public boolean facilitateTrade(Good good, Character buyer, Character seller) {
        double price = good.getPrice();
        boolean hasEnoughCredits = (buyer.getCredits() - price) > 0;
        if (!hasEnoughCredits) {
            return false;
        }
        Ship buyerShip = buyer.getMyShip();
        Good[] buyerGoods = buyerShip.getCargo();
        int counter = 0;
        for (int i = 0; i < buyerGoods.length; i++) {
            if (buyerGoods[i] != null) {
                counter += 1;
            }
        }
        if ((counter + 1) >= buyerGoods.length) {
            return false;
        }

        /* buyer has enough credits and has space for the good */
        buyerGoods[counter+1] = good;
        buyer.setCredits(buyer.getCredits() - price);

        Ship sellerShip = seller.getMyShip();
        Good[] sellerGoods = sellerShip.getCargo();
        ArrayList<Good> newGoods = new ArrayList<>();
        int find_good = -1;
        for (int i = 0; i < sellerGoods.length; i++) {
            if (find_good != -1 && sellerGoods[i].getGoodType() == good.getGoodType()) {
                find_good = i;
                newGoods.add(sellerGoods[i]);
            } else if (sellerGoods[i].getGoodType() != good.getGoodType()) {
                newGoods.add(sellerGoods[i]);
            }
        }
        sellerShip.setCargo((Good[]) newGoods.toArray());
        seller.setCredits(seller.getCredits() + price);
        return true;
    }

}