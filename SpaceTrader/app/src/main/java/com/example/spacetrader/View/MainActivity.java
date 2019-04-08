package com.example.spacetrader.View;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import android.widget.Toast;
import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.ConfigurationViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static android.support.constraint.Constraints.TAG;

/**
 * The initial screen users see - allows you to load a game that was in progress or start a new one
 */
public class MainActivity extends AppCompatActivity {

    private String scoreString = "";

    /**
     * method for loading the game
     * @throws Exception if the loading does not work correctly
     */
    private void getFile() throws Exception {

        File file = new File(this.getFilesDir(), "data.bin");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Game o = (Game) in.readObject();
        ConfigurationViewModel c = new ConfigurationViewModel(getApplication());
        c.loadGame(o);
        Log.d("Load", o.getPlayer().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpFirebase();

        /* Button that moves from MainActivity to ConfigurationActivity*/
        Button startButton = findViewById(R.id.start_button);
        Button loadGameButton = findViewById(R.id.load_game_button);
        Button highScoreButton = findViewById(R.id.high_score_button);

        startButton.setOnClickListener(v -> {
            Intent intent = new Intent( getApplicationContext(), ConfigurationActivity.class);
            startActivity(intent);
        });

        highScoreButton.setOnClickListener(v -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setTitle("Global High Scores");
            alertDialogBuilder.setMessage(scoreString);
            alertDialogBuilder.setNegativeButton("Back", (dialog, which) -> dialog.dismiss());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        });
        loadGameButton.setOnClickListener(v -> {
            try {
                getFile();
                Intent intent = new Intent( getApplicationContext(), UniverseActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Could not load game. Did you remember to save?",Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent( getApplicationContext(), ConfigurationActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * method that sets up the Firebase database
     */
    private void setUpFirebase() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("scores");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String cur_value = dataSnapshot.getValue(String.class);
                if (!scoreString.equals(cur_value)) {
                    updateScore(cur_value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    /**
     * method that updates the high scores
     * @param value the new score value to update
     */
    private void updateScore(String value) {
        scoreString = value;
        if (scoreString != null) {
            String[] scores_list = scoreString.split(", ");
            List<Double> scores_doubles = new ArrayList<>();
            for (String s : scores_list) {
                if (!s.isEmpty()) {
                    scores_doubles.add(Double.parseDouble(s));
                }
            }
            Collections.sort(scores_doubles);
            Collections.reverse(scores_doubles);
            scoreString = "";
            for (Double d : scores_doubles) {
                /* Could use StringBuilder for more efficient concatenation, but String is more
                *  effective for our specific usage
                */
                scoreString += String.format(Locale.getDefault(), "%.2f\n", d);
            }
        }
    }
}
