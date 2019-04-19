package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

import java.util.Locale;

/**
 * the activity that allows the user to refuel their ship and upgrade their shiptype
 */
public class ShipYardActivity extends AppCompatActivity {
    private TextView credits;
    private double playerCredits;
    private final ShipAdapter adapter = new ShipAdapter();

    private TradingViewModel tradingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shipyard);

        tradingViewModel = ViewModelProviders.of(this).get(TradingViewModel.class);

        playerCredits = tradingViewModel.getPlayerCredits();

        Button backButton = findViewById(R.id.ShipYard_Back_Button);
        backButton.setOnClickListener(v -> finish());
        credits = findViewById(R.id.shipyard_credit_input);
        String f = String.format(Locale.getDefault(), "%.2f", playerCredits);
        credits.setText(f);

        Button refuelMaxButton = findViewById(R.id.ShipYard_Refuel_Max_Button);
        refuelMaxButton.setOnClickListener(v -> {
            refuelMax();
            playerCredits = tradingViewModel.getPlayerCredits();
            String e = String.format(Locale.getDefault(), "%.2f", playerCredits);
            credits.setText(e);
        });
    }

    private void refuelMax() {
        boolean refueledMax = tradingViewModel.refuelMax();
        if(refueledMax) {
            Toast.makeText(getApplicationContext(), "Ship refueled fully",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Refueled using all credits",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
