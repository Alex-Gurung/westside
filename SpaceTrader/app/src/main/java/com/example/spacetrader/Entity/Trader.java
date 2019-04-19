package com.example.spacetrader.Entity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * a class that represents a trader that has trader capabilities and is an NPC
 */
public class Trader extends NonPlayerCharacter implements TraderCapability {

    private double credits = Double.POSITIVE_INFINITY;
    private List<Good> goods;
    private final Random random = new Random();

    /**
     * constructor that instantiates a Trader with a ship
     *
     */
    public Trader(SolarSystem currentSolarSystem) {
        super(new Ship(ShipType.TERMITE));
        this.goods = new ArrayList<>();
        this.currentSolarSystem = currentSolarSystem;
        populateGoods(ship.getShiptype().getCargoHolds());


    }

    /**
     * setter method that sets the price of the good based on the tech level and resource on the
     * current Solar System
     *
     * @param good of type Good whose price is to be calculated
     */
    public void setPrice(Good good) {
        setPrice(good, this.currentSolarSystem.getTechLevel(),
                this.currentSolarSystem.getResource());
        good.setPrice(good.getPrice()*.9);
    }

    /**
     * getter method for the price of the given good
     *
     * @param good of type good whose price will be retrieved
     * @return a double representation of the price of the given good
     */
    @Override
    public double getPrice(Good good) {
        return good.getPrice();
    }

    /**
     * method (Overridden from the TraderCapabilities interface) that determines if the player can
     * sell the specified good
     *
     * @param good of type Good that may or may not be sold
     * @return a boolean representation of whether or not the good can be sold
     */
    @Override
    public boolean canSell(Good good) {
        return ship.hasGood(good);
    }

    /**
     * method (Overridden from the TraderCapabilities interface) that determines if the player can
     * buy the specified good
     *
     * @param good of type Good that may or may not be bought
     * @return a boolean representation of whether or not the good can be bought
     */
    @Override
    public boolean canBuy(Good good) {
        return (good.getPrice() <= credits) && ship.hasCargoSpace();
    }

    /**
     * Overridden method (from its Trader Capability interface) that allows the player to buy goods
     *
     * @param g of type Good that is the good to be bought
     */
    @Override
    public void buy(Good g) {
        credits -= g.getPrice();
        ship.addCargo(g);
    }

    /**
     * Overridden method (from its Trader Capability interface) that allows the player to sell goods
     *
     * @param g of type Good that is the good to be sold
     */
    @Override
    public void sell(Good g) {
        credits += g.getPrice();
        ship.removeCargo(g);
    }

    /**
     * Overridden method (from its Trader Capability interface) that gets the array representation
     * of the player's cargo hold of Goods
     *
     * @return an array of Goods that the ship of the player contains
     */
    @Override
    public Good[] getCargo() {
        return ship.getCargo();
    }

    @Override
    public void interactWithPlayer(Player player) {
        player.canChangeShip(ShipType.BEETLE);
    }

    public void populateGoods(int numGoods) {
        Log.d("TRADERGOODS", "(2) populate goods");
        List<GoodType> producible = new ArrayList<>();
        for(GoodType gt : GoodType.values()) {
            if(gt.getMinTechLevelProduce() <= this.currentSolarSystem.getTechLevel().ordinal()){
                producible.add(gt);
            }
        }
        for (int i = 0; i < numGoods; i++) {
            Good add = new Good(producible.get(random.nextInt(producible.size())));
            setPrice(add);
            Log.d("TRADERGOODS", add.getGoodType().name());
            goods.add(add);
            this.ship.addCargo(add);
        }


    }

}
