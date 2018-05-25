package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.DecreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.IncreaseNotAllowedException;
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

    @Test
    public void changeIntensityTest() throws DecreaseNotAllowedException, IncreaseNotAllowedException {

        dice.setIntensity(3);
        assertEquals(3, dice.getIntensity());

        dice.decreaseIntensity();
        assertEquals(2, dice.getIntensity());

        dice.increaseIntensity();
        dice.increaseIntensity();
        assertEquals(4, dice.getIntensity());

        dice.setIntensity(5);
        dice.increaseIntensity();
        assertEquals(6, dice.getIntensity());
    }

    @Test
    public void setOppositeIntensityTest() {
        dice.setIntensity(5);
        dice.setOppositeIntensity();
        assertEquals(2, dice.getIntensity());
    }
}