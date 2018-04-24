package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DicePoolTest {
    DicePool dicePool;
    Dice mockDice;


    @Before
    public void before() {
        this.dicePool = new DicePool(18, 18, 18, 18, 18);
        this.mockDice = mock(Dice.class);
        when(this.mockDice.getColor()).thenReturn(DiceColor.BLUE);
        when(this.mockDice.getIntensity()).thenReturn(2);

        // Check if the constructor created 90 dices
        assertEquals("Not enough dices initialized", 90, this.dicePool.getDicePoolSize());
        testCorrectDicesNumbers(18, 18, 18, 18,18);
    }



    @Test
    public void testCorrectDicesNumbers(int expectedBlue, int expectedGreen, int expectedRed, int expectedViolet, int expectedYellow) {

        int blueDices = 0;
        int greenDices = 0;
        int redDices = 0;
        int violetDices = 0;
        int yellowDices = 0;
        Dice currentDice;

        for (int i = 0; i < this.dicePool.getDicePoolSize(); i++) {
            currentDice = this.dicePool.getDice(i);
            switch (currentDice.getColor()){
                case BLUE:
                    blueDices += 1;
                    break;
                case GREEN:
                    greenDices += 1;
                    break;
                case RED:
                    redDices += 1;
                    break;
                case VIOLET:
                    violetDices += 1;
                    break;
                case YELLOW:
                    yellowDices += 1;
                    break;
            }
        }

        assertEquals("[Blue] Wrong value", expectedBlue, blueDices);
        assertEquals("[Green] Wrong value", expectedGreen, greenDices);
        assertEquals("[Red] Wrong value", expectedRed, redDices);
        assertEquals("[Violet] Wrong value", expectedViolet, violetDices);
        assertEquals("[Yellow] Wrong value", expectedYellow, yellowDices);
    }



    @Test
    public void scrambleDicePool() {
        int dicePoolSize = this.dicePool.getDicePoolSize();
        this.dicePool.scrambleDicePool();

        // Check if after the Scramble operation the dicePoolSize has the same size
        assertEquals("DicePool changed its size",dicePoolSize, this.dicePool.getDicePoolSize());
        testCorrectDicesNumbers(18, 18, 18, 18, 18);
    }



    @Test
    public void extractDice() {
        int dicePoolSize = this.dicePool.getDicePoolSize();
        Dice extractedDice = mockDice;

        // DicePool of size N after someone extracted a Dice should be N-1
        assertEquals("Something went wrong with Dicepool extraction", dicePoolSize-1, this.dicePool.getDicePoolSize());

        switch (extractedDice.getColor()){
            case BLUE: testCorrectDicesNumbers(17,18,18,18,18); break;
            case GREEN: testCorrectDicesNumbers(18,17,18,18,18); break;
            case RED: testCorrectDicesNumbers(18,18,17,18,18); break;
            case VIOLET: testCorrectDicesNumbers(18,18,18,17,18); break;
            case YELLOW: testCorrectDicesNumbers(18,18,18,18,17); break;

        }
    }


    
    @Test
    public void addDice() {
        int dicePoolSize = this.dicePool.getDicePoolSize();
        dicePool.addDice(mockDice);

        assertEquals("Wrong DicePool length after inserting a Dice", dicePoolSize+1, this.dicePool.getDicePoolSize());
    }
}