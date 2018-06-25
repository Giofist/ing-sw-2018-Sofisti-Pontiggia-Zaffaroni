package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.CardIdNotAllowedException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.rmi.RemoteException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// COMPLETE, ERROR IN SETTER

public class TenagliaRotelleTest {

    private TenagliaRotelle toolCard;
    private Player player;
    private Dice mockDice;
    private SchemeCard schemeCard;
    private ToolRequestClass toolRequestClass;
    private Match mockMatch;
    private Gametable mockGametable;
    private DicePool roundDicePool;
    private Turn mockTurn;

    @Before
    public void before() throws IOException, MapConstrainReadingException, CardIdNotAllowedException, SchemeCardNotExistantException, OutOfMatrixException, TileConstrainException {
        // Tested class
        toolCard = new TenagliaRotelle();

        // Required classes for game interaction
        player = new Player();
        mockDice = mock(Dice.class);
        mockMatch = mock(Match.class);
        mockGametable = mock(Gametable.class);
        schemeCard = new SchemeCard(3);
        toolRequestClass = new ToolRequestClass();
        roundDicePool = new DicePool();
        mockTurn = mock(Turn.class);

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
        player.setTurn(mockTurn);
    }


    @Test
    public void executeNotFirstTurnException() throws ToolIllegalOperationException {
        player.getMatch().getGametable().getRoundDicepool().addDice(mockDice);
        when(mockTurn.getTurnID()).thenReturn(2);

        toolRequestClass.setSelectedRoundDicepoolDiceIndex(0);
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(0);

        try {
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(1, roundDicePool.getDicePoolSize());
            return;
        }

        assertFalse(0 == 0);
    }


    @Test
    public void executeBeginOfTheTurnException() throws ToolIllegalOperationException {
        player.getMatch().getGametable().getRoundDicepool().addDice(mockDice);
        when(mockTurn.getTurnID()).thenReturn(1);

        toolRequestClass.setSelectedRoundDicepoolDiceIndex(0);
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(0);

        try {
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(1, roundDicePool.getDicePoolSize());
            return;
        }

        assertFalse(0 == 0);
    }


    @Test
    public void executeNewInvalidPosition() {
        player.getMatch().getGametable().getRoundDicepool().addDice(mockDice);
        when(mockTurn.getTurnID()).thenReturn(1);

        toolRequestClass.setSelectedRoundDicepoolDiceIndex(0);
        toolRequestClass.setNewRow1(-1);
        toolRequestClass.setNewColumn1(0);

        try {
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(1, roundDicePool.getDicePoolSize());
            return;
        }

        assertFalse(0 == 0);
    }


    @Test
    public void executeConstrainInNewPosition() throws SchemeCardNotExistantException, OutOfMatrixException {
        player.getMatch().getGametable().getRoundDicepool().addDice(mockDice);
        when(mockTurn.getTurnID()).thenReturn(1);

        toolRequestClass.setSelectedRoundDicepoolDiceIndex(0);
        toolRequestClass.setNewRow1(1);
        toolRequestClass.setNewColumn1(0);

        assertFalse(player.getScheme().IsTileOccupied(1, 0));

        try {
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(1, roundDicePool.getDicePoolSize());
            assertFalse(player.getScheme().IsTileOccupied(1, 0));
            return;
        }

        assertFalse(0 == 0);
    }


    @Test
    public void executeOK() throws ToolIllegalOperationException, RemoteException {
        player.getMatch().getGametable().getRoundDicepool().addDice(mockDice);
        player.setPlayerState(State.HASSETADICESTATE);
        when(mockTurn.getTurnID()).thenReturn(1);

        toolRequestClass.setSelectedRoundDicepoolDiceIndex(0);
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(0);

        toolCard.execute(player, toolRequestClass);
        assertEquals(0, roundDicePool.getDicePoolSize());

    }

    // Getters tests
    @Test
    public void getIDTest() {
        assertEquals(8, toolCard.getID());
    }

}