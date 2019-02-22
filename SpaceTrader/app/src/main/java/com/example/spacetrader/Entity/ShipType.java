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
    private String name;
    private int maxDistance;
    private int cargoHolds;
    private int weaponSlots;
    private int shieldSlots;
    private int gadgetSlots;
    private int numMercenaries;
    private int hullStrength; //1 weak, 2 avg, 3 strong
    private int price;

    ShipType(String name, int maxDistance, int cargoHolds, int weaponSlots, int shieldSlots, int gadgetSlots, int numMercenaries, int hullStrength) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.cargoHolds = cargoHolds;
        this.weaponSlots = weaponSlots;
        this.shieldSlots = shieldSlots;
        this.gadgetSlots = gadgetSlots;
        this.numMercenaries = numMercenaries;
        this.hullStrength = hullStrength;
        this.price = this.calculatePrice();
    }

    private int calculatePrice(){
        int cost = 1000;
        cost += 100 * maxDistance;
        cost += 50 * cargoHolds;
        cost += 500 * weaponSlots;
        cost += 500 * gadgetSlots;
        cost += 500 * shieldSlots;
        cost += 1000 * numMercenaries;
        cost += 1500 * (hullStrength - 1);
        return cost;
    }

    public int getMaxDistance() {
        return maxDistance;
    }
    public int getCargoHolds() {
        return cargoHolds;
    }
    public int getWeaponSlots() {
        return weaponSlots;
    }
    public int getShieldSlots() {
        return shieldSlots;
    }
    public int getGadgetSlots() {
        return gadgetSlots;
    }
    public int getNumMercenaries() {
        return numMercenaries;
    }
    public int getHullStrength() {
        return hullStrength;
    }
    public int getPrice() {
        return price;
    }

    public String toString() {
        return "Name: " + name
                + "Distance: " + maxDistance + "\n"
                + "Cargo Slots:  " + cargoHolds + "\n"
                + "Weapon Slots: " + weaponSlots + "\n"
                + "Shield Slots: " + shieldSlots + "\n"
                + "Gadget Slots: " + gadgetSlots + "\n"
                + "Number of Mercenaries: " + numMercenaries + "\n"
                + "Hull Strength: " + hullStrength + "\n"
                + "Price: " + price;
    }




}
