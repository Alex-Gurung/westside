package com.example.spacetrader;

import com.example.spacetrader.Entity.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
/**
 * Example local unit test, which will execute on the development machine (host).
 * DO FACILITATE TRADE
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AmanUnitTest {
    private SolarSystem solarSystem;
    private Player pl;
    private Good testGood;
    private SpacePort spacePort;

    @Before
    public void setupFacilitateTrade() {
        solarSystem = new SolarSystem(new Location(0,0), PoliticalSystem.CAPITALISTSTATE, TechLevel.AGRICULTURAL, "name");
        solarSystem.setResource(Resource.MINERALRICH);
        pl = new Player(4,4,4,4, "player", new Ship(ShipType.GNAT), solarSystem, 0);
        spacePort = new SpacePort(TechLevel.AGRICULTURAL, Resource.MINERALRICH);
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
    public void testPlayerBuy() {
        pl.setCredits(1000);
        testGood.setPrice(200);
        pl.buy(testGood);
        Assert.assertTrue(Math.abs(pl.getCredits() -800) < 0.00001);
        Assert.assertTrue(pl.getCargo()[0].equals(testGood));
    }

    @Test
    public void testPlayerSell() {
        pl.setCredits(0);
        testGood.setPrice(200);
        Ship ship = pl.getMyShip();
        ship.addCargo(testGood);
        pl.sell(testGood);
        Assert.assertTrue(Math.abs(pl.getCredits() - 200) < 0.00001);
        Assert.assertTrue(pl.getCargo()[0] == null);
    }

    @Test
    public void testSpacePortCanBuy() {
        Assert.assertTrue(spacePort.canBuy(testGood));
    }

    @Test
    public void testSpacePortCannotBuy() {
        Good highTech = new Good(GoodType.ROBOT);
        SpacePort lowTech = new SpacePort(TechLevel.PREAGRICULTURAL, Resource.DESERT);
        Assert.assertFalse(lowTech.canBuy(highTech));
    }

    @Test
    public void testSpacePortCanSell() {
        Good firstGood = spacePort.getCargo()[0];
        Assert.assertTrue(spacePort.canSell(firstGood));
    }

    @Test
    public void testSpacePortCannotSellTechLevel() {
        Good highTech = new Good(GoodType.ROBOT);
        SpacePort lowTech = new SpacePort(TechLevel.PREAGRICULTURAL, Resource.DESERT);
        Assert.assertFalse(lowTech.canSell(highTech));
    }

    @Test
    public void testSpacePortCannotSellHasNoCargo() {
        Good firstGood = spacePort.getCargo()[0];
        Good[] spacePortCargo = spacePort.getCargo();
        for(Good g: spacePortCargo) {
            spacePort.sell(g);
        }
        Assert.assertFalse(spacePort.canSell(firstGood));
    }

    @Test
    public void canTrade() {
        spacePort = new SpacePort(TechLevel.PREAGRICULTURAL, Resource.ARTISTIC);
        testGood = new Good(GoodType.WATER);
        Good[] plCargo = pl.getCargo();
        for(int i = 0; i < plCargo.length; i++) {
            pl.sell(plCargo[0]);
        }
        pl.buy(testGood);
        Assert.assertTrue(Game.facilitateTrade(testGood, spacePort,pl)); //pl sells its only water
        Assert.assertFalse(Game.facilitateTrade(testGood, spacePort, pl)); //pl has no more goods
        for (int i = 0; i < plCargo.length; i++) {
            Assert.assertTrue(Game.facilitateTrade(testGood, pl, spacePort)); //pl buys water
        }
        Assert.assertFalse(Game.facilitateTrade(testGood, pl, spacePort)); //pl cargo bay is full
        pl.sell(testGood);
        testGood = new Good(GoodType.ROBOT);
        testGood.setPrice(10.0);
        pl.buy(testGood);
        Assert.assertFalse(Game.facilitateTrade(testGood, spacePort,pl)); //too low of a techlevel
        pl.sell(testGood);
        pl.setCredits(1.0);
        testGood = new Good(GoodType.WATER);
        testGood.setPrice(10.0);
        Assert.assertFalse(Game.facilitateTrade(testGood, pl, spacePort)); //pl has not enough money
        for(int i = 0; i < plCargo.length; i++) {
            pl.sell(plCargo[0]);
        } //cargo empty
        Assert.assertFalse(Game.facilitateTrade(testGood, spacePort, pl)); //pl has no goods
    }

    @AfterClass
    public static void goodBoiAlert() {
        System.out.println("yOu PaSsEd AlL tHe TeStS");
    }
}



