package it.polimi.ingsw.model;

import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/*
    Fix wait
 */


public class TurnTest {

    private Turn turn;
    private Round round;
    private Player player1;
    private Player player2;
    private User user1;
    private User user2;
    private Match mockMatch;

    @Before
    public void before() {
        // Useful classes
        mockMatch = mock(Match.class);
        user1 = new User("user1", "pass1");
        user2 = new User("user2", "pass2");
        player1 = new Player();
        player2 = new Player();

        // Player setup
        player1.setMatch(mockMatch);
        player2.setMatch(mockMatch);
        

        // Player list setup
        List<Player> playerList = new LinkedList<>();
        playerList.add(player1);
        playerList.add(player2);

        // Mock behaviours
        when(mockMatch.getNumberOfPlayers()).thenReturn(2);
        when(mockMatch.getallPlayers()).thenReturn(playerList);
        when(mockMatch.getallPlayersbutnotme(player1)).thenReturn(new LinkedList<Player>(){{ add(player2); }});
        when(mockMatch.getallPlayersbutnotme(player2)).thenReturn(new LinkedList<Player>(){{ add(player1); }});

        round = new Round(1, (LinkedList<Player>) playerList, mockMatch);

        // Tested class
        turn = new Turn(player1, round, 1);
    }



    @Test
    public void runTest() throws InterruptedException {
        Thread testThread = new Thread(new Turn(player1, round, 1));
        testThread.start();

        Thread.sleep(2000);

        testThread.interrupt();

        assertEquals(1, player1.getTurn().getTurnID());
        assertEquals(1, player2.getTurn().getTurnID());

        assertEquals(State.STARTTURNSTATE, player1.getPlayerState().getState());
        assertEquals(State.NOTYOURTURNSTATE, player2.getPlayerState().getState());

    }


    @Test
    public void getTurnIdTest() {
        assertEquals(1, turn.getTurnID());
    }

    @Test
    public void countDownTest() {
        assertEquals(2, turn.getCountDownValue());
        turn.countDown();
        assertEquals(1, turn.getCountDownValue());
    }

    @Test
    public void countDownAllTest() {
        assertEquals(2, turn.getCountDownValue());
        turn.countDownAll();
        assertEquals(0, turn.getCountDownValue());
    }
}