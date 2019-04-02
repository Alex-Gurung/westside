package com.example.spacetrader;

import com.example.spacetrader.Entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AmanUnitTest {
    private GoodType[] goodTypes;
    private TechLevel[] techLevels;
    private Resource[] resources;
    private TraderCapability traderCapability;
    private double[] prices;
    private static final double delta = 0.00001;

    @Before
    public void setupSetPrice() {
        goodTypes = GoodType.values();
        techLevels = TechLevel.values();
        resources = Resource.values();
        traderCapability = new Trader();
        prices = new double[1120];
        int i = 0;
        for(GoodType gt : goodTypes) {
            for(TechLevel tl : techLevels) {
                for(Resource r : resources) {
                    int min = gt.getMinPrice();
                    int max = gt.getMaxPrice();
                    boolean isNaturalGood = gt.getIsNaturalResource();
                    int tlordinal = tl.ordinal();
                    if(!isNaturalGood) {
                        tlordinal = 8 - tlordinal;
                    }
                    double add = ((max - min) * (double)tlordinal)/8;
                    double pr = min + add;
                    if(r.equals(gt.getHighCostResource())) {
                        pr *= 2;
                    }
                    if(r.equals(gt.getLowCostResource())) {
                        pr *= 0.5;
                    }
                    prices[i++] = pr;
                }
            }
        }
    }

    @Test
    public void checkAllPrices() {
        double[] calculatedPrices = new double[1120];
        int i = 0;
        for(GoodType gt : goodTypes) {
            for(TechLevel tl : techLevels) {
                for(Resource r : resources) {
                    Good test = new Good(gt);
                    traderCapability.setPrice(test, tl, r);
                    calculatedPrices[i++] = test.getPrice();
                }
            }
        }
        Assert.assertArrayEquals(calculatedPrices, prices, delta);
    }



    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}



