package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
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

import java.util.ArrayList;
import java.util.List;

public class SellGoodsActivity extends AppCompatActivity {
    private Button backButton;
    private TextView credits;
    private final CargoAdapter adapter = new CargoAdapter();
    private List<Good> cargo;
    private double playerCredits;

    private TradingViewModel tradingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_goods);

        tradingViewModel = ViewModelProviders.of(this).get(TradingViewModel.class);

        playerCredits = tradingViewModel.getPlayerCredits();

        backButton = findViewById(R.id.Cargo_Back_Button);
        backButton.setOnClickListener(v -> {
            finish();
        });
        credits = findViewById(R.id.cargo_credit_input);
        credits.setText("" + playerCredits);

        //first grab a reference to the widget
        RecyclerView recyclerView = findViewById(R.id.cargo_list);
        //Set the layout manager for the list to just be a vertical list of stuff
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //Setup the adapter for the view
        recyclerView.setAdapter(adapter);

        cargo = setDummyGoods();
        Good[] playerGoods = tradingViewModel.getCargo(tradingViewModel.getPlayer());
        if (playerGoods != null) {
            cargo = new ArrayList<Good>();
            for (int i = 0; i < playerGoods.length; i++) {
                if (playerGoods[i] != null) {
                    cargo.add(playerGoods[i]);
                }
            }
        }
        adapter.setCargoList(cargo);

        adapter.setOnCargoClickListener(new CargoAdapter.OnCargoGoodClickListener() {
            @Override
            public void onCargoGoodSell(int position) {
                sellItem(position);
            }
        });

    }
    public List<Good> setDummyGoods() {
        List<Good> list = new ArrayList<>();
        list.add(new Good(GoodType.MACHINE));
        list.add(new Good(GoodType.WATER));
        list.add(new Good(GoodType.ORE));
        return list;
    }
    public void sellItem(int position) {
        //update credits - make sure the goods have a price!!!
        Good toBuy = cargo.get(position);
        boolean isSold = tradingViewModel.facilitateTrade(toBuy, tradingViewModel.getSpacePort(), tradingViewModel.getPlayer());
        if (!isSold) {
            Toast.makeText(getApplicationContext(), "Could not sell item", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("NEW PLAYER CREDITS", "" + tradingViewModel.getPlayerCredits());
            playerCredits = tradingViewModel.getPlayerCredits();
            credits.setText("" + playerCredits);

            cargo.remove(cargo.get(position));

            adapter.notifyItemRemoved(position);
        }
    }
}
