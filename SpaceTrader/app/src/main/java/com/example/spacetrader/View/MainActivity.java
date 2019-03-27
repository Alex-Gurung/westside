package com.example.spacetrader.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spacetrader.R;
import com.google.firebase.FirebaseApp;


public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button loadGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Button that moves from MainActivity to ConfigurationActivity*/
        startButton = findViewById(R.id.start_button);
        loadGameButton = findViewById(R.id.load_game_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), ConfigurationActivity.class);
                startActivity(intent);
            }
        });
    }
}
