package com.example.spacetrader.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.spacetrader.Entity.Game;
import com.example.spacetrader.Entity.SolarSystem;
import com.example.spacetrader.Entity.SpacePort;
import com.example.spacetrader.Entity.Universe;
import com.example.spacetrader.R;
import com.example.spacetrader.ViewModel.ConfigurationViewModel;
import com.example.spacetrader.ViewModel.UniverseViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TravelActivity extends AppCompatActivity {
    private ConfigurationViewModel configurationViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 2 seconds
                int scenario = 0;
                if (scenario == 0) {
                    Intent intent = new Intent( getApplicationContext(), SpacePortActivity.class);
                    startActivityForResult(intent, 2);
                }
            }
        }, 2000);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 2) {
            saveGame();
            Toast toast=Toast.makeText(getApplicationContext(),"Game Saved",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
            finish();
        }
    }

    public void saveGame() {
        configurationViewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        Game g = configurationViewModel.getGame();
        File file = new File(this.getFilesDir(), "data.bin");
        boolean success = true;
        try {
            /*
               For binary, we use Serialization, so everything we write has to implement
               the Serializable interface.  Fortunately all the collection classes and APi classes
               that we might use are already Serializable.  You just have to make sure your
               classes implement Serializable.

               We have to use an ObjectOutputStream to write objects.

               One thing to be careful of:  You cannot serialize static data.
             */


            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            // We basically can save our entire data model with one write, since this will follow
            // all the links and pointers to save everything.  Just save the top level object.
            out.writeObject(g);
            out.close();
        }
        catch (IOException e) {
            Log.e("UserManagerFacade", "Error writing an entry from binary file", e);
            success = false;
        }

    }
}
