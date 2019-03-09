package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

public class SpacePortActivity extends AppCompatActivity {

    private Button backButton;
    private Button sellGoodsButton;
    private Button buyGoodsButton;
    private Button shipYardButton;
    private TextView credits;
    private TextView solarSystemText;
    private TradingViewModel tradingViewModel;

    private static final int BUY_OR_SELL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_port);
        String SolarSystemStats = getIntent().getStringExtra("SOLARSTYSTEMSTATS");

        tradingViewModel= ViewModelProviders.of(this).get(TradingViewModel.class);

        backButton = findViewById(R.id.SpacePort_Back_Button);
        sellGoodsButton = findViewById(R.id.SpacePort_Sell_Goods_Button);
        buyGoodsButton = findViewById(R.id.SpacePort_Buy_Goods_Button);
        shipYardButton = findViewById(R.id.SpacePort_ShipYard_Button);
        credits = findViewById(R.id.SpacePort_Credits_Text_Box);
        solarSystemText = findViewById(R.id.SpacePort_SolarSystem_Stats);

        credits.setText(""+tradingViewModel.getPlayerCredits());
        solarSystemText.setText(SolarSystemStats);


        //sends user back to solar system screen on pressing the back button
        backButton.setOnClickListener(v -> {
            finish();
        });
        shipYardButton.setOnClickListener(v -> {
            Intent intent = new Intent( getApplicationContext(), ShipYardActivity.class);
            startActivity(intent);
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
            credits.setText(""+tradingViewModel.getPlayerCredits());
        }
    }
}
