package com.example.spacetrader.Model;

import java.io.Serializable;

public abstract class Interactor implements Serializable {

    private Repository myRepository;

    protected Interactor(Repository repo) {
        myRepository = repo;
    }

    protected Repository getRepository() {
        return myRepository;
    }
}