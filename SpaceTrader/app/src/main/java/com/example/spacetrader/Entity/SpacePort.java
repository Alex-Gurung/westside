package com.example.spacetrader.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * class that represents a space port that exists in a Solar System
 */
public class SpacePort implements TraderCapability {

    private TechLevel techlevel;
    private List<Good> goods;
    private Random random = new Random();
    private Resource resource;

    /**
     * constructor that instantiates a space port with a tech level and a resource
     *
     * @param techlevel of type TechLevel that partially determines the prices of goods in the
     *                  space port
     * @param resource of type Resource that partially determines the prices of good in the space
     *                 port
     */
    public SpacePort(TechLevel techlevel, Resource resource) {
        this.techlevel = techlevel;
        this.resource = resource;
        goods = new ArrayList<>();
        int numGoods = 25 + random.nextInt(25);
        populateGoods(numGoods);
    }

    /**
     * method that populates the space port with random goods based on their availability in the
     * current Solar System
     *
     * @param numGoods of type in that represents the number of goods the space port will have
     */
    private void populateGoods(int numGoods) {
        List<GoodType> producible = new ArrayList<>();
        for(GoodType gt : GoodType.values()) {
            if(gt.getMinTechLevelProduce() <= techlevel.ordinal()){
                producible.add(gt);
            }
        }
        for (int i = 0; i < numGoods; i++) {
            Good add = new Good(producible.get(random.nextInt(producible.size())));
            setPrice(add);
            goods.add(add);
        }
    }

    /**
     * getter method (Overridden from the TraderCapabilities interface) that gets the price of the
     * good
     *
     * @param good of type good whose price will be determined
     * @return a double representation of the price of the given good
     */
    @Override
    public double getPrice(Good good) {
        return good.getPrice();
    }

    /**
     *  setter method that sets the price of the given good
     *
     * @param good of type Good whose price will be set based on the conditions of the current Solar
     *             System
     */
    public void setPrice(Good good) {
        setPrice(good, techlevel, resource);
    }

    /**
     * method (Overridden from the TraderCapabilities interface) that determines whether the player
     * can sell their items at the current space port
     *
     * @param good of type Good to be possibly sold
     * @return a boolean representation of whether the player can sell the given good
     */
    @Override
    public boolean canSell(Good good) {
        boolean tech = good.getMinTechLevelProduce() <= techlevel.ordinal();
        boolean inGoods = goods.contains(good);
        return tech && inGoods;
    }

    /**
     * method (Overridden from the TraderCapabilities interface) that determines whether the player
     * can buy items at the current space port
     *
     * @param good of type Good to be possibly bought
     * @return a boolean representation of whether the player can buy the given good
     */
    @Override
    public boolean canBuy(Good good) {
        return good.getMinTechLevelUse() <= techlevel.ordinal();
    }

    /**
     * method (Overridden from the TraderCapabilities interface) where the player can buy goods from
     * the current space port
     *
     * @param good of type Good that the player will be buying
     */
    @Override
    public void buy(Good good) {
        goods.add(good);
    }

    /**
     * method (Overridden from the TraderCapabilities interface) where the player can sell goods from
     * their cargo ship
     *
     * @param good of type Good that the player will be selling
     */
    @Override
    public void sell(Good good) {
        goods.remove(good);
    }

    /**
     * method that returns the list of goods at the current space port
     *
     * @return a List representation of the list of goods at the current space port
     */
    public List<Good> getGoods() {
        return goods;
    }

    /**
     * getter method that returns the player's cargo hold
     *
     * @return an array representation of type Good that is the player's cargo hold
     */
    public Good[] getCargo() {
        Good[] ourCargo = new Good[goods.size()];
        for (int i = 0; i < goods.size(); i++) {
            ourCargo[i] = goods.get(i);
            setPrice(ourCargo[i]);
        }
        return ourCargo;
    }
}
