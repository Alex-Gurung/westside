package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

/**
 * Activity in which the player can choose to buy/sell goods and refuel their ship
 */
public class SpacePortActivity extends AppCompatActivity {

    private TextView credits;
    private TradingViewModel tradingViewModel;

    private static final int BUY_OR_SELL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_port);

        tradingViewModel = ViewModelProviders.of(this).get(TradingViewModel.class);
        String SolarSystemStats = tradingViewModel.getSolarSystemStats();

        Button backButton = findViewById(R.id.SpacePort_Back_Button);
        Button sellGoodsButton = findViewById(R.id.SpacePort_Sell_Goods_Button);
        Button buyGoodsButton = findViewById(R.id.SpacePort_Buy_Goods_Button);
        Button shipYardButton = findViewById(R.id.SpacePort_ShipYard_Button);
        credits = findViewById(R.id.SpacePort_Credits_Text_Box);
        TextView solarSystemText = findViewById(R.id.SpacePort_SolarSystem_Stats);
        String f = ""+tradingViewModel.getPlayerCredits();
        credits.setText(f);
        solarSystemText.setText(SolarSystemStats);


        //sends user back to solar system screen on pressing the back button
        backButton.setOnClickListener(v -> finish());
        shipYardButton.setOnClickListener(v -> {
            Intent intent = new Intent( getApplicationContext(), ShipYardActivity.class);
            startActivityForResult(intent, BUY_OR_SELL);
        });
        sellGoodsButton.setOnClickListener(v -> {
            Intent intent = new Intent( getApplicationContext(), SellGoodsActivity.class);
            startActivityForResult(intent, BUY_OR_SELL);
        });
        buyGoodsButton.setOnClickListener(v -> {
            Intent intent = new Intent( getApplicationContext(), BuyMarketGoodsActivity.class);
            startActivityForResult(intent, BUY_OR_SELL);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == BUY_OR_SELL) {
            String f = ""+tradingViewModel.getPlayerCredits();
            credits.setText(f);
        }
    }
}
