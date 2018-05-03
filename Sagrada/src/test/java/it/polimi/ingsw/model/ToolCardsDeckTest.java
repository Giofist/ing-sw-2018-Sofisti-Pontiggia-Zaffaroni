package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.ToolCard.PinzaSgrossatrice;
import it.polimi.ingsw.model.ToolCard.ToolAction;
import it.polimi.ingsw.model.ToolCard.ToolCardsDeck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToolCardsDeckTest {

    private ToolCardsDeck toolCardsDeck;
    private ToolAction mockToolCard;

    @Before
    public void before() {
        toolCardsDeck = new ToolCardsDeck();
        mockToolCard = mock(PinzaSgrossatrice.class);

        when(mockToolCard.getID()).thenReturn(1);
    }

    @Test
    public void constructorTest() {
        for (int costIndex = 0; costIndex < 12; costIndex++ ) {
            assertEquals(1, toolCardsDeck.getCost(costIndex));
        }
    }

    @Test
    public void setCostTest() {
        // After constructor
        assertEquals(1, toolCardsDeck.getCost(0));

        // After first payment
        toolCardsDeck.setCostOfAction(0);
        assertEquals(2, toolCardsDeck.getCost(0));

        // After second payment (price should remain the same)
        toolCardsDeck.setCostOfAction(0);
        assertEquals(2, toolCardsDeck.getCost(0));
    }


    @Test
    public void doActionTest() throws ToolIllegalOperationException, TileConstrainException {
        toolCardsDeck.doAction(mockToolCard);

        // When the action is performed the corresponding cost must increase to 2
        assertEquals(2, toolCardsDeck.getCost(1));
    }


    @Test (expected = ToolIllegalOperationException.class)
    public void doActionTestException() throws ToolIllegalOperationException, TileConstrainException {
        doThrow(new ToolIllegalOperationException()).when(mockToolCard).execute();
        toolCardsDeck.doAction(mockToolCard);
    }

}