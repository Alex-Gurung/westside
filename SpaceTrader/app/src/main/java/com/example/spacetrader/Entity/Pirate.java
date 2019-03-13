package com.example.spacetrader.Entity;

import java.io.Serializable;

public class Pirate extends NonPlayerCharacter implements Serializable {
    public Pirate() {
        super(new Ship(ShipType.GNAT));
    }
    @Override
    public void interactWithPlayer(Player player) {

    }
}
