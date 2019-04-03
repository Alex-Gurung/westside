package com.example.spacetrader;

import com.example.spacetrader.Entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 * DO FACILITATE TRADE
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AmanUnitTest {
    Game game;
    Player player;
    Ship ship;
    private List<Player> players;
    private List<SpacePort> spacePorts;

    @Before
    public void setupFacilitateTrade() {
        players = new ArrayList<>();
        for(ShipType s : ShipType.values()) {
            ship = new Ship(s);
            player = new Player(4,4,4,4, "player", ship, null, 1000);
        }
        game = new Game(player);
        spacePorts = new ArrayList<>();
        for(int i = 0; i < TechLevel.values().length; i++) {
            for(int j = 0; j < Resource.values().length; j++) {
                spacePorts.add(new SpacePort(TechLevel.values()[i], Resource.values()[j]));
            }
        }
    }

    @Test
    public void testEmptyCargoNoCreditsCanBuy() {
        SolarSystem solarSystem = new SolarSystem(new Location(0,0), PoliticalSystem.CAPITALISTSTATE, TechLevel.AGRICULTURAL, "name");
        solarSystem.setResource(Resource.MINERALRICH);
        Player pl = new Player(4,4,4,4, "player", new Ship(ShipType.GNAT), solarSystem, 0);
        Good testGood = new Good(GoodType.FOOD);
        pl.setPrice(testGood);
        Assert.assertFalse(pl.canBuy(testGood));
    }

    @Test
    public void testEmptyCargoEnoughCreditsCanBuy() {
        SolarSystem solarSystem = new SolarSystem(new Location(0,0), PoliticalSystem.CAPITALISTSTATE, TechLevel.AGRICULTURAL, "name");
        solarSystem.setResource(Resource.MINERALRICH);
        Player pl = new Player(4,4,4,4, "player", new Ship(ShipType.GNAT), solarSystem, 1000);
        Good testGood = new Good(GoodType.FOOD);
        testGood.setPrice(10);
        Assert.assertTrue(pl.canBuy(testGood));
    }

    @Test
    public void testFullCargoEnoughCreditsPlayerCanBuy() {
        SolarSystem solarSystem = new SolarSystem(new Location(0,0), PoliticalSystem.CAPITALISTSTATE, TechLevel.AGRICULTURAL, "name");
        solarSystem.setResource(Resource.MINERALRICH);
        Player pl = new Player(4,4,4,4, "player", new Ship(ShipType.GNAT), solarSystem, 1000);
        Good testGood = new Good(GoodType.FOOD);
        testGood.setPrice(10);
        Ship ship = pl.getMyShip();
        while(ship.hasCargoSpace()) {
            ship.addCargo(new Good(GoodType.WATER));
        }
        Assert.assertFalse(pl.canBuy(testGood));
    }

    @Test
    public void testFullCargoNoCreditsPlayerCanBuy() {
        SolarSystem solarSystem = new SolarSystem(new Location(0,0), PoliticalSystem.CAPITALISTSTATE, TechLevel.AGRICULTURAL, "name");
        solarSystem.setResource(Resource.MINERALRICH);
        Player pl = new Player(4,4,4,4, "player", new Ship(ShipType.GNAT), solarSystem, 0);
        Good testGood = new Good(GoodType.FOOD);
        testGood.setPrice(10);
        Ship ship = pl.getMyShip();
        while(ship.hasCargoSpace()) {
            ship.addCargo(new Good(GoodType.WATER));
        }
        Assert.assertFalse(pl.canBuy(testGood));
    }


}



