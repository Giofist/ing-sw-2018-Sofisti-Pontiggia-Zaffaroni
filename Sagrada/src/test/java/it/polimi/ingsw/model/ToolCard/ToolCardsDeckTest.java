package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.NotEnoughTokenException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Exceptions.WrongToolCardIDException;
import it.polimi.ingsw.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ToolCardsDeckTest {

    private ToolCardsDeck toolCardsDeck;
    private Player mockPlayer;
    private ToolRequestClass toolRequestClass;

    @Before
    public void before() {
        // Tested method
        toolCardsDeck = new ToolCardsDeck();

        // Mock classes
        mockPlayer = mock(Player.class);
        toolRequestClass = new ToolRequestClass();

        //when(mockPlayer.
    }

    @Test
    public void getCostOK() {

    }

    @Test
    public void getCostException() {
        try {
            toolCardsDeck.getCost(100);
        } catch (WrongToolCardIDException e){
            return;
        }

        assertFalse(0 == 0);
    }


    @Test
    public void getToolCardsTest() {
        List toolCards  = toolCardsDeck.getcards();

        assertEquals(3, toolCards.size());
        assertTrue(toolCards.get(0).getClass() != toolCards.get(1).getClass());
        assertTrue(toolCards.get(1).getClass() != toolCards.get(2).getClass());
        assertTrue(toolCards.get(0).getClass() != toolCards.get(2).getClass());
    }


    @Test
    public void doActionTest() throws WrongToolCardIDException, ToolIllegalOperationException {
        List toolCards  = toolCardsDeck.getcards();
        ToolAction toolAction = (ToolAction) toolCards.get(0);
        int toolId = toolAction.getID();

        try {
            toolCardsDeck.doAction(toolId, mockPlayer, toolRequestClass);
        } catch (NotEnoughTokenException e){
            assertEquals(0, mockPlayer.getToken());
            return;
        }

        // We don't want to end here
        assertTrue( 0 == 1);
    }



}