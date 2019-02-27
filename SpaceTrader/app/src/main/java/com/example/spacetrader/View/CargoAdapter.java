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

/**
 * Adapter Class that allows a list of goods in the player's cargo to be displayed on a screen,
 * as well as be modified (or not) based on the actions of the player
 */
public class CargoAdapter extends RecyclerView.Adapter<CargoAdapter.CargoViewHolder>{
    /** a copy of the list of Goods in the model */
    private List<Good> cargo = new ArrayList<>();

    /** a listener for a touch event on the cargo good */
    private OnCargoGoodClickListener listener;

    /**
     * View holder that displays all pof the goods in the player's cargo hold
     *
     * @param parent of type ViewGroup to help with displaying a good item in the cargo hold
     * @param i of type int, but is unused for this context
     * @return a Cargo View holder that shows a single good item in the entire cargo hold
     */
    @NonNull
    @Override
    public CargoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // hook up to the view for a single good in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cargo_item, parent, false);

        return new CargoViewHolder(itemView);
    }

    /**
     * method to bind the view holder to certain attributes given to the view holder
     *
     * @param holder of type CargoViewHoldert whose text value will be set to the name of the good
     * @param position of type int to get the position of the cargo in the LOGCAT
     */
    @Override
    public void onBindViewHolder(@NonNull CargoViewHolder holder, int position) {

        //bind the cargo item data for the cargo
        Good cargoItem = cargo.get(position);

        Log.d("APP", "Binding: " + position + " " + cargo.get(position));

        holder.name.setText((cargoItem.getGoodType().name()));
        String price = "" + cargoItem.getPrice();
        holder.price.setText(price);

    }

    /**
     * method that returns the size of the player's cargo
     *
     * @return ant in representation of the player's cargo hold
     */
    @Override
    public int getItemCount() {
        return cargo.size();
    }

    /**
     * set's the cargo of the player
     *
     * @param cargo a list of Goods contained in the cargo
     */
    public void setCargoList(List<Good> cargo) {
        this.cargo = cargo;
        notifyDataSetChanged();
    }


    /**
     * This is a holder for the widgets associated with a single entry in the list of goods
     */
    class CargoViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private Button sellButton;

        /**
         * Method that creates the view layout of what each good will look like in the cargo hold
         *
         * @param itemView
         */
        public CargoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cargo_name);
            price = itemView.findViewById(R.id.cargo_price);
            sellButton = itemView.findViewById(R.id.market_buyButton);

            sellButton.setOnClickListener(new View.OnClickListener() {

                /**
                 * a method that reacts to the user pressing on the sell button on the screen
                 *
                 * @param view of type View that passes in the view the user clicked on
                 */
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onCargoGoodSell(position);
                    }
                }
            });
        }
    }

    /**
     * interface with one method to be implemented in the onClick method
     */
    public interface OnCargoGoodClickListener {
        void onCargoGoodSell(int position);
    }

    /**
     * sets a listener for each good item in the cargo hold
     *
     * @param listener of type OnCargoGoodClickListener that sets this instance's listener
     *
     */
    public void setOnCargoClickListener(OnCargoGoodClickListener listener) {
        this.listener = listener;
    }
}
