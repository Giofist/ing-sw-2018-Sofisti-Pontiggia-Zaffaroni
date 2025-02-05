package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// COMPLETATO CON CORREZIONE IMPLEMENTAZIONE

public class TamponeDiamantatoTest {

    private TamponeDiamantato toolCard;
    private ToolRequestClass toolRequestClass;
    private Player player;
    private Match mockMatch;
    private Turn mockTurn;
    private DicePool roundDicePool;
    private Gametable mockGameTable;

    @Before
    public void before() throws RemoteException {
        toolCard = new TamponeDiamantato();
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
        player.setPlayerState(State.STARTTURNSTATE);

        when(mockMatch.getGametable()).thenReturn(mockGameTable);
        when(mockGameTable.getRoundDicepool()).thenReturn(roundDicePool);
    }

    // This method will test when the specified index is out of range
    @Test (expected = ToolIllegalOperationException.class)
    public void executeIndexOutOfRangeException() throws ToolIllegalOperationException {
        toolRequestClass.setSelectedDiceIndex(-1);
        toolCard.execute(player, toolRequestClass);
        assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
    }


    @Test
    public void executeOK() throws DicepoolIndexException, ToolIllegalOperationException {
        toolRequestClass.setSelectedDiceIndex(0);

        int initialIntensity = player.getMatch().getGametable().getRoundDicepool().getDice(0).getIntensity();
        toolCard.execute(player, toolRequestClass);

        switch (initialIntensity) {
            case 1:
                assertEquals(6, player.getMatch().getGametable().getRoundDicepool().getDice(0).getIntensity());
                break;
            case 2:
                assertEquals(5, player.getMatch().getGametable().getRoundDicepool().getDice(0).getIntensity());
                break;
            case 3:
                assertEquals(4, player.getMatch().getGametable().getRoundDicepool().getDice(0).getIntensity());
                break;
            case 4:
                assertEquals(3, player.getMatch().getGametable().getRoundDicepool().getDice(0).getIntensity());
                break;
            case 5:
                assertEquals(2, player.getMatch().getGametable().getRoundDicepool().getDice(0).getIntensity());
                break;
            case 6:
                assertEquals(1, player.getMatch().getGametable().getRoundDicepool().getDice(0).getIntensity());
                break;
            default:
                assertFalse(0==0);
                break;
        }

        assertEquals(State.HASUSEDATOOLCARDACTIONSTATE, player.getPlayerState().getState());
    }


    // Minor tests
    @Test
    public void getIDTest() {
        assertEquals(10, toolCard.getID());
    }


}