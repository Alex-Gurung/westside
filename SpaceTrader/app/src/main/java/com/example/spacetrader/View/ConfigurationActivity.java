package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spacetrader.Entity.GameDifficulty;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.ConfigurationViewModel;

import java.util.Arrays;

/**
 * The activity that handles configuring the game - setting the name, skill points & game difficulty
 */
public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel configurationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);


        /*
         * Grab the dialog widgets so we can get info for later
         */
        Spinner difficultySpinner = findViewById(R.id.gameDifficulty_spinner);
        Button beginButton = findViewById(R.id.begin_button);
        Button cancelButton = findViewById(R.id.cancel_button);

        /*
         * Link begin button to its corresponding method
         */
        beginButton.setOnClickListener(v -> onBeginPressed());

        /*
         * Link cancel button to returning to main activity
         */

        cancelButton.setOnClickListener(v -> finish());

        /*
          Set up the adapter to display the allowable difficulties in the spinner
         */
        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Arrays.asList(GameDifficulty.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        configurationViewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
    }

    /**
     * the method that stipulates what happens when the user clicks the Begin button
     * ensures the skill points add up to 16
     */
    private void onBeginPressed() {
        EditText nameInput = findViewById(R.id.character_name_input);
        EditText fighterInput = findViewById(R.id.fighter_skill_pts_input);
        EditText engineerInput = findViewById(R.id.engineer_skill_pts_input);
        EditText traderInput = findViewById(R.id.trader_skill_pts_input);
        EditText pilotInput = findViewById(R.id.pilot_skill_pts_input);
        Spinner difficultyInput = findViewById(R.id.gameDifficulty_spinner);

        String name = nameInput.getText().toString();

        int fighter = Integer.parseInt("0" + fighterInput.getText().toString());
        int engineer = Integer.parseInt("0" +engineerInput.getText().toString());
        int pilot = Integer.parseInt("0" +pilotInput.getText().toString());
        int trader = Integer.parseInt("0" +traderInput.getText().toString());
        GameDifficulty difficulty = (GameDifficulty) difficultyInput.getSelectedItem();

        if (getTotalSkillPoints(fighter, engineer, pilot, trader) != 16) {
            Toast.makeText(ConfigurationActivity.this, "Skill points are not 16",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ConfigurationActivity.this, "Beginning your Space Journey!",
                    Toast.LENGTH_SHORT).show();
            configurationViewModel.initializeGame(name, fighter, engineer, pilot, trader,
                    difficulty);
            Intent intent = new Intent( getApplicationContext(), UniverseActivity.class);
            startActivity(intent);
        }
    }

    /**
     * method that calculates  the sum of the skill points
     *
     * @param fighter player's fighter skill points
     * @param engineer player's engineer skill points
     * @param pilot player's pilot skill points
     * @param trader player's trader skill points
     * @return the sum of the player's skill points
     */
    private int getTotalSkillPoints(int fighter, int engineer, int pilot, int trader) {
        return fighter + engineer + pilot + trader;
    }


}