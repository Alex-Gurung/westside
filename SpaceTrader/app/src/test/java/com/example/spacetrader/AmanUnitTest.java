package com.example.spacetrader;

import com.example.spacetrader.Entity.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
/**
 * Example local unit test, which will execute on the development machine (host).
 * DO FACILITATE TRADE
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AmanUnitTest {
    SolarSystem solarSystem;
    Player pl;
    Good testGood;

    @Before
    public void setupFacilitateTrade() {
        solarSystem = new SolarSystem(new Location(0,0), PoliticalSystem.CAPITALISTSTATE, TechLevel.AGRICULTURAL, "name");
        solarSystem.setResource(Resource.MINERALRICH);
        pl = new Player(4,4,4,4, "player", new Ship(ShipType.GNAT), solarSystem, 0);
        testGood = new Good(GoodType.FOOD);
    }

    @Test
    public void testEmptyCargoNoCreditsCanBuy() {
        pl.setCredits(0);
        pl.setPrice(testGood);
        Assert.assertFalse(pl.canBuy(testGood));
    }

    @Test
    public void testEmptyCargoEnoughCreditsCanBuy() {
        pl.setCredits(1000);
        testGood.setPrice(10);
        Assert.assertTrue(pl.canBuy(testGood));
    }

    @Test
    public void testFullCargoEnoughCreditsPlayerCanBuy() {
        pl.setCredits(1000);
        testGood.setPrice(10);
        Ship ship = pl.getMyShip();
        while(ship.hasCargoSpace()) {
            ship.addCargo(new Good(GoodType.WATER));
        }
        Assert.assertFalse(pl.canBuy(testGood));
    }

    @Test
    public void testFullCargoNoCreditsPlayerCanBuy() {
        pl.setCredits(0);
        testGood.setPrice(10);
        Ship ship = pl.getMyShip();
        while(ship.hasCargoSpace()) {
            ship.addCargo(new Good(GoodType.WATER));
        }
        Assert.assertFalse(pl.canBuy(testGood));
    }

    @Test
    public void testNoCargoCanSell() {
        Assert.assertFalse(pl.canSell(testGood));
    }

    @Test
    public void testHasCorrectCargoCanSell() {
        Ship ship = pl.getMyShip();
        ship.addCargo(testGood);
        Assert.assertTrue(pl.canSell(testGood));
    }

    @Test
    public void testHasWrongCargoCanSell() {
        Ship ship = pl.getMyShip();
        ship.addCargo(testGood);
        Assert.assertFalse(pl.canSell(new Good(GoodType.MACHINE)));
    }

    @Test
    public void testPlayerBuyHasMoneyAndSpace() {
        pl.setCredits(1000);
        testGood.setPrice(200);
        pl.buy(testGood);
        Assert.assertTrue(Math.abs(pl.getCredits() -800) < 0.00001);
        Assert.assertTrue(pl.getCargo()[0].equals(testGood));
    }
}



