package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.spacetrader.Entity.Location;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.TradingViewModel;

import java.util.Objects;
import java.util.Random;


/**
 * Activity class that literally just shows a cool travel warping jpg post-travel
 */
public class TravelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        TradingViewModel tradingViewModel = ViewModelProviders.of(this).get(TradingViewModel.class);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Actions to do after 1 second

        }, 1000);
        Random r = new Random();
        int scenario = r.nextInt(5);
        int diff = tradingViewModel.getGameDifficulty();
        switch (scenario) {
            case 0:
                Toast.makeText(TravelActivity.this, "Traveled safely", Toast.LENGTH_SHORT).show();
                handler.postDelayed(() -> {
                    // Actions to do after 2 seconds
                    Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                    startActivityForResult(intent, 2);
                }, 3000);
                break;
            case 1:
                Toast.makeText(TravelActivity.this, "You lost 100 credits",
                        Toast.LENGTH_SHORT).show();
                tradingViewModel.setPlayerCredits(tradingViewModel.getPlayerCredits() - 100);
                handler.postDelayed(() -> {
                    // Actions to do after 2 seconds
                    Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                    startActivityForResult(intent, 2);
                }, 3000);
                break;
            case 2:
                double gainCredits = (600 - (100 * diff));
                Toast.makeText(TravelActivity.this, "You gained " + gainCredits +
                        " credits", Toast.LENGTH_SHORT).show();
                tradingViewModel.setPlayerCredits(
                        tradingViewModel.getPlayerCredits() + (600 - (100 * diff))
                );
                handler.postDelayed(() -> {
                    // Actions to do after 2 seconds
                    Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                    startActivityForResult(intent, 2);
                }, 3000);
                break;
            case 3:
                Toast.makeText(TravelActivity.this, "A trader appears!", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        TravelActivity.this
                );
                alertDialogBuilder.setTitle("Do you want to trade?");
                alertDialogBuilder.setPositiveButton("Trade", (dialog, which) -> {
                    Intent intent = new Intent(getApplicationContext(), TraderActivity.class);
                    startActivityForResult(intent, 1);
                });
                alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                    Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                    startActivityForResult(intent, 2);
                });
                alertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                        startActivityForResult(intent, 2);
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            case 4:
                Toast.makeText(TravelActivity.this, "Inter-Galactic Casino", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(
                        TravelActivity.this
                );
                alert.setTitle("Do you want to Casino?");
                alert.setPositiveButton("Casino", (dialog, which) -> {
                    Intent intent = new Intent(getApplicationContext(), CasinoActivity.class);
                    startActivityForResult(intent, 3);
                });
                alert.setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                    Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                    startActivityForResult(intent, 2);
                });
                alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                        startActivityForResult(intent, 2);
                    }
                });
                AlertDialog alertD = alert.create();
                alertD.show();
                break;
        }

    }

    /**
     *
     *
     * @param requestCode a thing
     * @param resultCode another thing
     * @param data a last thing
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            finish();
        } else if (requestCode == 1) {
            Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
            startActivityForResult(intent, 2);
        } else if (requestCode == 3) {
            finish();
        }
    }


}
