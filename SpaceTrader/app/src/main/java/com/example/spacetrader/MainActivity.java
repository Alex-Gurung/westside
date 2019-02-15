package com.example.spacetrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "This is a test", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_configuration);

        startButton = findViewById(R.id.start_button);
    }
}
