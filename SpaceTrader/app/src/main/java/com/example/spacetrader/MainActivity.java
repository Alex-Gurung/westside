package com.example.spacetrader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.spacetrader.View.ConfigurationActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "This is a test", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);

        /* Button that moves from MainActivity to ConfigurationActivity*/
        Button goToConfig = (Button) findViewById(R.id.goToConfigButton);

        goToConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                startActivity(intent);
            }
        });

    }
}
