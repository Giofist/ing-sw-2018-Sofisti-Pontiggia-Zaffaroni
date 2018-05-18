package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DicePool;
import it.polimi.ingsw.model.Exceptions.DecreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.IncreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PinzaSgrossatriceException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Gametable;
import it.polimi.ingsw.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

// Complete

public class PinzaSgrossatriceTest {
    // Tested object
    private PinzaSgrossatrice pinzaSgrossatrice;

    // Mock objects
    private Player mockPlayer;
    private Gametable mockGametable;
    private DicePool mockRoundDicePool;
    private Dice mockDice;
    private int mockDiceIntensity = 5;


    @Before
    public void before() {
        this.pinzaSgrossatrice = new PinzaSgrossatrice(3, 1);

        this.mockPlayer = mock(Player.class);
        this.mockGametable = mock(Gametable.class);
        this.mockRoundDicePool = mock(DicePool.class);
        this.mockDice = mock(Dice.class);

        when(this.mockPlayer.getGametable()).thenReturn(mockGametable);
        when(this.mockGametable.getRoundDicepool()).thenReturn(mockRoundDicePool);
        when(this.mockRoundDicePool.getDice(3)).thenReturn(mockDice);
    }



    // This test will test the situation in which we try to decrement the intensity of a Dice with value 1
    @Test (expected = ToolIllegalOperationException.class)
    public void testExecuteOperation0DecreaseException () throws ToolIllegalOperationException, DecreaseNotAllowedException {
        // Here we prepare the PinzaSgrossatrice object with 2 as Dice index to change and we try to decrease its intensity
        // in a situation where is equal to 1
        pinzaSgrossatrice = new PinzaSgrossatrice(2, 0);
        when(this.mockRoundDicePool.getDice(2)).thenReturn(mockDice);

        // The mockDice will throw a DecreaseNotAllowedException because we are trying to decrease from 1 to 6
        doThrow(new DecreaseNotAllowedException()).when(mockDice).decreaseIntensity();

        // Testing of the execute method
        pinzaSgrossatrice.execute(mockPlayer);
    }


    // This test will test the situation in which we try to increment the intensity of a Dice with value 6
    @Test (expected = ToolIllegalOperationException.class)
    public void testExecuteOperation1 () throws ToolIllegalOperationException, IncreaseNotAllowedException {
        // Here we prepare the PinzaSgrossatrice object with 3 as Dice index to change and we try to increase its intensity
        // in a situation where is equal to 6
        pinzaSgrossatrice = new PinzaSgrossatrice(3, 1);

        // The mockDice will throw a DecreaseNotAllowedException because we are trying to decrease from 1 to 6
        doThrow(new IncreaseNotAllowedException()).when(mockDice).increaseIntensity();

        // Testing of the execute method
        pinzaSgrossatrice.execute(mockPlayer);
    }


    @Test
    public void testGetId() {
        assertEquals(1, pinzaSgrossatrice.getID());
    }

    @Test
    public void testGetCardTitle() {
        assertEquals("Pinza Sgrossatrice", pinzaSgrossatrice.getCardTitle());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1.\n" +
                              "Non puoi cambiare un 6 in 1 o un 1 in 6.", pinzaSgrossatrice.getDescription());
    }

}
