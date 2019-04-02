package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.Entity.Universe;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.ConfigurationViewModel;
import com.example.spacetrader.ViewModel.TradingViewModel;
import com.example.spacetrader.ViewModel.UniverseViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;


/**
 * Activity class that literally just shows a cool travel warping jpg post-travel
 */
public class TravelActivity extends AppCompatActivity {
    private ConfigurationViewModel configurationViewModel;


    private TradingViewModel tradingViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        tradingViewModel= ViewModelProviders.of(this).get(TradingViewModel.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 1 second

            }
        }, 1000);
        Random r = new Random();
        int scenario = r.nextInt(3);
        switch (scenario) {
            case 0:
                Toast.makeText(TravelActivity.this, "Traveled safely", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(TravelActivity.this, "You lost 200 credits", Toast.LENGTH_SHORT).show();
                tradingViewModel.setPlayerCredits(tradingViewModel.getPlayerCredits() - 200);
                break;
            case 2:
                Toast.makeText(TravelActivity.this, "You gained 200 credits", Toast.LENGTH_SHORT).show();
                tradingViewModel.setPlayerCredits(tradingViewModel.getPlayerCredits() + 200);
                break;
        }
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 2 seconds
                Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                startActivityForResult(intent, 2);
            }
        }, 3000);
    }

    /**
     *
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 2) {
            Toast toast=Toast.makeText(getApplicationContext(),"Game Saved",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
            finish();

        }
    }


}
