package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.Entity.Location;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.UniverseViewModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class UniverseActivity extends AppCompatActivity {

    private UniverseViewModel universeViewModel;
    private GraphView graph;
    private Button tradeButton;
    private TextView currentSolarSystem;
    private HashMap<DataPoint, SolarSystem> dpToSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

        universeViewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
        currentSolarSystem = findViewById(R.id.universe_curent_solarSystem);

        currentSolarSystem.setText(universeViewModel.getCurrentSolarSystem().toString());
        //Log.d("UniverseActivity", configurationViewModel.getGame().getUniverse().toString());

        graph = (GraphView) findViewById(R.id.graphView);

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
        graph.getViewport().setMaxY(100);
        // enable scaling and scrolling
//        graph.getViewport().setScalable(true);
//        graph.getViewport().setScalableY(true);
        graph.getViewport().setBackgroundColor(Color.BLACK);
        graph.getViewport().setDrawBorder(false);

        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);// remove horizontal x labels and line
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);

        graph.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);
    //>>
        showMyLocation(null);
    //>>


        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                SolarSystem thisSS = dpToSS.get(dataPoint);
                Toast.makeText(UniverseActivity.this, "Solar System: "+dataPoint, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UniverseActivity.this);
                alertDialogBuilder.setTitle("Travel to Solar System?");
                alertDialogBuilder.setMessage(thisSS.toString());
                alertDialogBuilder.setPositiveButton("Travel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                //>>
                        Location previousLocation = universeViewModel.getCurrentSolarSystem().getLocation();
                        Log.d("UniverseActivity", " prev Location" + previousLocation.toString());
                //>>
                        boolean didTravel = universeViewModel.facilitateTravel(thisSS);
                        if (!didTravel) {
                            Toast.makeText(UniverseActivity.this, "Could not travel", Toast.LENGTH_SHORT).show();;
                        } else {
                            Toast.makeText(UniverseActivity.this, "Succesfully traveled!", Toast.LENGTH_SHORT).show();
                            currentSolarSystem.setText(universeViewModel.getCurrentSolarSystem().toString());
                //>>
                            Log.d("UniverseActivity", " curr Location" + universeViewModel.getCurrentSolarSystem().getLocation().toString());
                            showMyLocation(previousLocation);
                //>>
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
                Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                intent.putExtra("SOLARSTYSTEMSTATS",universeViewModel.getCurrentSolarSystem().toString() );
                startActivity(intent);
            }
        });

    }

    /* NOTE: if we want to actually use this, we have to adjust the universeViewModel
            if not, ehh- just delete the stuff I put arrows by and the following method
     */


    /**
     * Makes the current SolarSystem point on the Universe Graph Red and changes the previous
     * SolarSystem point back to normal if needed
     *
     * @param prevLocation the previousLocation to change color from red to blue if not null
     */
    private void showMyLocation(Location prevLocation){
        if (prevLocation != null) {
            DataPoint[] prevDP = new DataPoint[1];
            prevDP[0]= new DataPoint(prevLocation.getX(),prevLocation.getY());
            PointsGraphSeries<DataPoint> prevLocationS = new PointsGraphSeries<DataPoint>(prevDP);
            graph.addSeries(prevLocationS);
            prevLocationS.setColor(Color.rgb(1,114,203));
        }
        DataPoint[] currentDP = new DataPoint[1];
        currentDP[0]= new DataPoint(universeViewModel.getCurrentSolarSystem().getLocation().getX(),
                                    universeViewModel.getCurrentSolarSystem().getLocation().getY());
        PointsGraphSeries<DataPoint> myLocation = new PointsGraphSeries<DataPoint>(currentDP);
        graph.addSeries(myLocation);
        myLocation.setColor(Color.RED);

    }

}
