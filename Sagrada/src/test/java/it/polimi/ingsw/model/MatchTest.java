package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Exceptions.GameNotExistantException;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.IsAlreadyActiveException;
import it.polimi.ingsw.model.Exceptions.LoginException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Matches;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MatchTest {

    private Match match;
    private Player mockPlayer1;
    private Player mockPlayer2;
    private Player mockPlayer3;
    private Observer mockObserver;

    @Before
    public void before() {
        mockPlayer1 = new Player();
        mockPlayer2 = new Player();
        mockPlayer3 = new Player();

        mockObserver = mock(Observer.class);


        match = new Match(mockPlayer1, "Match1");
    }

    @Test
    public void gettersSettersTest() {
        assertEquals(1, match.getNumberOfPlayers());
        assertEquals("Match1", match.getName());
        assertNull(match.getGametable());
        assertFalse(match.isStarted());
        match.setStarted(true);
        assertTrue(match.isStarted());
    }

    @Test
    public void joinMatchTest() {
        match.join(mockPlayer2);
        try {
            match.join(mockPlayer3);
        } catch (IllegalMonitorStateException e){
        }

        assertTrue(match.getIsReadyToStart());
        assertEquals(3, match.getNumberOfPlayers());
    }

    @Test
    public void getPlayersTest() {
        match.join(mockPlayer2);
        List<Player> players;

        players = match.getallPlayers();
        assertEquals(2, players.size());

        players = match.getallPlayersbutnotme(mockPlayer2);
        assertEquals(1, players.size());
    }

    @Test
    public void leaveMatchTest() throws HomonymyException, GameNotExistantException {
        MatchesList.singleton().createMatch(mockPlayer1, "Match1");
        assertEquals(1, MatchesList.singleton().getMatchesListSize());
        match = MatchesList.singleton().getMatch("Match1");
        match.leavethematch(mockPlayer1);
        assertEquals(0, MatchesList.singleton().getMatchesListSize());
    }


    @Test
    public void forceEndMatchTest() throws HomonymyException, LoginException, IsAlreadyActiveException {
        mockPlayer1.setName("Player1");
        UsersList.Singleton().clearUserList();
        UsersList.Singleton().register("Player1", "Player1");
        UsersList.Singleton().check("Player1", "Player1", mockObserver);
        match.forceendmatch();
        assertEquals(State.FORCEENDMATCH , mockPlayer1.getPlayerState().getState());
    }

    @Test
    public void runTest() throws InterruptedException {
        mockPlayer2.setMatch(match);
        match.join(mockPlayer2);
        match.setIsReadToStart(true);

        Thread matchThread = new Thread(match);

        matchThread.start();
        Thread.sleep(2000);
        matchThread.interrupt();

        assertNotNull(match.getGametable());
        assertNotNull(match.getGametable());
        assertNotNull(mockPlayer1.getPrivateGoalCard());
        assertNotNull(mockPlayer2.getPrivateGoalCard());
        assertEquals(State.MUSTSETSCHEMECARD, mockPlayer1.getPlayerState().getState());
        assertEquals(State.MUSTSETSCHEMECARD, mockPlayer2.getPlayerState().getState());
    }

}