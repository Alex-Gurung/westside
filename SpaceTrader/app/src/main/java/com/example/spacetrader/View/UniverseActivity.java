package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.spacetrader.Entity.Location;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.ConfigurationViewModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class UniverseActivity extends AppCompatActivity {

    private ConfigurationViewModel configurationViewModel;
    private GraphView graph;
    private Button tradeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

        configurationViewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);

        //Log.d("UniverseActivity", configurationViewModel.getGame().getUniverse().toString());

        graph = (GraphView) findViewById(R.id.graphView);

        HashSet<SolarSystem> solarSystems = configurationViewModel.getGame().getUniverse().getSolarSystems();
        List<DataPoint> dps = new ArrayList<>();
        for (SolarSystem system : solarSystems) {
            Location loc = system.getLocation();
            dps.add(new DataPoint(loc.getX(), loc.getY()));
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

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(UniverseActivity.this, "Solar System: "+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });

        tradeButton = findViewById(R.id.game_tradeButton);
        tradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                startActivity(intent);
            }
        });

    }

}
