package com.example.spacetrader.Model;

import com.example.spacetrader.Entity.Game;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the interface to the domain/business classes from anywhere else in the application
 *
 *
 * As it is, we are making this class do everything.
 */

public class Model {

    /** the data repository */
    private Repository myRepository;

    private Map<String, Object> interactorMap;

    /** Singleton Pattern Code
     *  this allows us to get access to this class
     *  anywhere, which will allow our View models to access
     *  the "back end"  more easily
     */
    private static  Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap<>();
        registerInteractors();
    }

    /** end Singleton Pattern */


    private void registerInteractors() {
        interactorMap.put("Game", new GameInteractor(myRepository));
    }

    public GameInteractor getGameInteractor() {
        return (GameInteractor) interactorMap.get("Game");
    }


}