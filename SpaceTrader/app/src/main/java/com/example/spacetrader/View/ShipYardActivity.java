package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.Entity.Ship;
import com.example.spacetrader.Entity.ShipType;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

import java.util.Arrays;
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

        Button backButton = findViewById(R.id.ShipYard_Back_Button);
        Button refuelMaxButton = findViewById(R.id.ShipYard_Refuel_Max_Button);

        RecyclerView recyclerView = findViewById(R.id.ship_list);//first grab a reference to the widget
        //Set the layout manager for the list to just be a vertical list of stuff
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);//Setup the adapter for the view
        adapter.setShipList();
        adapter.setOnShipClickListener(this::upgradeShipType);
        updateCredits();

        backButton.setOnClickListener(v -> finish());
        refuelMaxButton.setOnClickListener(v -> {
            refuelMax();
            updateCredits();
        });
    }

    private void upgradeShipType(int position) {
        ShipType shipType = Arrays.asList(ShipType.values()).get(position);
        boolean upgradeSuccessful = tradingViewModel.upgradeShip(shipType);
        if (upgradeSuccessful) {
            Toast.makeText(getApplicationContext(), "Ship upgraded", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Ship not upgraded", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateCredits() {
        playerCredits = tradingViewModel.getPlayerCredits();
        credits = findViewById(R.id.shipyard_credit_input);
        String f = String.format(Locale.getDefault(), "%.2f", playerCredits);
        credits.setText(f);
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
