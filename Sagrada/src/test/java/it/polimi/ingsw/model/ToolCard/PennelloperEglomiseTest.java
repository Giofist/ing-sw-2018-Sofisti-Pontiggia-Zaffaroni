package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.AlesatorePerLaminadiRameException;
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

// Complete

public class PennelloperEglomiseTest {

    private PennelloperEglomise pennelloperEglomise;
    private Player player;
    private Dice mockDice;
    private SchemeCard schemeCard;
    private ToolRequestClass toolRequestClass;
    private Match mockMatch;

    @Before
    public void before() throws IOException, MapConstrainReadingException, CardIdNotAllowedException {
        pennelloperEglomise = new PennelloperEglomise();
        player = new Player();
        mockDice = mock(Dice.class);
        mockMatch = mock(Match.class);
        doNothing().when(mockMatch).countDown();
        schemeCard = new SchemeCard(3);
        schemeCard.setTwinCard(new SchemeCard(4));
        toolRequestClass = new ToolRequestClass();

        player.addExtractedSchemeCard(schemeCard);
        player.setMatch(mockMatch);
        player.setPlayerState(State.STARTTURNSTATE);
        player.setScheme(3);


        when(mockDice.getColor()).thenReturn(DiceColor.VIOLET);
        when(mockDice.getIntensity()).thenReturn(2);

    }


    // This method tests if a Player specifies by mistake a wrong position on the schemeCard from where to pick a Dice
    @Test
    public void getNotExistantDiceExceptionTest() throws ToolIllegalOperationException {
        toolRequestClass.setOldRow1(1);
        toolRequestClass.setOldColumn1(6);
        toolRequestClass.setNewRow1(2);
        toolRequestClass.setNewColumn1(2);

        try {
            this.pennelloperEglomise.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        // Here we arrive if the exception is not correctly thrown, we don't want to end up here
        assertEquals(0, 1);
    }



    // This method tests when there is a constrain in placing the Dice
    @Test
    public void executeTileValueConstrainException() throws OutOfMatrixException, TileConstrainException, ToolIllegalOperationException, DiceNotExistantException {
        schemeCard.setDice(mockDice, 0, 2, false, false, false);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(2);
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(1);

        try {
            this.pennelloperEglomise.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertFalse(schemeCard.IsTileOccupied(2, 3));
            assertTrue(schemeCard.getDice(0, 2) == mockDice);
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        // Here we arrive if the exception is not correctly thrown, we don't want to end up here
        assertEquals(0, 1);
    }


    // Test the situation in which the User specifies a new wrong position
    @Test
    public void executeWithInvalidNewPositionException() throws OutOfMatrixException, TileConstrainException, DiceNotExistantException, ToolIllegalOperationException {
        schemeCard.setDice(mockDice, 0, 2, false, false, false);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(2);
        toolRequestClass.setNewRow1(2);
        toolRequestClass.setNewColumn1(6);

        try {
            this.pennelloperEglomise.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertFalse(schemeCard.IsTileOccupied(2, 3));
            assertTrue(schemeCard.getDice(0, 2) == mockDice);
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        // Here we arrive if the exception is not correctly thrown, we don't want to end up here
        assertEquals(0, 1);
    }


    @Test
    public void executeEverythingOk() throws OutOfMatrixException, TileConstrainException, DiceNotExistantException, ToolIllegalOperationException {
        schemeCard.setDice(mockDice, 0, 2, false, false, false);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(2);
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(0);


        this.pennelloperEglomise.execute(player, toolRequestClass);

        assertFalse(schemeCard.IsTileOccupied(0, 2));
        assertTrue(schemeCard.getDice(0, 0) == mockDice);
        assertEquals(State.HASUSEDATOOLCARDACTIONSTATE, player.getPlayerState().getState());
    }


    @Test
    public void executeEverythingOkIgnoringColorConstrain() throws OutOfMatrixException, TileConstrainException, ToolIllegalOperationException, DiceNotExistantException {
        schemeCard.setDice(mockDice, 0, 2, false, false, false);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(2);

        // In this new position there should be a color constrain but the toolcard allows me to ignore it
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(3);


        this.pennelloperEglomise.execute(player, toolRequestClass);

        assertFalse(schemeCard.IsTileOccupied(0, 2));
        assertTrue(schemeCard.getDice(0, 3) == mockDice);
        assertEquals(State.HASUSEDATOOLCARDACTIONSTATE, player.getPlayerState().getState());
    }



    // Minor tests
    @Test
    public void getIDTest() {
        assertEquals(2, pennelloperEglomise.getID());
    }

    @Test
    public void getCardTitleTest() {
        assertEquals("Pennello per Eglomise", pennelloperEglomise.getCardTitle());
    }

    @Test
    public void getCardDescriptionTest() {
        assertEquals("Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore.\n" +
                "Devi rispettare tutte le altre restrizioni di piazzamento.", pennelloperEglomise.getDescription());
    }
}