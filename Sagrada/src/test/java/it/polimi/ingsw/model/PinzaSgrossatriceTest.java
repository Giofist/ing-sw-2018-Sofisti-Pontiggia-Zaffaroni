package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.ToolCard.PinzaSgrossatrice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PinzaSgrossatriceTest {
    private PinzaSgrossatrice pinzaSgrossatrice;
    private Player mockPlayer;
    private Gametable mockGametable;
    private int selectedDiceIndex;
    private int operation;
    private int getIntensityReturnValue;
    private Dice mockDice;

    @Before
    public void before() {
        this.mockPlayer = mock(Player.class);

        this.pinzaSgrossatrice = new PinzaSgrossatrice(mockPlayer, 3, 1);
    }

    @Test
    public void testGetId() {
        assertEquals(1, pinzaSgrossatrice.getID());
    }

}