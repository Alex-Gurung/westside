package com.example.spacetrader;
import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Location;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.PoliticalSystem;
import com.example.spacetrader.Entity.Ship;
import com.example.spacetrader.Entity.ShipType;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.TechLevel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test that makes sure that the player has the means and requirements to change or upgrade
 * their ship
 *
 */
public class JadenUnitTest {
    private Game game;
    private SolarSystem solarSystem;
    private Ship upgrade;
    private Ship downgrade;
    private Ship beginning;

    @Before
    public void setUpGame() {
        beginning = new Ship(ShipType.GNAT);
        solarSystem = new SolarSystem(new Location(0, 7),
                PoliticalSystem.CAPITALISTSTATE, TechLevel.HITECH, "Generic Name");

        game = new Game(new Player(4, 4, 4, 4,
                "Jaden", beginning, solarSystem, 1000.0));

        upgrade = new Ship(ShipType.BUMBLEBEE);
        downgrade = new Ship(ShipType.FLEA);
        // ship attributes:                   travel distance, cargo holds, fuel price
        //beginning ship stats: GNAT("Gnat", travel distance: 14, hold: 15, fuel price: 11)
        //downgraded ship stats: FLEA("Flea", travel distance: 20, hold: 4, fuel price: 10)
        //upgraded ship stats: BUMBLEBEE("Bumblebee", travel distance: 15, hold: 20, fuel price: 12)
    }

    @Test
    public void testCanUpgrade() {
        //-----------------------checks that cargo hold for upgrade is large enough-----------------
        int i = game.getPlayer().getCargo().length;
        int j = upgrade.getCargo().length;
        int k = downgrade.getCargo().length;
        boolean canUpgrade;
        game.setPlayerCredits(1000000); //factors out credits in the test
        Assert.assertTrue(i <= j);
        Assert.assertTrue(i >= k);
        //should upgrade with no problem since cargo hold is larger
        canUpgrade = game.changePlayerShipType(upgrade.getShiptype());
        Assert.assertTrue("Player should have been able to upgrade with no problem ", canUpgrade);

        //should not upgrade since downgrade has smaller cargo hold
        canUpgrade = game.changePlayerShipType(downgrade.getShiptype());
        Assert.assertFalse("Player should not have been able to upgrade ", canUpgrade);


        //----------------------- checks for enough credits to upgrade------------------------------
        game.setPlayerCredits(0); // should not be able to upgrade with this value
        Assert.assertTrue(game.getPlayerCredits() < upgrade.getPrice());

        //value from upgrade ship should stay same/not upgrade
        canUpgrade = game.changePlayerShipType(upgrade.getShiptype());
        Assert.assertFalse("Player should not have been able to upgrade ", canUpgrade);

        game.setPlayerCredits(10000000); //should be able to upgrade
        Assert.assertTrue(game.getPlayerCredits() >= upgrade.getPrice());
        //Value from upgrade ship should change to upgrade
        canUpgrade = game.changePlayerShipType(upgrade.getShiptype());
        Assert.assertTrue("Player should have been able to upgrade with no problem", canUpgrade);
    }
}
