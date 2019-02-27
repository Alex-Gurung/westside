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

public class CargoAdapter extends RecyclerView.Adapter<CargoAdapter.CargoViewHolder>{
    /** a copy of the list of students in the model */
    private List<Good> cargo = new ArrayList<>();

    /** a listener for a touch event on the student */
    private OnCargoGoodClickListener listener;

    @NonNull
    @Override
    public CargoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // hook up to the view for a single student in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cargo_item, parent, false);

        return new CargoViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull CargoViewHolder holder, int position) {

        //bind the student data for one student
        Good cargoItem = cargo.get(position);

        Log.d("APP", "Binding: " + position + " " + cargo.get(position));

        holder.name.setText((cargoItem.getGoodType().name()));
        String price = "" + cargoItem.getPrice();
        holder.price.setText(price);


    }

    @Override
    public int getItemCount() {
        return cargo.size();
    }

    public void setCargoList(List<Good> cargo) {
        this.cargo = cargo;
        notifyDataSetChanged();
    }


    /**
     * This is a holder for the widgets associated with a single entry in the list of students
     */
    class CargoViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private Button sellButton;


        public CargoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cargo_name);
            price = itemView.findViewById(R.id.cargo_price);
            sellButton = itemView.findViewById(R.id.cargo_sellButton);

            sellButton.setOnClickListener(new View.OnClickListener() {

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

    public interface OnCargoGoodClickListener {
        void onCargoGoodSell(int position);
    }

    public void setOnCargoClickListener(OnCargoGoodClickListener listener) {
        this.listener = listener;
    }
}
