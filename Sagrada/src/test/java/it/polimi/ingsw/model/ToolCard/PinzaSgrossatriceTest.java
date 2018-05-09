package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DecreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.IncreaseNotAllowedException;
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
    private Dice mockDice;
    private int mockDiceIntensity = 5;


    @Before
    public void before() {
        this.pinzaSgrossatrice = new PinzaSgrossatrice(mockPlayer, 3, 1);

        this.mockPlayer = mock(Player.class);
        this.mockGametable = mock(Gametable.class);
        this.mockDice = mock(Dice.class);

        when(this.mockPlayer.getGametable()).thenReturn(mockGametable);
        when(this.mockGametable.getRoundDicepool().getDice(3)).thenReturn(mockDice);
    }

    @Test
    public void testGetId() {
        assertEquals(1, pinzaSgrossatrice.getID());
    }

    @Test
    public void testGetCardTitle() {
        assertEquals("Pinza Sgrossatrice", pinzaSgrossatrice.getCardTitle());
    }

    @Test (expected = ToolIllegalOperationException.class)
    public void testExecuteOperation0 () throws ToolIllegalOperationException {
        doThrow(new DecreaseNotAllowedException()).when(mockDice).decreaseIntensity();
        pinzaSgrossatrice = new PinzaSgrossatrice(mockPlayer, 3, 0);
        pinzaSgrossatrice.execute();
    }

    @Test (expected = ToolIllegalOperationException.class)
    public void testExecuteOperation1 () throws ToolIllegalOperationException {
        doThrow(new IncreaseNotAllowedException()).when(mockDice).increaseIntensity();
        pinzaSgrossatrice = new PinzaSgrossatrice(mockPlayer, 3, 1);
        pinzaSgrossatrice.execute();
    }



}