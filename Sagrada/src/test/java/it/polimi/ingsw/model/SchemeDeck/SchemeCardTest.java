package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileyetOccupiedException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SchemeCardTest {

    private SchemeCard schemeCard;
    private Dice mockDice;

    @Before
    public void before() throws IOException, MapConstrainReadingException {
        schemeCard = new SchemeCard(3);
        mockDice = mock(Dice.class);

        when(mockDice.getIntensity()).thenReturn(2);
        when(mockDice.getColor()).thenReturn(DiceColor.YELLOW);
    }

    @Test
    public void getTest() throws OutOfMatrixException {
        assertEquals(3, schemeCard.getID());
        assertEquals(5, schemeCard.getMaxColumn());
        assertEquals(4, schemeCard.getMaxRow());
        assertEquals("3. Fractal Drops", schemeCard.getMapName());
        assertEquals(3, schemeCard.getDifficulty());

        // rand.nextInt interval is [0, i)
        Random rand = new Random();
        assertFalse(schemeCard.IsTileOccupied(rand.nextInt(schemeCard.getMaxRow()), rand.nextInt(schemeCard.getMaxColumn())));

    }


    @Test
    public void setDiceWithConstrain() throws OutOfMatrixException, TileConstrainException {
        // First move, Dice must be on the border
        assertFalse(schemeCard.SettableHere(mockDice, 1, 1, false, false));

        // Putting the Dice in a Tile with color constrains respected
        assertTrue(schemeCard.SettableHere(mockDice, 0, 3, false, false));
        schemeCard.setDice(mockDice, 0, 3, false, false, false);

        // Putting the Dice in a Tile with color constrains NOT respected
        assertFalse(schemeCard.SettableHere(mockDice, 1, 0, false, false));

        // Putting the second Dice in a non-border Tile but without another Dice near
        assertFalse(schemeCard.SettableHere(mockDice, 1, 1, false, false));

        // This last 2 gives error

        // Putting the second Dice in a border Tile with another dice near in diagonal
        assertTrue(schemeCard.SettableHere(new Dice(DiceColor.GREEN), 1, 4, false, false));

        // Putting the second Dice in a border Tile with another dice near in vertical
        assertTrue(schemeCard.SettableHere(new Dice(DiceColor.GREEN), 1, 3, false, false));
    }


    @Test (expected = TileyetOccupiedException.class)
    public void setTwoDiceSamePlaceException() throws OutOfMatrixException, TileConstrainException, DiceNotExistantException {
        schemeCard.setDice(mockDice, 0, 0, true, true, true);
        assertFalse(schemeCard.SettableHere(mockDice, 0, 0, true, true));
        assertEquals(DiceColor.YELLOW, schemeCard.getDiceColour(0,0));
        assertEquals(2, schemeCard.getDiceIntensity(0,0));
        schemeCard.setDice(mockDice, 0, 0, true, true, true);
    }




    @Test
    public void toStringTest() throws OutOfMatrixException, TileConstrainException {
        assertEquals("3. Fractal Drops%Difficoltà della mappa: 3%0_-4*-0_-0YELLOW-6*-!0RED-0_-2*-0_-0_-!0_-0_-0RED-0VIOLET-1*-!0BLUE-0YELLOW-0_-0_-0_-!", schemeCard.toString());
        schemeCard.setDice(mockDice, 0, 0, true, true, true);
        assertEquals("3. Fractal Drops%Difficoltà della mappa: 3%2yellow-4*-0_-0YELLOW-6*-!0RED-0_-2*-0_-0_-!0_-0_-0RED-0VIOLET-1*-!0BLUE-0YELLOW-0_-0_-0_-!", schemeCard.toString());
    }
}