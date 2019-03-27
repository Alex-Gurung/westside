package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

import java.util.Random;


public class TravelActivity extends AppCompatActivity {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 2) {
            finish();
        }
    }
}
