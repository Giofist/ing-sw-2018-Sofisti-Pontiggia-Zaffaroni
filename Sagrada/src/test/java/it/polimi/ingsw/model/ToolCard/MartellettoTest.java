package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


// COMPLETE

public class MartellettoTest {

    private Martelletto toolCard;
    private ToolRequestClass toolRequestClass;
    private Player player;
    private Match mockMatch;
    private Turn mockTurn;
    private DicePool roundDicePool;
    private Gametable mockGameTable;

    @Before
    public void before() {
        toolCard = new Martelletto();
        toolRequestClass = new ToolRequestClass();

        roundDicePool = new DicePool();
        roundDicePool.addDice(new Dice(DiceColor.RED));
        roundDicePool.addDice(new Dice(DiceColor.YELLOW));

        player = new Player();
        mockMatch = mock(Match.class);
        mockGameTable = mock(Gametable.class);
        mockTurn = mock(Turn.class);
        player.setMatch(mockMatch);
        player.setTurn(mockTurn);

        when(mockMatch.getGametable()).thenReturn(mockGameTable);
        when(mockGameTable.getRoundDicepool()).thenReturn(roundDicePool);
    }

    // This tool card can only be used during the second turn in a round
    @Test (expected = ToolIllegalOperationException.class)
    public void executeNotInSecondRoundException() throws ToolIllegalOperationException {
        when(mockTurn.getTurnID()).thenReturn(1);
        toolCard.execute(player, toolRequestClass);
    }

    // This Tool Card cannot be used after placing a Dice
    @Test (expected = ToolIllegalOperationException.class)
    public void executeAfterSettingaDiceException() throws ToolIllegalOperationException, RemoteException {
        when(mockTurn.getTurnID()).thenReturn(2);
        player.setPlayerState(State.HASSETADICESTATE);
        toolCard.execute(player, toolRequestClass);
    }


    // This method should succed when all the requirements are satisfied
    @Test
    public void executeOK() throws ToolIllegalOperationException, RemoteException, EmpyDicepoolException {
        when(mockTurn.getTurnID()).thenReturn(2);
        player.setPlayerState(State.STARTTURNSTATE);

        toolCard.execute(player, toolRequestClass);

        assertEquals(State.HASUSEDATOOLCARDACTIONSTATE, player.getPlayerState().getState());
    }


    // Minor tests
    @Test
    public void getIDTest() {
        assertEquals(7, toolCard.getID());
    }

    @Test
    public void getCardTitleTest() {
        assertEquals("Martelletto", toolCard.getCardTitle());
    }

    @Test
    public void getCardDescriptionTest() {
        assertEquals("Tira nuovamente tutti i dadi della riserva.\n" +
                "Questa carta può essere usata solo durante il tuo secondo turno, prima di scegliere il secondo dado.", toolCard.getDescription());
    }
}