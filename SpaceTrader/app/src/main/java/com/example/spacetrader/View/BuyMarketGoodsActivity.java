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
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Activity that extends AppCompatActivity that facilitates the buying of market goods at
 * the space port
 */
public class BuyMarketGoodsActivity extends AppCompatActivity {
    private Button backButton;
    private double playerCredits;
    private TextView credits;
    private final MarketAdapter adapter = new MarketAdapter();
    private List<Good> marketList;

    private TradingViewModel tradingViewModel;
    private SpacePort spacePort;

    /**
     * method that instantiates the viewable attributes that the user can see on the screen
     *
     * @param savedInstanceState of type Bundle that represents a saved instance of the class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_market_goods);

        tradingViewModel = ViewModelProviders.of(this).get(TradingViewModel.class);

        playerCredits = tradingViewModel.getPlayerCredits();
        spacePort = tradingViewModel.getSpacePort();

        backButton = findViewById(R.id.Market_Back_Button);
        backButton.setOnClickListener(v -> {
            finish();
        });

        credits = findViewById(R.id.market_credit_input);
        credits.setText("" + playerCredits);


        //first grab a reference to the widget
        RecyclerView recyclerView = findViewById(R.id.market_list);
        //Set the layout manager for the list to just be a vertical list of stuff
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //Setup the adapter for the view
        recyclerView.setAdapter(adapter);

        marketList = setDummyGoods();
        Good[] spacePortGoods = tradingViewModel.getCargo(spacePort);
        if (spacePortGoods != null) {
            List<Good> goods = new ArrayList<Good>();
            for (int i = 0; i < spacePortGoods.length; i++){
                if (spacePortGoods[i] != null) {
                    goods.add(spacePortGoods[i]);
                }
            }
            Log.d("SPACEPORTGOODS", goods.toString());
            adapter.setMarketList(goods);
            marketList = Arrays.asList(spacePortGoods);
        } else {
            adapter.setMarketList(marketList);
        }

        adapter.setOnMarketClickListener(new MarketAdapter.OnMarketItemClickListener() {
            @Override
            public void onMarketItemBuy(int position) {
                buyItem(position);
            }
        });

    }

    /**
     * simple method to set a number of dummy goods in the player's cargo hold
     *
     * @return a list of goods that would be in the player's cargo hold
     */
    public List<Good> setDummyGoods() {
        List<Good> list = new ArrayList<>();
        list.add(new Good(GoodType.MACHINE));
        list.add(new Good(GoodType.FUR));
        list.add(new Good(GoodType.ORE));
        return list;
    }

    /**
     * method that facilitates the buying of an item
     *
     * @param position the index at which the good should be removed from
     */
    public void buyItem(int position) {
        //update credits - make sure the goods have a price!!!
        Good toBuy = marketList.get(position);
        boolean isSold = tradingViewModel.facilitateTrade(toBuy, tradingViewModel.getPlayer(), spacePort);
        if (!isSold) {
            Toast.makeText(this, "Could not buy item", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("NEW PLAYER CREDITS", "" + tradingViewModel.getPlayerCredits());
            playerCredits = tradingViewModel.getPlayerCredits();
            credits.setText("" + playerCredits);
            //facilitateTrade already removes the item, the following line is not necessary
            //marketList.remove(toBuy);

            adapter.notifyItemRemoved(position);
        }
    }
}

