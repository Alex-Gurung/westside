package com.example.spacetrader.Entity;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;

public class FirebaseActor {
    private String scoreString = "";
    private DatabaseReference myRef;
    private Game game;
    public FirebaseActor() {}
    public FirebaseActor(Game game) {
        this.game = game;
        setUpFirebase();
    }
    private void setUpFirebase() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("scores");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                scoreString = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


    /**
     * Gets the current value, and returns the string of the new value to put on firebase
     * e.g. ("1.2, 1000", 1.0) -> "1.2, 1000, 1.0"
     * @param database_value
     * @return string of value to send to myRef
     */
    public String updateScore(String database_value, double my_credits) {
        String toReturn;
        String my_score = "" + my_credits;
        scoreString = database_value;
        if (scoreString != null) {
            String[] scores = scoreString.split(", ");
            StringBuilder new_scores = new StringBuilder();
            boolean anyMatch = false;
            for (String s : scores) {
                if (s.length() > 0) {
                    if (s.equals(my_score)) {
                        anyMatch = true;
                    }
                    /* Could use StringBuilder for more efficient concatenation, but String is more
                     * effective for our specific usage
                     */
                    new_scores.append(s).append(", ");
                }
            }
            if (!anyMatch) {
                new_scores.append(my_score);
            } else {
                new_scores = new StringBuilder(new_scores.substring(0, new_scores.length() - 2));
            }
            toReturn = new_scores.toString();
        } else {
            toReturn = my_score;
        }
        Log.d(TAG, "New High Score: " + database_value);
        return toReturn;
    }
    public void updateFire() {
        if (this.game != null) {
            // try updating the list of scores with the current player credits
            // if they're not the same, then firebase needs to be updated
            String new_string = updateScore(scoreString, game.getPlayerCredits());
            if (!new_string.equals(scoreString)) {
                myRef.setValue(new_string);
                scoreString = new_string;
                Log.d("FIREBASE UPDATE: ", new_string);
            }
        }
    }
}
