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
import java.util.Collections;
import java.util.List;
/**
 * An adapter for the list of items available for purchase at the SpacePort
 */
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

    /**
     * Sets the market list to a list of goods
     * @param list the list of market goods
     */
    public void setMarketList(Iterable<Good> list) {
        List<Good> our_list = new ArrayList<>();
        for (Good g : list) {
            our_list.add(g);
        }
        this.marketList = our_list;
        notifyDataSetChanged();
    }

    /**
     * getter for the market list
     * @return the market list
     */
    public List<Good> getMarketList() {
        return Collections.unmodifiableList(marketList);
    }

    /**
     * method for removing an item from the market list when it is bough
     * @param position the position of the item to be bought
     */
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

                if ((listener != null) && (position != RecyclerView.NO_POSITION)) {
                    listener.onMarketItemBuy(position);
                }
            });

        }
    }

    /**
     * interface with one method to be implemented in the onClick method
     */
    public interface OnMarketItemClickListener {
        /**
         * the method that will be implemented in the onClick method
         * @param position the position of the item that is to be bought
         */
        void onMarketItemBuy(int position);
    }
    /**
     * sets a listener for each good item in the market list
     *
     * @param listener of type OnMarketItemClickListener that sets this instance's listener
     *
     */
    public void setOnMarketClickListener(OnMarketItemClickListener listener) {
        this.listener = listener;
    }
}
