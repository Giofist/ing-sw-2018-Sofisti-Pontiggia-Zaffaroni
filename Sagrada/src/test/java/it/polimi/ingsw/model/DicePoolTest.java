package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


// A couple of methods must be changed in order to throw a proper exception instead of null value

public class DicePoolTest {

    private DicePool dicePool;
    private Dice mockDice;

    @Before
    public void before() {
        dicePool = new DicePool();
        mockDice = mock(Dice.class);
        when(mockDice.getIntensity()).thenReturn(2);
        when(mockDice.getColor()).thenReturn(DiceColor.GREEN);
    }

    @Test
    public void constructorEmptyDicePoolTest() {
        assertEquals(0, dicePool.getDicePoolSize());
    }


    // This method tests if the constructor inserts the correct number of dices
    @Test
    public void constructorDicePoolTest() {
        dicePool = new DicePool(18, 18, 18, 18, 18);
        assertEquals(90, dicePool.getDicePoolSize());
    }

    // This method tests that an empty dicePool is instantiated if a wrong parameter is passed
    @Test
    public void constructorDicePoolWithWrongParametersTest() {
        dicePool = new DicePool(-1, 2, 4, 0, 1);
        assertEquals(0, dicePool.getDicePoolSize());
    }


    // This method tests if the shuffle function works
    @Test
    public void shuffleDicePoolTest() {
        dicePool = new DicePool(18, 18, 18, 18, 18);
        DicePool dicePool2 = new DicePool(18, 18, 18, 18, 18);

        assertFalse(dicePool.getallDicesbutnotremove().equals(dicePool2.getallDicesbutnotremove()));
    }


    // This method tests if adding dices to the Dicepool works properly
    @Test
    public void insertDiceTest() {
        assertEquals(0, dicePool.getDicePoolSize());

        dicePool.insertDice(DiceColor.YELLOW);
        assertEquals(1, dicePool.getDicePoolSize());
        assertEquals(DiceColor.YELLOW, dicePool.getDice(0).getColor());
    }


    // This method tests the addDice function which is a method used only for RoundDicePool for which a Dice is directly passed
    @Test
    public void addDiceTest() {
        assertEquals(0, dicePool.getDicePoolSize());
        dicePool.addDice(mockDice);
        assertEquals(1, dicePool.getDicePoolSize());
        Dice dice = dicePool.getDice(0);
        assertEquals(2, dice.getIntensity());
        assertEquals(DiceColor.GREEN, dice.getColor());
    }


    // This method will test addDice(int selectedDiceIndex,Dice dice )
    @Test
    public void addDiceTest2() {
        dicePool.addDice(0, mockDice);
        assertEquals(2, dicePool.getDice(0).getIntensity());
        assertEquals(DiceColor.GREEN, dicePool.getDice(0).getColor());
    }


    // Soon this method will be updated to throw an exception
    // This method test that getDice only returns a Dice without deleting it from the DicePool
    @Test
    public void getDiceTest() {
        dicePool = new DicePool(18, 18, 18, 18, 18);
        assertEquals(90, dicePool.getDicePoolSize());
        Dice dice = dicePool.getDice(23);
        assertEquals(90, dicePool.getDicePoolSize());
        assertEquals(dice.getIntensity(), dicePool.getDice(23).getIntensity());
        assertEquals(dice.getColor(), dicePool.getDice(23).getColor());
    }


    @Test
    public void removeallDicesTest() {
        dicePool = new DicePool(18, 18, 18, 18, 18);
        assertEquals(90, dicePool.getDicePoolSize());
        dicePool.removeallDices();
        assertEquals(0, dicePool.getDicePoolSize());
    }


    @Test
    public void addAllDices() {
        List<Dice> dices = Arrays.asList(mockDice, mockDice);
        assertEquals(0, dicePool.getDicePoolSize());
        dicePool.addallDices(dices);
        assertEquals(2, dicePool.getDicePoolSize());
    }


    @Test
    public void getAllDicesButNotRemoveTest() {
        dicePool = new DicePool(18, 18, 18, 18, 18);
        List<Dice> dices= new LinkedList<Dice>();

        dices = dicePool.getallDicesbutnotremove();
        assertEquals(90, dicePool.getDicePoolSize());

        for (int i = 0; i < dicePool.getDicePoolSize(); i++){
            assertEquals(dices.get(i).getIntensity(), dicePool.getDice(i).getIntensity());
            assertEquals(dices.get(i).getColor(), dicePool.getDice(i).getColor());
        }
    }



    // This method in the tested class will soon throw an EmptyException
    @Test
    public void extractFromEmptyDicePoolTest() {
        assertNull(dicePool.extractDice());
    }


}