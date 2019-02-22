package com.example.spacetrader.Entity;

public enum ShipType {
    //name, max distance, cargo holds, weapons, shields, gadgets, mercs, hull strength
    FLEA("Flea", 20, 4, 0, 0, 0, 0, 1),
    GNAT("Gnat", 14, 15, 1, 0, 1, 0, 2),
    FIREFLY("Firefly",17, 20, 1, 1, 1, 0, 2),
    MOSQUITO("Mosquito", 13, 15, 2, 1, 1, 0,3),
    BUMBLEBEE("Bumblebee", 15, 20, 1, 2, 2, 1, 2),
    BEETLE("Beetle", 14, 50, 0, 1, 1, 3, 1),
    HORNET("Hornet", 16, 20, 3, 2, 1, 2, 3),
    GRASSHOPPER("Grasshopper", 15, 30, 2, 2, 3, 3, 2),
    TERMITE("Termite", 13, 60, 1, 3, 2, 3, 3),
    WASP("Wasp", 14, 35, 3, 2, 2, 3, 3);
    public String name;
    private int maxDistance;
    private int fuel; //a percent
    public int cargoHolds;
    public int weaponSlots;
    public int shieldSlots;
    public int gadgetSlots;
    public int numMercenaries;
    public int hullStrength; //1 weak, 2 avg, 3 strong
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
