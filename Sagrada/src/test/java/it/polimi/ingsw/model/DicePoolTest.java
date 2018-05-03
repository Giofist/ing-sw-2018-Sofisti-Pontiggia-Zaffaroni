package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DicePoolTest {

    private DicePool dicePool;

    @Before
    public void before() {
        dicePool = new DicePool();
    }

    @Test
    public void constructorEmptyDicePoolTest() {
        assertEquals(0, dicePool.getDicePoolSize());
    }

    @Test
    public void constructorDicePoolTest() {
        dicePool = new DicePool(18, 18, 18, 18, 18);
        assertEquals(90, dicePool.getDicePoolSize());
    }

    @Test
    public void constructorDicePoolWithWrongParametersTest() {
        dicePool = new DicePool(-1, 2, 4, 0, 1);
        assertEquals(0, dicePool.getDicePoolSize());
    }

    @Test
    public void extractFromEmptyDicePoolTest() {
        assertNull(dicePool.extractDice());
    }


}