package com.example.spacetrader.Entity;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * resource type enum
 */
public enum Resource implements Serializable {

    NEVER("Never"),
    NOSPECIALSOURCES("No Special Resources"),
    MINERALRICH("Mineral Rich"),
    MINERALPOOR("Mineral Poor"),
    DESERT("Desert"),
    LOTSOFWATER("Lots Of Water"),
    RICHSOIL("Rich Soil"),
    POORSOIL("Poor Soil"),
    RICHFAUNA("Rich Fauna"),
    LIFELESS("Lifeless"),
    WIERDMUSHROOMS("Wierd Mushrooms"),
    LOSTSOFHERBS("Lots Of Herbs"),
    ARTISTIC("Artistic"),
    WARLIKE("Warlike");

    private final String name;

    /**
     * makes the enum
     * @param name the string representation of the resource
     */
    Resource(String name){
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
 }
