package com.example.spacetrader;

import com.example.spacetrader.Entity.FirebaseActor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AlexUnitTest {
    private static FirebaseActor firebaseActor;

    @Before
    public void setUp(){
        firebaseActor = new FirebaseActor();
    }

    @Test
    public void checkNonDoubleScoreStringUniquePlayerScore() {
        /*
        * test for valid score strings of length [0, LISTBOUND)
        * when the players current score is not unique
        */
        int LISTBOUND = 100;
        Random r  = new Random();
        // Want at least one element in the array
        int number_values = r.nextInt(LISTBOUND) + 1;
        String[] scores_list = new String[number_values];
        for (int j = 0; j < number_values; j++) {
            scores_list[j] = "a";
        }
        Double my_score = 1.0; // Score list has no doubles so score is unique
        String scores = String.join(", ", scores_list);
        String expected = scores + ", " + my_score;
        String actual = firebaseActor.updateScore(scores, my_score);
        assertEquals(expected, actual); //as our score isn't unique, want the same thing out
    }

    @Test
    public void checkValidScoreStringUniquePlayerScore() {
        /*
         * test for valid score strings of length [0, 100)
         * when the players current score is unique
         */
        int LISTBOUND = 100;
        Random r  = new Random();
        // Create array of random length
        int number_values = r.nextInt(LISTBOUND);
        String[] scores_list = new String[number_values];
        // To make sure our score is unique, keep a hash set of scores
        Set<Double> existing_numbers = new HashSet<>();
        for (int j = 0; j < number_values; j++) {
            Double toAdd = r.nextDouble();
            //convert double to a string and add to set and scores_list
            scores_list[j] = "" + toAdd;
            existing_numbers.add(toAdd);
        }
        // Get a random double that isn't in the list
        Double my_score = r.nextDouble();
        while (existing_numbers.contains(my_score)) {
            my_score = r.nextDouble();
        }
        // Create expected and actual output
        String scores = String.join(", ", scores_list);
        String actual = firebaseActor.updateScore(scores, my_score);
        String expected = scores + ", " + my_score; //scores, with added score on end
        assertEquals(expected, actual);

    }

    @Test
    public void checkValidScoreStringNotUniquePlayerScore() {
        /*
         * for invalid score strings of length [0, 100)
         * when the players current score is unique
         */
        int LISTBOUND = 100;
        Random r  = new Random();
        // Create array of random size (of at least 1)
        int number_values = r.nextInt(LISTBOUND) + 1;
        String[] scores_list = new String[number_values];
        Double[] existing_numbers = new Double[number_values];
        for (int j = 0; j < number_values; j++) {
            Double toAdd = r.nextDouble();
            scores_list[j] = "" + toAdd;
            existing_numbers[j] = toAdd;
        }
        int my_score_index = r.nextInt(number_values);
        double my_score = existing_numbers[my_score_index];
        String scores = String.join(" ", scores_list);
        /*
        * NOTE THIS IS THE SAME EXPECTED OUTPUT AS INVALID, BECAUSE WE DON'T CARE ABOUT
        * PRE-MALFORMED DATA
        * */
        String expected = scores + ", " + my_score; //scores, with added score on end
        assertEquals(expected, firebaseActor.updateScore(scores, my_score));
        // If there are no elements in the list, then we cannot test score that already exists
    }
}



