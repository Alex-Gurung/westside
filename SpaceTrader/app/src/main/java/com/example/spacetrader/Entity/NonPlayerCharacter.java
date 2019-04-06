package com.example.spacetrader.Entity;

/**
 * NPC (Non Player Character) class
 * these characters simply interact with the player
 */
public abstract class NonPlayerCharacter extends Character{
    NonPlayerCharacter(Ship ship) {
        super(ship);
    }

    /**
     * abstract class for NPCs to interact with the player
     * @param player the player the NPC is interacting with
     */
    public abstract void interactWithPlayer(Player player);
}