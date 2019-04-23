package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.Location;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.Wormhole;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.UniverseViewModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * activity that allows the user to choose to travel to a new solar system or visit their current
 * one, also houses the save button for persistence and the save functionality for firebase
 */
public class UniverseActivity extends AppCompatActivity {

    private UniverseViewModel universeViewModel;
    private GraphView graph;
    private TextView currentSolarSystem;
    private TextView currentFuel;
    private HashMap<DataPoint, SolarSystem> dpToSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

        universeViewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
        currentSolarSystem = findViewById(R.id.universe_current_solarSystem);
        currentFuel = findViewById(R.id.fuelTextView);

        updateFields();

        graph = findViewById(R.id.graphView);

        DataPoint[] dp = ssToDP(universeViewModel.getSolarSystems());
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(dp);

        graph.setTitle("Solar Systems");
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(35);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(35);

        graph.getViewport().setBackgroundColor(Color.rgb(250,250,250));
        graph.getViewport().setDrawBorder(false);

        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        // remove horizontal x labels and line
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);

        graph.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);
        showMyLocation();

        series.setOnDataPointTapListener((series1, dataPoint) -> {
            SolarSystem thisSS = dpToSS.get(dataPoint);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    UniverseActivity.this
            );
            alertDialogBuilder.setTitle("Travel to Solar System?");
            alertDialogBuilder.setMessage(Objects.requireNonNull(thisSS).toString());
            alertDialogBuilder.setPositiveButton("Travel", (dialog, which) -> {
                Location previousLocation = universeViewModel.getCurrentSolarSystem().getLocation();
                Log.d("UniverseActivity", " prev Location" + previousLocation.toString());
                boolean didTravel = false;
                try {
                    didTravel = universeViewModel.facilitateTravel(thisSS);
                    if (!didTravel) {
                        Toast.makeText(UniverseActivity.this, "Could not travel",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), TravelActivity.class);
                        startActivityForResult(intent, 1);
                    }
                } catch (IllegalArgumentException e) {
                    Toast.makeText(UniverseActivity.this, "Travelled using wormhole",
                            Toast.LENGTH_SHORT).show();
                    showTravelable();
                    showMyLocation();
                    //Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                    //startActivityForResult(intent, 2);
                }

            });
            alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        });

        Button tradeButton = findViewById(R.id.game_tradeButton);
        tradeButton.setOnClickListener(v -> {
            Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
            startActivityForResult(intent, 1);
        });

        Button saveButton = findViewById(R.id.game_saveButton);
        saveButton.setOnClickListener(v -> {
            saveGame();
            Toast.makeText(UniverseActivity.this, "Game Saved to Device",
                    Toast.LENGTH_SHORT).show();
        });

        final MediaPlayer song = MediaPlayer.create(this, R.raw.mixtape);
        song.setLooping(true);
        Button songButton = findViewById(R.id.game_songButton);
        songButton.setOnClickListener(v -> {
            if (song.isPlaying()) {
                song.pause();
            } else {
                song.start();
            }
        });

    }
    private DataPoint[] ssToDP(Iterable<SolarSystem> solarSystems) {
        dpToSS = new HashMap<>();
        List<DataPoint> dps = new ArrayList<>();
        for (SolarSystem system : solarSystems) {
            DataPoint cur_dp = new DataPoint(system.getX(), system.getY());
            dps.add(cur_dp);
            dpToSS.put(cur_dp, system);
        }
        DataPoint[] dataPoints = new DataPoint[dps.size()];
        Collections.sort(dps, (s1, s2) -> (int)s1.getX() - (int)s2.getX());
        for (int i =0; i < dps.size(); i++) {
            dataPoints[i] = dps.get(i);
        }
        return dataPoints;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            Toast.makeText(UniverseActivity.this, "UpdateFuel", Toast.LENGTH_SHORT).show();
            updateFields();
            showMyLocation();

        }
    }

    private void saveGame() {
        Game g = universeViewModel.getGame();
        File file = new File(this.getFilesDir(), "data.bin");
        try {
            /*
               For binary, we use Serialization, so everything we write has to implement
               the Serializable interface.  Fortunately all the collection classes and APi classes
               that we might use are already Serializable.  You just have to make sure your
               classes implement Serializable.

               We have to use an ObjectOutputStream to write objects.

               One thing to be careful of:  You cannot serialize static data.
             */


            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
            // We basically can save our entire data model with one write, since this will follow
            // all the links and pointers to save everything.  Just save the top level object.
            out.writeObject(g);
            out.close();
        }
        catch (IOException e) {
            Log.e("UserManagerFacade", "Error writing an entry from binary file", e);
        }

    }

    private void updateFields() {
        currentSolarSystem.setText(universeViewModel.getCurrentSolarSystem().toString());
        String f = "Current fuel: " +
                String.format(Locale.getDefault(), "%.1f",
                        universeViewModel.getFuel() * 100) + "%";
        currentFuel.setText(f);

    }


    /**
     * Makes the current SolarSystem point on the Universe Graph Red and changes the previous
     * SolarSystem point back to the correct color
     *
     */
    private void showMyLocation(){
        this.showTravelable();
        DataPoint[] currentDP = new DataPoint[1];
        currentDP[0]= new DataPoint(universeViewModel.getCurrentSolarX(),
                universeViewModel.getCurrentSolarY());
        PointsGraphSeries<DataPoint> myLocation = new PointsGraphSeries<>(currentDP);
        graph.addSeries(myLocation);
        myLocation.setColor(Color.RED);
    }

    /**
     *
     */
    private void showTravelable() {
        Set<SolarSystem> solars = universeViewModel.getSolarSystems();
        Wormhole wormhole = universeViewModel.getWormhole();
        List<DataPoint> locsPlayerCanTravel = new ArrayList<>();
        List<DataPoint> notLocsPlayerCanTravel = new ArrayList<>();
        List<DataPoint> wormholeLocs = new ArrayList<>();
        for(SolarSystem solarSystem : solars) {
            if (wormhole.checkEndPoint(solarSystem)) {
                wormholeLocs.add(new DataPoint(solarSystem.getX(),
                        solarSystem.getY()));
            } else if(universeViewModel.playerCanTravel(solarSystem)) {
                Log.d("travel: " , solarSystem.getLocation().toString());
                locsPlayerCanTravel.add(new DataPoint(solarSystem.getX() ,
                        solarSystem.getY()));
            } else {
                notLocsPlayerCanTravel.add(new DataPoint(solarSystem.getX() ,
                        solarSystem.getY()));
            }
        }
        DataPoint[] l = new DataPoint[locsPlayerCanTravel.size()];
        DataPoint[] n = new DataPoint[notLocsPlayerCanTravel.size()];
        DataPoint[] w = new DataPoint[wormholeLocs.size()];
        Collections.sort(locsPlayerCanTravel, (s1, s2) -> (int)s1.getX() - (int)s2.getX());
        Collections.sort(notLocsPlayerCanTravel, (s1, s2) -> (int)s1.getX() - (int)s2.getX());
        Collections.sort(wormholeLocs, (s1, s2) -> (int)s1.getX() - (int)s2.getX());
        for (int i =0; i < locsPlayerCanTravel.size(); i++) {
            l[i] = locsPlayerCanTravel.get(i);
        }
        for (int i =0; i < notLocsPlayerCanTravel.size(); i++) {
            n[i] = notLocsPlayerCanTravel.get(i);
        }
        for (int i = 0; i < wormholeLocs.size(); i++) {
            w[i] = wormholeLocs.get(i);
            Log.d("worm", i + " " + wormholeLocs.get(i));
        }
        PointsGraphSeries<DataPoint> lPCT = new PointsGraphSeries<>(l);
        PointsGraphSeries<DataPoint> nLPCT = new PointsGraphSeries<>(n);
        PointsGraphSeries<DataPoint> wormholePGS = new PointsGraphSeries<>(w);
        graph.addSeries(wormholePGS);
        graph.addSeries(lPCT);
        graph.addSeries(nLPCT);
        wormholePGS.setColor(Color.BLACK);
        lPCT.setColor(Color.YELLOW);
        nLPCT.setColor(Color.rgb(1,114,203));

    }
}
