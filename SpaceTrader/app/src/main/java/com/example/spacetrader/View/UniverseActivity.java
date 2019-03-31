package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.Entity.Location;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.UniverseViewModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UniverseActivity extends AppCompatActivity {

    private UniverseViewModel universeViewModel;
    private GraphView graph;
    private Button tradeButton;
    private TextView currentSolarSystem;
    private TextView currentFuel;
    private HashMap<DataPoint, SolarSystem> dpToSS;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

        universeViewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
        currentSolarSystem = findViewById(R.id.universe_curent_solarSystem);
        currentFuel = findViewById(R.id.fuelTextView);

        updateFields();

        //Log.d("UniverseActivity", configurationViewModel.getGame().getUniverse().toString());

        graph = findViewById(R.id.graphView);

        HashSet<SolarSystem> solarSystems = universeViewModel.getSolarSystems();
        dpToSS = new HashMap<>();
        List<DataPoint> dps = new ArrayList<>();
        for (SolarSystem system : solarSystems) {
            Location loc = system.getLocation();
            DataPoint cur_dp = new DataPoint(loc.getX(), loc.getY());
            dps.add(cur_dp);
            dpToSS.put(cur_dp, system);
        }
        DataPoint[] dataPoints = new DataPoint[dps.size()];
        Collections.sort(dps, (s1, s2) -> (int)s1.getX() - (int)s2.getX());
        for (int i =0; i < dps.size(); i++) {
            dataPoints[i] = dps.get(i);
        }
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(dataPoints);

        graph.setTitle("Solar Systems");
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(35);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(35);

        // enable scaling and scrolling
//        graph.getViewport().setScalable(true);
//        graph.getViewport().setScalableY(true);
        graph.getViewport().setBackgroundColor(Color.rgb(250,250,250));
        graph.getViewport().setDrawBorder(false);

        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);// remove horizontal x labels and line
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);

        graph.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);
        showMyLocation();

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                SolarSystem thisSS = dpToSS.get(dataPoint);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UniverseActivity.this);
                alertDialogBuilder.setTitle("Travel to Solar System?");
                alertDialogBuilder.setMessage(thisSS.toString());
                alertDialogBuilder.setPositiveButton("Travel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Location previousLocation = universeViewModel.getCurrentSolarSystem().getLocation();
                        Log.d("UniverseActivity", " prev Location" + previousLocation.toString());
                        boolean didTravel = universeViewModel.facilitateTravel(thisSS);
                        if (!didTravel) {
                            Toast.makeText(UniverseActivity.this, "Could not travel", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent( getApplicationContext(), TravelActivity.class);
                            startActivityForResult(intent, 1);
                            Log.d("UniverseActivity", " curr Location" + universeViewModel.getCurrentSolarSystem().getLocation().toString());
                            updateFields();
                            showMyLocation();


                        }
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        tradeButton = findViewById(R.id.game_tradeButton);
        tradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGame();
                Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                intent.putExtra("SOLARSTYSTEMSTATS",universeViewModel.getCurrentSolarSystem().toString() );
                startActivity(intent);
                updateFields();
                showMyLocation();

            }
        });
    }

    private void updateFields() {
        currentSolarSystem.setText(universeViewModel.getCurrentSolarSystem().toString());
        currentFuel.setText("Current fuel: " + String.format("%.1f", universeViewModel.getFuel() * 100) + "%");

    }


    /**
     * Makes the current SolarSystem point on the Universe Graph Red and changes the previous
     * SolarSystem point back to the correct color
     *
     */
    private void showMyLocation(){
        this.showTravelable();
        DataPoint[] currentDP = new DataPoint[1];
        currentDP[0]= new DataPoint(universeViewModel.getCurrentSolarSystem().getLocation().getX(),
                                    universeViewModel.getCurrentSolarSystem().getLocation().getY());
        PointsGraphSeries<DataPoint> myLocation = new PointsGraphSeries<DataPoint>(currentDP);
        graph.addSeries(myLocation);
        myLocation.setColor(Color.RED);

    }

    private void showTravelable() {
        Set<SolarSystem> solars = universeViewModel.getSolarSystems();
        List<DataPoint> locsPlayerCanTravel = new ArrayList<>();
        List<DataPoint> notLocsPlayerCanTravel = new ArrayList<>();
        for(SolarSystem solarSystem : solars) {
            if(universeViewModel.playerCanTravel(solarSystem)) {
                Log.d("travel: " , solarSystem.getLocation().toString());
                locsPlayerCanTravel.add(new DataPoint(solarSystem.getLocation().getX() , solarSystem.getLocation().getY()));
            } else {
                notLocsPlayerCanTravel.add(new DataPoint(solarSystem.getLocation().getX() , solarSystem.getLocation().getY()));
            }
        }
        DataPoint[] l = new DataPoint[locsPlayerCanTravel.size()];
        DataPoint[] n = new DataPoint[notLocsPlayerCanTravel.size()];
        Collections.sort(locsPlayerCanTravel, (s1, s2) -> (int)s1.getX() - (int)s2.getX());
        Collections.sort(notLocsPlayerCanTravel, (s1, s2) -> (int)s1.getX() - (int)s2.getX());
        for (int i =0; i < locsPlayerCanTravel.size(); i++) {
            l[i] = locsPlayerCanTravel.get(i);
        }
        for (int i =0; i < notLocsPlayerCanTravel.size(); i++) {
            n[i] = notLocsPlayerCanTravel.get(i);
        }
        PointsGraphSeries<DataPoint> lPCT = new PointsGraphSeries<>(l);
        PointsGraphSeries<DataPoint> nLPCT = new PointsGraphSeries<>(n);
        graph.addSeries(lPCT);
        graph.addSeries(nLPCT);
        lPCT.setColor(Color.YELLOW);
        nLPCT.setColor(Color.rgb(1,114,203));
    }

    private void saveGame() {
//        universeViewModel.firebaseSave();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef =   database.getReference();
        Toast.makeText(getApplicationContext(), "FIREBASE UPDATED", Toast.LENGTH_SHORT).show();
        myRef.setValue(new HashMap<String, Object>(){{
            put("e", universeViewModel.getPlayerCredits());
        }});
    }
}
