package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// CANNOT TEST WAITING METHOD

public class RoundTest {

    private Round round;
    private Player mockPlayer1;
    private Player mockPlayer2;
    private Match mockMatch;
    private LinkedList<Player> players;
    private Gametable mockGametable;

    @Before
    public void before() {
        // Mock classes
        mockPlayer1 = mock(Player.class);
        mockPlayer2 = mock(Player.class);
        mockMatch = mock(Match.class);
        mockGametable = mock(Gametable.class);

        players = new LinkedList<>();
        players.add(mockPlayer1);
        players.add(mockPlayer2);


        // Mock behaviour
        when(mockMatch.getGametable()).thenReturn(mockGametable);
        when(mockMatch.getNumberOfPlayers()).thenReturn(2);
        when(mockPlayer1.getMatch()).thenReturn(mockMatch);
        when(mockPlayer2.getMatch()).thenReturn(mockMatch);
        doNothing().when(mockGametable).setupRound();

    }


    @Test
    public void runTest() throws InterruptedException {
        Thread testThread = new Thread(new RoundTestThread());
        testThread.start();

        Thread.sleep(2000);
        testThread.interrupt();
    }

    // Getters
    @Test
    public void getMatchTest() {
        round = new Round(1, players, mockMatch);
        assertEquals(mockMatch, round.getMatch());
    }

    @Test
    public void getNumRoundTest() {
        round = new Round(1, players, mockMatch);
        assertEquals(1, round.getNum_round());
    }

    @Test
    public void getPlayersTest() {
        round = new Round(1, players, mockMatch);
        assertEquals(players, round.getPlayers());
    }

}