package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.GoodType;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ShipYardActivity extends AppCompatActivity {
    private Button backButton;
    private Button refuelMaxButton;
    private TextView credits;
    private double playerCredits;

    private TradingViewModel tradingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipyard);

        tradingViewModel = ViewModelProviders.of(this).get(TradingViewModel.class);

        playerCredits = tradingViewModel.getPlayerCredits();

        backButton = findViewById(R.id.ShipYard_Back_Button);
        backButton.setOnClickListener(v -> {
            finish();
        });
        credits = findViewById(R.id.shipyard_credit_input);
        credits.setText(new Double(playerCredits).toString());

        refuelMaxButton = findViewById(R.id.ShipYard_Refuel_Max_Button);
        refuelMaxButton.setOnClickListener(v -> {
            refuelMax();
            playerCredits = tradingViewModel.getPlayerCredits();
            credits.setText(new Double(playerCredits).toString());
        });
    }

    public void refuelMax() {
        boolean refueledMax = tradingViewModel.refuelMax();
        if(refueledMax) {
            Toast.makeText(getApplicationContext(), "Ship refueled fully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Try something else lol", Toast.LENGTH_SHORT).show();
        }
    }
}
