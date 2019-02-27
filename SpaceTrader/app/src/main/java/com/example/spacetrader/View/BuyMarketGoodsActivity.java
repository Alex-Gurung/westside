package com.example.spacetrader.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.Entity.GoodType;
import com.example.spacetrader.R;

import java.util.ArrayList;
import java.util.List;

public class BuyMarketGoodsActivity extends AppCompatActivity {
    private Button backButton;
    private double playerCredits = 10000;
    private TextView credits;
    private final MarketAdapter adapter = new MarketAdapter();
    private List<Good> marketList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_market_goods);
        backButton = findViewById(R.id.Market_Back_Button);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
            startActivity(intent);
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
        adapter.setMarketList(marketList);
        adapter.setOnMarketClickListener(new MarketAdapter.OnMarketItemClickListener() {
            @Override
            public void onMarketItemBuy(int position) {
                buyItem(position);
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
    public void buyItem(int position) {
        //update credits - make sure the goods have a price!!!
        double usedCredits = marketList.get(position).getPrice();
        playerCredits -= usedCredits;
        credits.setText(""+ playerCredits);

        marketList.remove(marketList.get(position));

        adapter.notifyItemRemoved(position);
    }
}

