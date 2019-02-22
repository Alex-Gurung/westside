package com.example.spacetrader.Entity;

public enum ShipType {
    //name, max distance, cargo holds, weapons, shields, gadgets, mercs, hull strength
    FLEA("Flea",),
    GNAT("Gnat", 14, 15, 1,0,1),
    FIREFLY("Firefly",17,20,1,1,1),
    MOSQUITO("Mosquito"),
    BUMBLEBEE("Bumblebee"),
    BEETLE("Beetle"),
    HORNET("Hornet"),
    GRASSHOPPER("Grasshopper"),
    TERMITE("Termite"),
    WASP("Wasp");
    public String name;
    private int maxDistance;
    private int fuel; //a percent
    public int cargoHolds;
    public int weaponSlots;
    public int shieldSlots;
    public int gadgetSlots;
    public int numMercenaries;
    public int hullStrength;
    public boolean hasEscapePod;

    ShipType(String name, int maxDistance, int cargoHolds, int weaponSlots, int shieldSlots, int gadgetSlots, int numMercenaries, int hullStrength) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.cargoHolds = cargoHolds;
        this.weaponSlots = weaponSlots;
        this.shieldSlots = shieldSlots;
        this.gadgetSlots = gadgetSlots;
        this.numMercenaries = numMercenaries;
        this.hullStrength = hullStrength;
        this.hasEscapePod = false;
        this.fuel = 100;
    }

    public String toString() {
        return name;
    }




}
