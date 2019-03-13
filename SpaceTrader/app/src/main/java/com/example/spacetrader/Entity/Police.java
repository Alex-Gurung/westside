package com.example.spacetrader.Entity;

import java.io.Serializable;

public class Police extends NonPlayerCharacter implements Serializable {
    public Police() {
        super(new Ship(ShipType.GNAT));
    }
    @Override
    public void interactWithPlayer(Player player) {

    }
}
