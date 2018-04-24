package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DiceTest {

    private Dice dice;

    @Before
    public void before(){
        this.dice = new Dice(DiceColor.YELLOW);
    }

    @Test
    public void getColor() {
        assertTrue("Unknown color " + dice.getColor(), Arrays.asList(DiceColor.values()).contains(dice.getColor()));
    }

    @Test
    public void getIntensity() {
        assertTrue("Intensity out of range" + dice.getIntensity(), 1 <= dice.getIntensity() && dice.getIntensity() <= 6);
    }

    @Test
    public void setRandomIntensity() {
        assertTrue("Intensity out of range " + dice.getIntensity(), 1 <= dice.getIntensity() && dice.getIntensity() <= 6);
    }
}