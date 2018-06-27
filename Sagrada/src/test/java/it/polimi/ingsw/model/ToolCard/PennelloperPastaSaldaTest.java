package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.CardIdNotAllowedException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.rmi.RemoteException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// COMPLETE PART2

public class PennelloperPastaSaldaTest {

    private PennelloperPastaSalda toolCard;
    private ToolRequestClass toolRequestClass;
    private Player player;
    private Match mockMatch;
    private Turn mockTurn;
    private DicePool roundDicePool;
    private Gametable mockGameTable;
    private SchemeCard schemeCard;


    @Before
    public void before() throws IOException, MapConstrainReadingException, CardIdNotAllowedException {
        // Tested Class
        toolCard = new PennelloperPastaSalda();

        // Required classes for game interaction
        player = new Player();
        toolRequestClass = new ToolRequestClass();
        roundDicePool = new DicePool();
        mockMatch = mock(Match.class);
        mockGameTable = mock(Gametable.class);
        mockTurn = mock(Turn.class);

        schemeCard = new SchemeCard(3);

        // Player setup
        player.addExtractedSchemeCard(schemeCard);
        player.setMatch(mockMatch);
        player.setTurn(mockTurn);
        player.setPlayerState(State.STARTTURNSTATE);
        player.setScheme(3);

        // Scheme card setup
        schemeCard.setTwinCard(new SchemeCard(4));

        // Mock behaviour
        when(mockMatch.getGametable()).thenReturn(mockGameTable);
        when(mockGameTable.getRoundDicepool()).thenReturn(roundDicePool);
    }


    @Test
    public void executeInvalidDiceIndexException() throws ToolIllegalOperationException {
        // Prepare ToolRequestClass
        toolRequestClass.setSelectedDiceIndex(2);

        try {
            // Tested method
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        assertFalse(0 == 0);
    }


    @Test
    public void executeOK() throws ToolIllegalOperationException {
        // Prepare RoundDicePool
        roundDicePool.addDice(new Dice(DiceColor.RED));

        // Prepare ToolRequestClass
        toolRequestClass.setSelectedDiceIndex(0);

        // Tested method
        toolCard.execute(player, toolRequestClass);

        assertEquals(State.MUSTSETPENNELLOPERPASTASALDASTATE, player.getPlayerState().getState());
    }


    @Test
    public void executeAlreadySetaDiceStateException() throws ToolIllegalOperationException, RemoteException {
        // Prepare player state
        player.setPlayerState(State.HASSETADICESTATE);

        // Prepare RoundDicePool
        roundDicePool.addDice(new Dice(DiceColor.RED));

        // Prepare ToolRequestClass
        toolRequestClass.setSelectedDiceIndex(0);

        try {
            // Tested method
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(State.HASSETADICESTATE, player.getPlayerState().getState());
            return;
        }

        assertFalse(0 == 0);


    }

    // Getter
    @Test
    public void getIdTest() {
        assertEquals(6, toolCard.getID());
    }

}