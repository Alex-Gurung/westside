package com.example.spacetrader.ViewModel;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.Entity.Player;

public class ConfigurationViewModel extends AndroidViewModel {

    public ConfigurationViewModel (@NonNull Application application){
        super(application);
    }
    public boolean skillPointChecker(Player player) {
        return (player.getTotalSkillPoints() == 16) ? true: false;
    }
}
