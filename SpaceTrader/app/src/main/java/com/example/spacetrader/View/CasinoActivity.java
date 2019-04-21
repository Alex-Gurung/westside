package com.example.spacetrader.View;

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
import java.util.Random;

public class CasinoActivity extends AppCompatActivity {

    private double playerCredits;
    private TextView credits;
    private final MarketAdapter adapter = new MarketAdapter();
    private List<Good> marketList;

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
        TextView slot1 = findViewById(R.id.slot_1);
        TextView slot2 = findViewById(R.id.slot_2);
        TextView slot3 = findViewById(R.id.slot_3);
        slot1.setText("Roll");
        slot2.setText("Roll");
        slot3.setText("Roll");


        Button roll = findViewById(R.id.roll_button);
        roll.setOnClickListener(v -> {
            if (tradingViewModel.getPlayerCredits() >= 50) {
                Random r = new Random();
                tradingViewModel.setPlayerCredits(tradingViewModel.getPlayerCredits() - 50);
                Double d2 = tradingViewModel.getPlayerCredits();
                credits.setText(d2.toString());
                slot1.setText("" + (r.nextInt(3) + 1));
                slot2.setText("" + (r.nextInt(3) + 1));
                slot3.setText("" + (r.nextInt(3) + 1));
                if (slot1.getText().toString().equals(slot2.getText().toString())
                        && slot2.getText().toString().equals(slot3.getText().toString())) {
                    Toast.makeText(this, "Winner: " + (Double.parseDouble(slot1.getText().toString()) * 500) + " credits", Toast.LENGTH_SHORT).show();
                    tradingViewModel.setPlayerCredits(tradingViewModel.getPlayerCredits() + (Double.parseDouble(slot1.getText().toString()) * 500));
                    Double d3 = tradingViewModel.getPlayerCredits();
                    credits.setText(d3.toString());
                } else {
                    Toast.makeText(this, "You Lose Try Again?", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Not Enough Credits", Toast.LENGTH_SHORT).show();
            }
        });

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