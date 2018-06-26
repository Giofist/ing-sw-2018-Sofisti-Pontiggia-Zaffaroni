package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// NON PASSA IL TEST PROBABILE ERRORE

public class TaglierinaCircolareTest {

    private TaglierinaCircolare toolCard;
    private ToolRequestClass toolRequestClass;
    private Player player;
    private Match mockMatch;
    private Turn mockTurn;
    private DicePool roundDicePool;
    private Gametable mockGameTable;
    private RoundTrack roundTrack;

    @Before
    public void before() throws RemoteException, RoundTrackException, DicepoolIndexException {
        toolCard = new TaglierinaCircolare();
        toolRequestClass = new ToolRequestClass();

        roundDicePool = new DicePool();
        roundDicePool.addDice(new Dice(DiceColor.RED));
        roundDicePool.addDice(new Dice(DiceColor.YELLOW));

        roundTrack = new RoundTrack();
        List<Dice> roundTrackDices = Arrays.asList(new Dice(DiceColor.RED), new Dice(DiceColor.YELLOW));
        roundTrack.setRoundTrackDices(1, roundTrackDices);
        roundTrack.getroundTrackDices(1).getDice(0).setIntensity(1);
        roundTrack.getroundTrackDices(1).getDice(1).setIntensity(2);

        player = new Player();
        mockMatch = mock(Match.class);
        mockGameTable = mock(Gametable.class);
        mockTurn = mock(Turn.class);
        player.setMatch(mockMatch);
        player.setTurn(mockTurn);
        player.setPlayerState(State.STARTTURNSTATE);


        when(mockMatch.getGametable()).thenReturn(mockGameTable);
        when(mockGameTable.getRoundDicepool()).thenReturn(roundDicePool);
        when(mockGameTable.getRoundTrack()).thenReturn(roundTrack);
    }


    @Test
    public void executeOK() throws DicepoolIndexException, ToolIllegalOperationException, RoundTrackException {

        player.getMatch().getGametable().getRoundDicepool().getDice(0).setIntensity(3);
        player.getMatch().getGametable().getRoundDicepool().getDice(0).setIntensity(4);

        toolRequestClass.setSelectedDiceIndex(0);
        toolRequestClass.setRoundWhereThediceis(1);
        toolRequestClass.setSelectedDiceIndex(0);


        toolCard.execute(player, toolRequestClass);

        assertEquals(1, player.getMatch().getGametable().getRoundDicepool().getDice(0).getIntensity());
        assertEquals(3, player.getMatch().getGametable().getRoundTrack().getroundTrackDices(1).getDice(0).getIntensity());
    }


    // Getters

    @Test
    public void getID() {
        assertEquals(5, toolCard.getID());
    }



}