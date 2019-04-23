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
import com.example.spacetrader.Entity.Trader;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.SpacePortViewModel;

import java.util.ArrayList;
import java.util.List;

public class TraderActivity extends AppCompatActivity {

    private double playerCredits;
    private TextView credits;
    private final MarketAdapter adapter = new MarketAdapter();
    private List<Good> marketList;

    private SpacePortViewModel tradingViewModel;
    private Trader trader;

    /**
     * method that instantiates the viewable attributes that the user can see on the screen
     *
     * @param savedInstanceState of type Bundle that represents a saved instance of the class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_market_goods);

        tradingViewModel = ViewModelProviders.of(this).get(SpacePortViewModel.class);

        playerCredits = tradingViewModel.getPlayerCredits();
        trader = new Trader(tradingViewModel.getCurrentSolarSystem());

        Button backButton = findViewById(R.id.Market_Back_Button);
        backButton.setOnClickListener(v -> finish());

        credits = findViewById(R.id.market_credit_input);
        String f = "" + playerCredits;
        credits.setText(f);

        TextView title = findViewById(R.id.Market_label);
        title.setText("Trader's Goods");

        //first grab a reference to the widget
        RecyclerView recyclerView = findViewById(R.id.market_list);
        //Set the layout manager for the list to just be a vertical list of stuff
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //Setup the adapter for the view
        recyclerView.setAdapter(adapter);

        //marketList = setDummyGoods();
        Good[] traderGoods = tradingViewModel.getCargo(trader);

        if (traderGoods != null) {
            marketList = new ArrayList<>();
            for (Good traderGood : traderGoods) {
                if (traderGood != null) {
                    marketList.add(traderGood);
                }
            }
            Log.d("TRADERGOODS", marketList.toString());
        }
        adapter.setMarketList(marketList);

        adapter.setOnMarketClickListener(this::buyItem);

    }

    /**
     * method that facilitates the buying of an item
     *
     * @param position the index at which the good should be removed from
     */
    private void buyItem(int position) {
        Good toBuy = marketList.get(position);
        boolean isSold = tradingViewModel.facilitateTrade(toBuy, tradingViewModel.getPlayer(),
                trader);
        if (!isSold) {
            Toast.makeText(this, "Could not buy item", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("NEW PLAYER CREDITS", "" + tradingViewModel.getPlayerCredits());
            playerCredits = tradingViewModel.getPlayerCredits();
            String f = "" + playerCredits;
            credits.setText(f);
            adapter.removeItem(position);
        }
    }
}
