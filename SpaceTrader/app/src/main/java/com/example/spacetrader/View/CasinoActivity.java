package com.example.spacetrader.View;

import java.util.Random;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.Trader;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

import java.util.List;

public class CasinoActivity extends AppCompatActivity {

    private double playerCredits;
    private TextView credits;
    private final MarketAdapter adapter = new MarketAdapter();
    private List<Good> marketList;
    private Random rand;

    private TradingViewModel tradingViewModel;
    private Trader trader;

    /**
     * method that instantiates the viewable attributes that the user can see on the screen
     *
     * @param savedInstanceState of type Bundle that represents a saved instance of the class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casino);
        credits = findViewById(R.id.player_credits);
        tradingViewModel = ViewModelProviders.of(this).get(TradingViewModel.class);
        Double d = tradingViewModel.getPlayerCredits();
        credits.setText(d.toString());







        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 3) {
            Log.d("Hello", "Hello World");
        }
    }

}