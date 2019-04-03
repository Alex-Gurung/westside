package com.example.spacetrader;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.GoodType;
import com.example.spacetrader.Entity.Player;
import com.example.spacetrader.Entity.Resource;
import com.example.spacetrader.Entity.Ship;
import com.example.spacetrader.Entity.ShipType;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.TechLevel;
import com.example.spacetrader.Entity.Trader;
import com.example.spacetrader.Entity.TraderCapability;
import com.example.spacetrader.Entity.Universe;
import com.example.spacetrader.Model.Repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AlexUnitTest {
    private static Repository repository;

    @Before
    public void setupSetRepo() {
        repository = new Repository();
    }

    @Test
    public void checkUpdateScore() {

    }
}



