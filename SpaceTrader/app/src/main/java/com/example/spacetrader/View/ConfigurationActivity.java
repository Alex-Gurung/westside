package com.example.spacetrader.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.spacetrader.R;

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
          Set up the adapter to display the allowable majors in the spinner
         */

    }

    public void onBeginPressed(View view) {

    }
}
