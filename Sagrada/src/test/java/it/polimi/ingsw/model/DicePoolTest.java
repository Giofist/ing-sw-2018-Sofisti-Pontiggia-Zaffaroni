package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
    Untested methods:
        scrambleDicePool gets tested indirectly shuffleDicePoolTest
        toString
        removeDice
*/

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
    public void insertDiceTest() throws DicepoolIndexException {
        assertEquals(0, dicePool.getDicePoolSize());

        dicePool.insertDice(DiceColor.YELLOW);
        assertEquals(1, dicePool.getDicePoolSize());
        assertEquals(DiceColor.YELLOW, dicePool.getDice(0).getColor());
    }


    // This method tests the addDice function which is a method used only for RoundDicePool for which a Dice is directly passed
    @Test
    public void addDiceTest() throws DicepoolIndexException {
        assertEquals(0, dicePool.getDicePoolSize());
        dicePool.addDice(mockDice);
        assertEquals(1, dicePool.getDicePoolSize());

        Dice dice = dicePool.getDice(0);
        assertEquals(2, dice.getIntensity());
        assertEquals(DiceColor.GREEN, dice.getColor());
    }


    // This method will test addDice(int selectedDiceIndex,Dice dice)
    @Test
    public void addDiceTest2() throws DicepoolIndexException {
        assertEquals(0, dicePool.getDicePoolSize());
        dicePool.addDice(0, mockDice);

        Dice dice = dicePool.getDice(0);
        assertEquals(2, dice.getIntensity());
        assertEquals(DiceColor.GREEN, dice.getColor());
    }



    @Test
    public void getDiceTest() throws DicepoolIndexException {
        dicePool = new DicePool(18, 18, 18, 18, 18);

        Dice dice = dicePool.getDice(23);
        assertEquals(90, dicePool.getDicePoolSize());

        assertEquals(dice.getIntensity(), dicePool.getDice(23).getIntensity());
        assertEquals(dice.getColor(), dicePool.getDice(23).getColor());
    }

    @Test (expected = DicepoolIndexException.class)
    public void getDiceExceptionTest() throws DicepoolIndexException {
        dicePool = new DicePool(18, 18, 18, 18, 18);
        dicePool.getDice(1000);
    }

    @Test (expected = DicepoolIndexException.class)
    public void getDiceEmpyDicepoolExceptionTest() throws DicepoolIndexException {
        dicePool.getDice(0);
    }


    @Test
    public void removeallDicesTest() {
        dicePool = new DicePool(18, 18, 18, 18, 18);
        assertEquals(90, dicePool.getDicePoolSize());
        dicePool.removeallDices();
        assertEquals(0, dicePool.getDicePoolSize());
    }


    @Test
    public void addAllDicesTest() throws DicepoolIndexException {
        List<Dice> dices = Arrays.asList(mockDice, mockDice);
        assertEquals(0, dicePool.getDicePoolSize());
        dicePool.addallDices(dices);
        assertEquals(2, dicePool.getDicePoolSize());

        assertEquals(DiceColor.GREEN, dicePool.getDice(0).getColor());
        assertEquals(DiceColor.GREEN, dicePool.getDice(1).getColor());
        assertEquals(2, dicePool.getDice(0).getIntensity());
        assertEquals(2, dicePool.getDice(1).getIntensity());
    }


    @Test
    public void getAllDicesButNotRemoveTest() throws DicepoolIndexException {
        dicePool = new DicePool(18, 18, 18, 18, 18);
        List<Dice> dices= new LinkedList<Dice>();

        dices = dicePool.getallDicesbutnotremove();
        assertEquals(90, dicePool.getDicePoolSize());

        for (int i = 0; i < dicePool.getDicePoolSize(); i++){
            assertEquals(dices.get(i).getIntensity(), dicePool.getDice(i).getIntensity());
            assertEquals(dices.get(i).getColor(), dicePool.getDice(i).getColor());
        }
    }


    @Test
    public void extractTest() throws DicepoolIndexException {
        dicePool = new DicePool(18,18,18,18,18);

        assertEquals(90, dicePool.getDicePoolSize());
        dicePool.extractDice();
        assertEquals(89, dicePool.getDicePoolSize());
    }

    // This method in the tested class will soon throw an EmptyException
    @Test (expected = DicepoolIndexException.class)
    public void extractEmpyDicepoolExceptionTest() throws DicepoolIndexException {
        dicePool.extractDice();
    }

}