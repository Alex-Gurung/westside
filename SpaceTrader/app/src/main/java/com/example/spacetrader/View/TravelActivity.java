package com.example.spacetrader.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.R;

public class TravelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 2 seconds
                int scenario = 0;
                if (scenario == 0) {
                    Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                    startActivityForResult(intent, 2);
                }
            }
        }, 2000);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 2) {
            finish();
        }
    }
}
