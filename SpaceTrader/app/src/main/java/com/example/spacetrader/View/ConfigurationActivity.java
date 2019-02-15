package com.example.spacetrader.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spacetrader.Entity.GameDifficulty;
import com.example.spacetrader.R;

import java.util.Arrays;

public class ConfigurationActivity extends AppCompatActivity {

    private EditText nameField;
    private Spinner difficultySpinner;

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

        /*
          Set up the adapter to display the allowable difficulties in the spinner
         */
        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arrays.asList(GameDifficulty.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);



    }

    public void onBeginPressed(View view) {
        if (getTotalSkillPoints() != 16) {
            Toast.makeText(this, "Skill points are not 16", Toast.LENGTH_SHORT).show();
        }
    }

    public int getTotalSkillPoints() {
        EditText engineer = findViewById(R.id.engineer_skill_pts_input);
        EditText trader = findViewById(R.id.trader_skill_pts_input);
        EditText fighter = findViewById(R.id.fighter_skill_pts_input);
        EditText pilot = findViewById(R.id.pilot_skill_pts_input);
        return  Integer.parseInt(engineer.getText().toString())
                + Integer.parseInt(trader.getText().toString())
                + Integer.parseInt(fighter.getText().toString())
                + Integer.parseInt(pilot.getText().toString());
    }


}
