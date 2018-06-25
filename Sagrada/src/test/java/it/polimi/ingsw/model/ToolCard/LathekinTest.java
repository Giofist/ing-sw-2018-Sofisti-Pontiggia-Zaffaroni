package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// COMPLETE
// FIXED player.getScheme().setDice(removedDice2, toolRequestClass.getNewRow2(), toolRequestClass.getNewColumn2(),false,false, false);
// DUBBIO PRIMO DADO DA PIAZZARE
// ERROR in implementation, when we try to place 2 different Dices in the same place (CORRECT)
// same error could happen when the first dice is placed correctly and the second one receives an error

public class LathekinTest {

    private Lathekin lathekin;
    private Player player;
    private Dice mockDice;
    private Dice mockDice2;
    private SchemeCard schemeCard;
    private ToolRequestClass toolRequestClass;
    private Match mockMatch;

    @Before
    public void before() throws IOException, MapConstrainReadingException, CardIdNotAllowedException {
        // Tested class
        lathekin = new Lathekin();

        // Required classes for game interaction
        player = new Player();
        mockDice = mock(Dice.class);
        mockDice2 = mock(Dice.class);
        mockMatch = mock(Match.class);
        schemeCard = new SchemeCard(3);
        toolRequestClass = new ToolRequestClass();

        // SchemeCard setup
        schemeCard.setTwinCard(new SchemeCard(4));


        // Player setup
        player.addExtractedSchemeCard(schemeCard);
        player.setMatch(mockMatch);
        player.setPlayerState(State.STARTTURNSTATE);
        player.setScheme(3);


        // Mock Behaviours
        doNothing().when(mockMatch).countDown();
        when(mockDice.getColor()).thenReturn(DiceColor.VIOLET);
        when(mockDice.getIntensity()).thenReturn(2);
        when(mockDice2.getColor()).thenReturn(DiceColor.YELLOW);
        when(mockDice2.getIntensity()).thenReturn(6);

    }


    // This method tests if a Player specifies by mistake a wrong position on the schemeCard from where to pick a Dice
    @Test
    public void getNotExistantDiceExceptionTest() throws ToolIllegalOperationException {
        toolRequestClass.setOldRow1(1);
        toolRequestClass.setOldColumn1(6);
        toolRequestClass.setNewRow1(2);
        toolRequestClass.setNewColumn1(2);
        toolRequestClass.setOldRow2(1);
        toolRequestClass.setOldColumn2(6);
        toolRequestClass.setNewRow2(2);
        toolRequestClass.setNewColumn2(2);

        try {
            this.lathekin.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        // Here we arrive if the exception is not correctly thrown, we don't want to end up here
        assertEquals(0, 1);
    }



    // This method tests when there is a constrain in placing the Dice
    @Test
    public void executeTileColorConstrainException() throws OutOfMatrixException, TileConstrainException, ToolIllegalOperationException, DiceNotExistantException {
        schemeCard.setDice(mockDice, 0, 2, false, false, true);
        schemeCard.setDice(mockDice2, 0,3, false, false, true);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(2);
        toolRequestClass.setNewRow1(1);
        toolRequestClass.setNewColumn1(0);
        toolRequestClass.setOldRow2(0);
        toolRequestClass.setOldColumn2(3);
        toolRequestClass.setNewRow2(0);
        toolRequestClass.setNewColumn2(0);

        try {
            this.lathekin.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertFalse(schemeCard.IsTileOccupied(1, 0));
            assertFalse(schemeCard.IsTileOccupied(0, 0));
            assertTrue(schemeCard.getDice(0, 2) == mockDice);
            assertTrue(schemeCard.getDice(0, 3) == mockDice2);
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        // Here we arrive if the exception is not correctly thrown, we don't want to end up here
        assertEquals(0, 1);
    }


    @Test
    public void executeOK() throws OutOfMatrixException, TileConstrainException, ToolIllegalOperationException, DiceNotExistantException {
        schemeCard.setDice(mockDice, 0, 2, false, false, true);
        schemeCard.setDice(mockDice2, 0,3, false, false, true);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(2);
        toolRequestClass.setNewRow1(2);
        toolRequestClass.setNewColumn1(0);
        toolRequestClass.setOldRow2(0);
        toolRequestClass.setOldColumn2(3);
        toolRequestClass.setNewRow2(3);
        toolRequestClass.setNewColumn2(1);

        lathekin.execute(player, toolRequestClass);

        assertFalse(schemeCard.IsTileOccupied(0, 2));
        assertFalse(schemeCard.IsTileOccupied(0, 3));
        assertTrue(schemeCard.getDice(2, 0) == mockDice);
        assertTrue(schemeCard.getDice(3, 1) == mockDice2);
        assertEquals(State.HASUSEDATOOLCARDACTIONSTATE, player.getPlayerState().getState());
    }


    @Test
    public void executeSameDiceSelectionException() throws OutOfMatrixException, TileConstrainException, DiceNotExistantException {
        schemeCard.setDice(mockDice, 0, 2, false, false, true);
        schemeCard.setDice(mockDice2, 0,3, false, false, true);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(2);
        toolRequestClass.setNewRow1(2);
        toolRequestClass.setNewColumn1(0);
        toolRequestClass.setOldRow2(0);
        toolRequestClass.setOldColumn2(2);
        toolRequestClass.setNewRow2(3);
        toolRequestClass.setNewColumn2(1);

        try {
            this.lathekin.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertFalse(schemeCard.IsTileOccupied(2, 0));
            assertFalse(schemeCard.IsTileOccupied(3, 1));

            assertTrue(schemeCard.getDice(0, 2) == mockDice);
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        // Here we arrive if the exception is not correctly thrown, we don't want to end up here
        assertEquals(0, 1);
    }



    @Test
    public void executeTwoDiceSameFinalPositionException() throws OutOfMatrixException, TileConstrainException, DiceNotExistantException {
        schemeCard.setDice(mockDice, 0, 2, false, false, true);
        schemeCard.setDice(mockDice2, 0,3, false, false, true);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(2);
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(0);
        toolRequestClass.setOldRow2(0);
        toolRequestClass.setOldColumn2(3);
        toolRequestClass.setNewRow2(0);
        toolRequestClass.setNewColumn2(0);

        try {
            this.lathekin.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertFalse(schemeCard.IsTileOccupied(0, 0));

            assertTrue(schemeCard.getDice(0, 2) == mockDice);
            assertTrue(schemeCard.getDice(0, 3) == mockDice2);
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        // Here we arrive if the exception is not correctly thrown, we don't want to end up here
        assertEquals(0, 1);
    }

    // Minor tests
    @Test
    public void testGetId() {
        assertEquals(4, this.lathekin.getID());
    }

    @Test
    public void testGetCardTitle() {
        assertEquals("Lathekin", lathekin.getCardTitle());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("Muovi esattamente due dadi.\n" +
                "Rispetta tutte le restrizioni di piazzamento.",
                lathekin.getDescription());
    }
}
