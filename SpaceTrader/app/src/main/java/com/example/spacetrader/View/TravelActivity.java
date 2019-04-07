package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

import java.util.Random;


/**
 * Activity class that literally just shows a cool travel warping jpg post-travel
 */
public class TravelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        TradingViewModel tradingViewModel = ViewModelProviders.of(this).get(TradingViewModel.class);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Actions to do after 1 second

        }, 1000);
        Random r = new Random();
        int scenario = r.nextInt(3);
        int diff = tradingViewModel.getGame().getGameDifficulty().ordinal();
        switch (scenario) {
            case 0:
                Toast.makeText(TravelActivity.this, "Traveled safely",
                        Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(TravelActivity.this, "You lost 100 credits",
                        Toast.LENGTH_SHORT).show();
                tradingViewModel.setPlayerCredits(tradingViewModel.getPlayerCredits() - 100);
                break;
            case 2:
                double gainCredits = (600 - 100*diff);
                Toast.makeText(TravelActivity.this, "You gained " + gainCredits +
                        " credits", Toast.LENGTH_SHORT).show();
                tradingViewModel.setPlayerCredits(
                        tradingViewModel.getPlayerCredits() + (600 - 100*diff)
                );
                break;
        }
        handler.postDelayed(() -> {
            // Actions to do after 2 seconds
            Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
            startActivityForResult(intent, 2);
        }, 3000);
    }

    /**
     *
     *
     * @param requestCode a thing
     * @param resultCode another thing
     * @param data a last thing
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }


}
