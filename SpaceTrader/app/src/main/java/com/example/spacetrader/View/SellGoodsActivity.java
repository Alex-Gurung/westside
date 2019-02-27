package com.example.spacetrader.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.spacetrader.R;

public class SellGoodsActivity extends AppCompatActivity {
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_goods);
        backButton = findViewById(R.id.Cargo_Back_Button);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
            startActivity(intent);
        });
    }
}
