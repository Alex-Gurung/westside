package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spacetrader.Entity.GameDifficulty;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.ConfigurationViewModel;

import java.util.Arrays;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel configurationViewModel;

    private EditText nameField;
    private Spinner difficultySpinner;
    private Button beginButton;
    private Button cancelButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        /*
         * Grab the dialog widgets so we can get info for later
         */
        nameField = findViewById(R.id.character_name_input);
        difficultySpinner = findViewById(R.id.gameDifficulty_spinner);
        beginButton = (Button) findViewById(R.id.begin_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);

        /*
         * Link begin button to its corresponding method
         */
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBeginPressed();
            }
        });

        /*
         * Link cancel button to returning to main activity
         */

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        /*
          Set up the adapter to display the allowable difficulties in the spinner
         */
        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arrays.asList(GameDifficulty.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);
    }

    public void onBeginPressed() {
        EditText nameInput = findViewById(R.id.character_name_input);
        EditText fighterInput = findViewById(R.id.fighter_skill_pts_input);
        EditText engineerInput = findViewById(R.id.engineer_skill_pts_input);
        EditText traderInput = findViewById(R.id.trader_skill_pts_input);
        EditText pilotInput = findViewById(R.id.pilot_skill_pts_input);
        Spinner difficultyInput = findViewById(R.id.gameDifficulty_spinner);

        String name = nameInput.getText().toString();
        int fighter = Integer.parseInt(fighterInput.getText().toString());
        int engineer = Integer.parseInt(engineerInput.getText().toString());
        int pilot = Integer.parseInt(pilotInput.getText().toString());
        int trader = Integer.parseInt(traderInput.getText().toString());
        GameDifficulty difficulty = (GameDifficulty) difficultyInput.getSelectedItem();

        if (getTotalSkillPoints() != 16) {
            Toast.makeText(ConfigurationActivity.this, "Skill points are not 16", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ConfigurationActivity.this, "Beginning your Space Journey!", Toast.LENGTH_SHORT).show();
            ConfigurationViewModel.initializeGame(name, fighter, engineer, pilot, trader, difficulty);
        }
    }

    public int getTotalSkillPoints() {
        EditText engineer = findViewById(R.id.engineer_skill_pts_input);
        EditText trader = findViewById(R.id.trader_skill_pts_input);
        EditText fighter = findViewById(R.id.fighter_skill_pts_input);
        EditText pilot = findViewById(R.id.pilot_skill_pts_input);
        return Integer.parseInt(engineer.getText().toString())
                + Integer.parseInt(trader.getText().toString())
                + Integer.parseInt(fighter.getText().toString())
                + Integer.parseInt(pilot.getText().toString());
    }


}