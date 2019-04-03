package com.example.spacetrader.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.Entity.Good;
import com.example.spacetrader.R;

import java.util.ArrayList;
import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder>{
    /** a copy of the list of marketList in the model */
    private List<Good> marketList = new ArrayList<>();

    /** a listener for a touch event on the cargoItem */
    private OnMarketItemClickListener listener;

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // hook up to the view for a single student in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.market_item, parent, false);

        return new MarketViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {

        //bind the marketList data for one cargoItem
        Good marketItem = marketList.get(position);

        Log.d("APP", "Binding: " + position + " " + marketList.get(position));

        holder.name.setText((marketItem.getGoodType().name()));
        String price = "" + marketItem.getPrice();
        holder.price.setText(price);

    }

    @Override
    public int getItemCount() {
        return marketList.size();
    }


    public void setMarketList(List<Good> list) {
        this.marketList = list;
        notifyDataSetChanged();
    }

    public List<Good> getMarketList() {
        return marketList;
    }

    public void removeItem(int position) {
        this.marketList.remove(position);
        Log.d("FACILITATING: ", "position: " +position);
        notifyDataSetChanged();
    }


    /**
     * This is a holder for the widgets associated with a single entry in the list of marketList
     */
    class MarketViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;
        private final Button sellButton;


        MarketViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.market__name);
            price = itemView.findViewById(R.id.market_price);
            sellButton = itemView.findViewById(R.id.market_buyButton);

            sellButton.setOnClickListener(view -> {
                int position = getAdapterPosition();

                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onMarketItemBuy(position);
                }
            });

        }
    }

    public interface OnMarketItemClickListener {
        void onMarketItemBuy(int position);
    }

    public void setOnMarketClickListener(OnMarketItemClickListener listener) {
        this.listener = listener;
    }
}
