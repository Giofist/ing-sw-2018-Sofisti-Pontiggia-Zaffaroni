package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DicePoolTest {
    DicePool dicePool;
    Dice mockDice;
    int expectedBlue = 0;
    int expectedGreen = 0;
    int expectedRed = 0;
    int expectedViolet = 0;
    int expectedYellow = 0;


    @Before
    public void before() {
        this.dicePool = new DicePool(18, 18, 18, 18, 18);

        this.mockDice = mock(Dice.class);
        when(this.mockDice.getColor()).thenReturn(DiceColor.BLUE);
        when(this.mockDice.getIntensity()).thenReturn(2);

        this.expectedBlue = 18;
        this.expectedGreen = 18;
        this.expectedRed = 18;
        this.expectedViolet = 18;
        this.expectedYellow = 18;
    }

    @Test
    public void testDicePoolSize(){
        int expectedSize = expectedBlue + expectedGreen + expectedRed + expectedViolet + expectedYellow;
        assertEquals("Wrong Size", expectedSize, this.dicePool.getDicePoolSize());
    }


    @Test
    public void testCorrectDicesNumbers() {

        int blueDices = 0;
        int greenDices = 0;
        int redDices = 0;
        int violetDices = 0;
        int yellowDices = 0;


        for (int i = 0; i < this.dicePool.getDicePoolSize(); i++) {
            switch (this.mockDice.getColor()){
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

        this.expectedBlue = 18;
        this.expectedGreen = 18;
        this.expectedRed = 18;
        this.expectedViolet = 18;
        this.expectedYellow = 18;
        testCorrectDicesNumbers();
    }



    @Test
    public void extractDice() {
        int dicePoolSize = this.dicePool.getDicePoolSize();
        Dice extractedDice = mockDice;

        // DicePool of size N after someone extracted a Dice should be N-1
        assertEquals("Something went wrong with Dicepool extraction", dicePoolSize-1, this.dicePool.getDicePoolSize());

        this.expectedBlue = 18;
        this.expectedGreen = 18;
        this.expectedRed = 18;
        this.expectedViolet = 18;
        this.expectedYellow = 18;

        switch (extractedDice.getColor()){
            case BLUE:
                this.expectedBlue = 17;
                testCorrectDicesNumbers();break;
            case GREEN:
                this.expectedGreen = 17;
                testCorrectDicesNumbers();break;
            case RED:
                this.expectedRed = 17;
                testCorrectDicesNumbers();break;
            case VIOLET:
                this.expectedViolet = 17;
                testCorrectDicesNumbers();break;
            case YELLOW:
                this.expectedYellow = 17;
                testCorrectDicesNumbers();break;

        }
    }



    @Test
    public void addDice() {
        int dicePoolSize = this.dicePool.getDicePoolSize();
        dicePool.addDice(mockDice);

        assertEquals("Wrong DicePool length after inserting a Dice", dicePoolSize+1, this.dicePool.getDicePoolSize());
    }
}