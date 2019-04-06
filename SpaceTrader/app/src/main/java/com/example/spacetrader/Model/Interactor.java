package com.example.spacetrader.Model;

import java.io.Serializable;

/**
 * The interactor abstract class - it has a repository
 *
 */
public abstract class Interactor implements Serializable {

    private final Repository myRepository;

    /**
     * constructor for the interactor
     * @param repo the repository
     */
    Interactor(Repository repo) {
        myRepository = repo;
    }

    /**
     * getter for the repository
     * @return the repository
     */
    Repository getRepository() {
        return myRepository;
    }
}