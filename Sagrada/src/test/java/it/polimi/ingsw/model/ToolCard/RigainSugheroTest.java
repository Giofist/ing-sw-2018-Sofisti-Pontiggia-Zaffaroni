package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.CardIdNotAllowedException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// COMPLETE

public class RigainSugheroTest {

    private RigainSughero toolCard;
    private Player player;
    private Dice mockDice;
    private SchemeCard schemeCard;
    private ToolRequestClass toolRequestClass;
    private Match mockMatch;
    private Gametable mockGametable;
    private DicePool roundDicePool;

    @Before
    public void before() throws IOException, MapConstrainReadingException, CardIdNotAllowedException, SchemeCardNotExistantException, OutOfMatrixException, TileConstrainException {
        // Tested class
        toolCard = new RigainSughero();

        // Required classes for game interaction
        player = new Player();
        mockDice = mock(Dice.class);
        mockMatch = mock(Match.class);
        mockGametable = mock(Gametable.class);
        schemeCard = new SchemeCard(3);
        toolRequestClass = new ToolRequestClass();
        roundDicePool = new DicePool();

        // Scheme Card setup
        schemeCard.setTwinCard(new SchemeCard(4));

        // Mock behaviours
        doNothing().when(mockMatch).countDown();
        when(mockMatch.getGametable()).thenReturn(mockGametable);
        when(mockGametable.getRoundDicepool()).thenReturn(roundDicePool);
        when(mockDice.getColor()).thenReturn(DiceColor.VIOLET);
        when(mockDice.getIntensity()).thenReturn(2);


        // Player setup
        player.addExtractedSchemeCard(schemeCard);
        player.setMatch(mockMatch);
        player.setPlayerState(State.STARTTURNSTATE);
        player.setScheme(3);



        // Setting the first Dice on the Scheme Card
        player.getScheme().setDice(mockDice, 1, 4, false, false, false);

    }

    @Test (expected = ToolIllegalOperationException.class)
    public void executeDiceNotExistantException() throws ToolIllegalOperationException {
        Dice mockDice2 = mock(Dice.class);
        when(mockDice2.getIntensity()).thenReturn(1);
        when(mockDice2.getColor()).thenReturn(DiceColor.YELLOW);

        player.getMatch().getGametable().getRoundDicepool().addDice(mockDice2);

        toolRequestClass.setSelectedDiceIndex(-1);
        toolRequestClass.setNewRow1(2);
        toolRequestClass.setNewColumn1(1);

        toolCard.execute(player, toolRequestClass);
    }

    @Test
    public void executeInvalidOutOfSchemePositionException() {
        Dice mockDice2 = mock(Dice.class);
        when(mockDice2.getIntensity()).thenReturn(1);
        when(mockDice2.getColor()).thenReturn(DiceColor.YELLOW);

        player.getMatch().getGametable().getRoundDicepool().addDice(mockDice2);

        toolRequestClass.setSelectedDiceIndex(-1);
        toolRequestClass.setNewRow1(2);
        toolRequestClass.setNewColumn1(5);

        try {
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(1, player.getMatch().getGametable().getRoundDicepool().getDicePoolSize());
            return;
        }

        // We don't want to end here
        assertFalse(0 == 0);

    }


    @Test
    public void executePlaceNearAnotherDiceException() {
        Dice mockDice2 = mock(Dice.class);
        when(mockDice2.getIntensity()).thenReturn(1);
        when(mockDice2.getColor()).thenReturn(DiceColor.YELLOW);

        player.getMatch().getGametable().getRoundDicepool().addDice(mockDice2);

        toolRequestClass.setSelectedDiceIndex(-1);
        toolRequestClass.setNewRow1(1);
        toolRequestClass.setNewColumn1(3);

        try {
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(1, player.getMatch().getGametable().getRoundDicepool().getDicePoolSize());
            return;
        }

        // We don't want to end here
        assertFalse(0 == 0);

    }


    @Test
    public void executeColorRestrictionException() {
        Dice mockDice2 = mock(Dice.class);
        when(mockDice2.getIntensity()).thenReturn(1);
        when(mockDice2.getColor()).thenReturn(DiceColor.YELLOW);

        player.getMatch().getGametable().getRoundDicepool().addDice(mockDice2);

        toolRequestClass.setSelectedDiceIndex(0);
        toolRequestClass.setNewRow1(1);
        toolRequestClass.setNewColumn1(0);

        try {
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(1, player.getMatch().getGametable().getRoundDicepool().getDicePoolSize());
            return;
        }

        // We don't want to end here
        assertFalse(0 == 0);
    }

    @Test
    public void exectuteOK() throws ToolIllegalOperationException {
        Dice mockDice2 = mock(Dice.class);
        when(mockDice2.getIntensity()).thenReturn(1);
        when(mockDice2.getColor()).thenReturn(DiceColor.YELLOW);

        player.getMatch().getGametable().getRoundDicepool().addDice(mockDice2);

        toolRequestClass.setSelectedDiceIndex(0);
        toolRequestClass.setNewRow1(2);
        toolRequestClass.setNewColumn1(1);

        toolCard.execute(player, toolRequestClass);

        assertEquals(0, player.getMatch().getGametable().getRoundDicepool().getDicePoolSize());
    }


    // Getters

    @Test
    public void getID() {
        assertEquals(9, toolCard.getID());
    }


}