package com.example.spacetrader;

import com.example.spacetrader.Entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class JonUnitTest {
    private Game game;
    private Player player;
    private SolarSystem solarSystem1;
    private SolarSystem solarSystem2;
    private SolarSystem solarSystem3;


    @Before
    public void setupFacilitateTravel() {
        solarSystem1 = new SolarSystem(new Location(0,0), PoliticalSystem.DEMOCRACY, TechLevel.HITECH, "Earth");
        solarSystem2 = new SolarSystem(new Location(4,3), PoliticalSystem.ANARCHY, TechLevel.AGRICULTURAL, "Earth");
        solarSystem3 = new SolarSystem(new Location(20,0), PoliticalSystem.DEMOCRACY, TechLevel.HITECH, "Earth");
        player = new Player(4,4,4,4,"Jon", new Ship(ShipType.GNAT), solarSystem1, 1000);
        game = new Game(player);
    }

    @Test
    public void getSolarSystemDistance() {
        Assert.assertTrue(solarSystem1.getDistance(solarSystem2) == 5);
        Assert.assertTrue(solarSystem2.getDistance(solarSystem3) == Math.hypot(4-20, 3-0));
        Assert.assertTrue(solarSystem3.getDistance(solarSystem1) == 20);
    }
    @Test
    public void getShipDistance() {
        Assert.assertTrue(player.getShipDistance() == ShipType.GNAT.getMaxDistance());
    }
    @Test
    public void playerCanTravel() {
        Assert.assertTrue(game.facilitateTravel(solarSystem1));
        Assert.assertTrue(game.facilitateTravel(solarSystem2));
    }
    @Test
    public void playerCannotTravel() {
        Assert.assertFalse(game.facilitateTravel(solarSystem3));
    }
    @Test
    public void makePlayerTravel() {
        Assert.assertTrue(player.getCurrentSolarSystem() == solarSystem1);
        player.travel(solarSystem2);
        Assert.assertTrue(player.getCurrentSolarSystem() == solarSystem2);
        player.travel(solarSystem1);
        Assert.assertTrue(player.getCurrentSolarSystem() == solarSystem1);
    }
    @Test
    public void changePricesOfGoods() {
        player.getMyShip().addCargo(new Good(GoodType.MACHINE));
        player.getMyShip().addCargo(new Good(GoodType.FIREARM));
        player.getMyShip().addCargo(new Good(GoodType.FOOD));
        player.getMyShip().addCargo(new Good(GoodType.WATER));
        player.getMyShip().addCargo(new Good(GoodType.ROBOT));
        player.travel(solarSystem2);
        Double[] prices = {795.0, 1118.75, 108.75, 33.0, 4343.75};
        player.setPrice(new Good(GoodType.MACHINE));
        player.setPrice(new Good(GoodType.FIREARM));
        int i = 0;
        while (i < 5) {
            Assert.assertTrue(player.getCargo()[i].getPrice() == prices[i]);
            i++;
        }
    }
}