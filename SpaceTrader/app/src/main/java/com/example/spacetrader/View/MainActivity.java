package com.example.spacetrader.View;


import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Location;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.Universe;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.ConfigurationViewModel;
import com.google.firebase.FirebaseApp;
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

import static android.support.constraint.Constraints.TAG;


public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button loadGameButton;
    private Button highScoreButton;
    private String scoreString = "";
    private DatabaseReference myRef;


    public void getFile() {
        try {
            File file = new File(this.getFilesDir(), "data.bin");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Game o = (Game) in.readObject();
            ConfigurationViewModel c = new ConfigurationViewModel(getApplication());
            c.loadGame(o);
            Log.d("Load", o.getPlayer().toString());
        } catch (Exception e){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpFirebase();

        /* Button that moves from MainActivity to ConfigurationActivity*/
        startButton = findViewById(R.id.start_button);
        loadGameButton = findViewById(R.id.load_game_button);
        highScoreButton = findViewById(R.id.high_score_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), ConfigurationActivity.class);
                startActivity(intent);
            }
        });

        highScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Global High Scores");
                alertDialogBuilder.setMessage(scoreString);
                alertDialogBuilder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        loadGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFile();
                Intent intent = new Intent( getApplicationContext(), UniverseActivity.class);
                startActivity(intent);
            }
        });
    }
    public void setUpFirebase() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("scores");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                updateScore(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void updateScore(String value) {
        scoreString = value;
        if (scoreString != null) {
            String[] scores_list = scoreString.split(", ");
            ArrayList<Double> scores_doubles = new ArrayList<>();
            for (String s : scores_list) {
                if (s.length() > 0) {
                    scores_doubles.add(Double.parseDouble(s));
                }
            }
            Collections.sort(scores_doubles);
            Collections.reverse(scores_doubles);
            scoreString = "";
            for (Double d : scores_doubles) {
                scoreString += String.format("%.2f\n", d);
            }
        }
        Log.d(TAG, "New High Scores: " + value);
    }
}
