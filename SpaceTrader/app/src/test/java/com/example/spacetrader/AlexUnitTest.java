package com.example.spacetrader;

import com.example.spacetrader.Entity.FirebaseActor;
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

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AlexUnitTest {
    private static FirebaseActor firebaseActor;

    @Test
    public void checkValidScoreStringNotUniquePlayerScore() {
        for (int i = 0; i < 1000; i++) {
            Random r  = new Random();
            int number_values = r.nextInt();
            String[] scores_list = new String[number_values];
            for (int j = 0; j < number_values; j++) {
                scores_list[j] = "" + r.nextDouble();
            }
            Double my_score = Double.parseDouble(scores_list[0]);
            String scores = String.join(", ", scores_list);
            assertEquals(scores, firebaseActor.updateScore(scores, my_score));
        }
    }
}



