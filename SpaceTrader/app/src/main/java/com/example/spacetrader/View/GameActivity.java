package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.ConfigurationViewModel;

public class GameActivity extends AppCompatActivity {

    private ConfigurationViewModel configurationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        configurationViewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);


        Log.d("GameActivity", configurationViewModel.getGame().getUniverse().toString());
    }
}
