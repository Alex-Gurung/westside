package com.example.spacetrader.Model;

import java.io.Serializable;

public abstract class Interactor implements Serializable {

    private final Repository myRepository;

    Interactor(Repository repo) {
        myRepository = repo;
    }

    Repository getRepository() {
        return myRepository;
    }
}