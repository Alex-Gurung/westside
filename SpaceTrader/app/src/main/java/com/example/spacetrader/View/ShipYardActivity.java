package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

import java.util.Locale;

/**
 * the activity that allows the user to refuel their ship
 */
public class ShipYardActivity extends AppCompatActivity {
    private TextView credits;
    private double playerCredits;

    private TradingViewModel tradingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //recyclerView = (RecyclerView) findViewById(R.id.ShipType_RecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        //mAdapter = new MyAdapter(myDataset);
        //recyclerView.setAdapter(mAdapter);


        setContentView(R.layout.activity_shipyard);

        tradingViewModel = ViewModelProviders.of(this).get(TradingViewModel.class);

        playerCredits = tradingViewModel.getPlayerCredits();

        Button backButton = findViewById(R.id.ShipYard_Back_Button);
        backButton.setOnClickListener(v -> finish());
        credits = findViewById(R.id.shipyard_credit_input);
        String f = String.format(Locale.getDefault(), "%.2f", playerCredits);
        credits.setText(f);

        Button refuelMaxButton = findViewById(R.id.ShipYard_Refuel_Max_Button);
        refuelMaxButton.setOnClickListener(v -> {
            refuelMax();
            playerCredits = tradingViewModel.getPlayerCredits();
            String e = String.format(Locale.getDefault(), "%.2f", playerCredits);
            credits.setText(e);
        });
    }

    private void refuelMax() {
        boolean refueledMax = tradingViewModel.refuelMax();
        if(refueledMax) {
            Toast.makeText(getApplicationContext(), "Ship refueled fully",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Can not refuel",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
/*
class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] shipTypes;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter() {
        shipTypes = tradingViewModel;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        ...
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}*/