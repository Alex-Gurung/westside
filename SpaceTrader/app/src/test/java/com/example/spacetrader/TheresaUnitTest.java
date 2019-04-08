package com.example.spacetrader;


import com.example.spacetrader.Entity.Location;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.Ship;
import com.example.spacetrader.Entity.ShipType;
import com.example.spacetrader.Entity.SolarSystem;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class TheresaUnitTest {
    private Player player;
    private Ship ship;

    @Before
    public void setUpRefuelByCredits(){
        ship = new Ship(ShipType.GNAT);
        //GNAT's maxDist = 14;
        //GNAT's fuelPrice = 11;
        player = new Player(4,4,4,4,
                            "player", ship, new SolarSystem( new Location(10,15)), 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNegativeCreditsException() {
        player.refuelByCredits(-100);
    }

    @Test
    public void testNotEnoughCreditsCannotRefuel() {
        player.setCredits(0);
        Assert.assertFalse(player.refuelByCredits(10));
    }

    @Test
    public void testEnoughCreditsRefuelMax() {
        player.setCredits(1000);
        ship.refuel(-0.1);
        double expectedCredits = 1000.0 - ((1.0 - ship.getFuel()) * (11 * 14));
        Assert.assertTrue(player.refuelByCredits(100.0));
        Assert.assertEquals(1.0, ship.getFuel(),0.001);
        Assert.assertEquals(expectedCredits, player.getCredits(),0.001);
    }

    @Test
    public void testEnoughCreditsRefuel() {
        player.setCredits(100);
        ship.refuel(-0.9);
        double expectedFuel = 0.1 + (100.0 / (14 * 11));
        Assert.assertTrue(player.refuelByCredits(100.0));
        Assert.assertEquals(expectedFuel, ship.getFuel(),0.001);
        Assert.assertEquals(0, player.getCredits(),0.001);
    }

}
