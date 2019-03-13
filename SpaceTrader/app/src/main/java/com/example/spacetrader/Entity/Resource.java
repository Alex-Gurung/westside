package com.example.spacetrader.Entity;

import java.io.Serializable;

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

    private String name;

    Resource(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
 }
