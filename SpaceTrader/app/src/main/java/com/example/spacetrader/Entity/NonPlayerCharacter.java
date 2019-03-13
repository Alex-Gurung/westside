package com.example.spacetrader.Entity;

public abstract class NonPlayerCharacter extends Character{
    public NonPlayerCharacter(Ship ship) {
        super("NPC", ship);
    }
    public abstract void interactWithPlayer(Player player);
}
