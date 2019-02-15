package com.example.spacetrader.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class ConfigurationActivity {

    private EditText nameField;

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

        /*
          Set up the adapter to display the allowable majors in the spinner
         */

    }

    public void onBeginPressed(View view) {

    }
}
