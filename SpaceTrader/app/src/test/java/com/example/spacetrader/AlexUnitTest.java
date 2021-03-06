package com.example.spacetrader;

import com.example.spacetrader.Entity.FirebaseActor;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AlexUnitTest {
    private FirebaseActor firebaseActor;

    /**
     * Sets up the firebaseActor so we can test the method
     */
    @Before
    public void setUp(){
        firebaseActor = new FirebaseActor();
    }

    /**
     * test for empty score string
     */
    @Test
    public void checkEmptyScoreString() {
        double my_score = 1.0;
        String scores = "";
        String expected = "" + my_score;
        String actual = firebaseActor.updateScore(scores, my_score);
        assertEquals(expected, actual); //as our score isn't unique, want the same thing out
    }

    /**
     * test for non-double (invalidish )score strings of length [0, LISTBOUND)
     * when the players current score is unique
     */
    @Test
    public void checkNonDoubleScoreStringUniquePlayerScore() {
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

    /**
     * test for valid score strings of length [0, 100)
     * when the players current score is unique
     */
    @Test
    public void checkValidScoreStringUniquePlayerScore() {
        int LISTBOUND = 100;
        Random r  = new Random();
        // Create array of random length
        int number_values = r.nextInt(LISTBOUND);
        String[] scores_list = new String[number_values];
        // To make sure our score is unique, keep a hash set of scores
        Collection<Double> existing_numbers = new HashSet<>();
        for (int j = 0; j < number_values; j++) {
            Double toAdd = r.nextDouble();
            //convert double to a string and add to set and scores_list
            scores_list[j] = "" + toAdd;
            existing_numbers.add(toAdd);
        }
        // Get a random double that isn't in the list
        double my_score = r.nextDouble();
        while (existing_numbers.contains(my_score)) {
            my_score = r.nextDouble();
        }
        // Create expected and actual output
        String scores = String.join(", ", scores_list);
        String actual = firebaseActor.updateScore(scores, my_score);
        String expected = scores + ", " + my_score; //scores, with added score on end
        assertEquals(expected, actual);

    }

    /**
     * Tests for invalid score strings of length [0, 100)
     * when the players current score is not unique
     */
    @Test
    public void checkValidScoreStringNotUniquePlayerScore() {
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
        String scores = String.join(", ", scores_list);
        /*
        * String is not unique, so lists should not change
        * */
        assertEquals(scores, firebaseActor.updateScore(scores, my_score));
    }

    /**
     * Tests for null score string
     */
    @Test
    public void checkNullScoreString() {
        Random r  = new Random();
        double my_score = r.nextDouble();
        String expected = "" + my_score;
        // expect just my score back, as scoresString was null
        assertEquals(expected, firebaseActor.updateScore(null, my_score));
    }
}



