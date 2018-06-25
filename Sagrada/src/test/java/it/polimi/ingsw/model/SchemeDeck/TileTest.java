package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.NotRespectedNumberConstrainException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.NotRispectedColorConstrainException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileyetOccupiedException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Tile doesn't throw an exception when we try to place a dice in an already occupied place

public class TileTest {

    private Tile tile;
    private Dice mockDice;

    @Before
    public void before() {
        tile = new Tile(0,0);
        mockDice = mock(Dice.class);

        when(mockDice.getIntensity()).thenReturn(2);
        when(mockDice.getColor()).thenReturn(DiceColor.VIOLET);
    }


    @Test
    public void placeAndRemoveDiceNoConstrain() throws TileConstrainException, DiceNotExistantException {
        assertFalse(tile.isOccupied());
        tile.setDice(mockDice, true, true);
        assertTrue(tile.isOccupied());
        tile.removeDice();
        assertFalse(tile.isOccupied());
    }



    @Test (expected = DiceNotExistantException.class)
    public void getDiceNotExistantFromTile() throws DiceNotExistantException {
        tile.getDice();
    }


    @Test (expected = NotRispectedColorConstrainException.class)
    public void setColorConstrainException() throws TileConstrainException {
        tile.setColourConstrain(DiceColor.GREEN);
        tile.setHaveColor_constrain(true);
        assertFalse(tile.settableDiceHere(mockDice, false, true));
        tile.setDice(mockDice, false, true);
    }


    @Test (expected = NotRespectedNumberConstrainException.class)
    public void setIntensityConstrainException() throws TileConstrainException {
        tile.setNumberConstrain(3);
        tile.setHaveNumber_constrain(true);
        tile.setDice(mockDice, true, false);
    }


    @Test
    public void getRowGetColumn() {
        assertEquals(0, tile.getRow());
        assertEquals(0, tile.getColumn());
    }

}