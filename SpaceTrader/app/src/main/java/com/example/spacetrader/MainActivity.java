package com.example.spacetrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "This is a test", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
    }
}
