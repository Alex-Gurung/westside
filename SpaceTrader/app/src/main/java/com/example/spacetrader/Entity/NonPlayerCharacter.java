package com.example.spacetrader.Entity;

abstract class NonPlayerCharacter extends Character{
    NonPlayerCharacter(Ship ship) {
        super("NPC", ship);
    }
    public abstract void interactWithPlayer(Player player);
}