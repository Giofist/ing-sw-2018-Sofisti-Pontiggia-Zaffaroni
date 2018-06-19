package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
    Untested methods:
    toString
 */

public class RoundTrackTest {

    private RoundTrack roundTrack;
    private LinkedList<Dice> dices;
    private Dice mockDice;

    @Before
    public void before() {
        roundTrack = new RoundTrack();
        mockDice = mock(Dice.class);
        dices = new LinkedList<Dice>();
        dices.add(mockDice);
        dices.add(mockDice);
        dices.add(mockDice);


        when(mockDice.getIntensity()).thenReturn(2);
        when(mockDice.getColor()).thenReturn(DiceColor.RED);
    }


    @Test
    public void setAndGetRoundTrackDicesTest() throws RoundTrackException {
        roundTrack.setRoundTrackDices(3, dices);
        assertEquals(0, roundTrack.getroundTrackDices(1).getDicePoolSize());
        assertEquals(0, roundTrack.getroundTrackDices(2).getDicePoolSize());
        assertEquals(3, roundTrack.getroundTrackDices(3).getDicePoolSize());
        assertEquals(0, roundTrack.getroundTrackDices(4).getDicePoolSize());
        assertEquals(0, roundTrack.getroundTrackDices(5).getDicePoolSize());
        assertEquals(0, roundTrack.getroundTrackDices(6).getDicePoolSize());
        assertEquals(0, roundTrack.getroundTrackDices(7).getDicePoolSize());
        assertEquals(0, roundTrack.getroundTrackDices(8).getDicePoolSize());
        assertEquals(0, roundTrack.getroundTrackDices(9).getDicePoolSize());
        assertEquals(0, roundTrack.getroundTrackDices(10).getDicePoolSize());
    }

    @Test (expected = RoundTrackException.class)
    public void setRoundTrackException() throws RoundTrackException {
        roundTrack.setRoundTrackDices(0, dices);
    }

    @Test (expected = RoundTrackException.class)
    public void getRoundTrackDicesExceptionTest() throws RoundTrackException {
        roundTrack.getroundTrackDices(0);
    }

    @Test
    public void allColorsTest() throws RoundTrackException {
        List<DiceColor> colors;
        roundTrack.setRoundTrackDices(3, dices);
        colors = roundTrack.allColors();

        assertEquals(3, colors.size());
        assertEquals(DiceColor.RED, colors.get(0));
        assertEquals(DiceColor.RED, colors.get(1));
        assertEquals(DiceColor.RED, colors.get(2));
    }
}