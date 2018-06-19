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
        mockMatch = mock(Match.class);

        user1 = new User("user1", "pass1");
        user2 = new User("user2", "pass2");
        player1 = new Player();
        player2 = new Player();

        player1.setMatch(mockMatch);
        player1.setUser(user1);

        player2.setMatch(mockMatch);
        player2.setUser(user2);

        List<Player> playerList = new LinkedList<>();
        playerList.add(player1);
        playerList.add(player2);


        when(mockMatch.getNumberOfPlayers()).thenReturn(2);
        when(mockMatch.getallPlayers()).thenReturn(playerList);
        when(mockMatch.getallPlayersbutnotme(player1)).thenReturn(new LinkedList<Player>(){{ add(player2); }});
        when(mockMatch.getallPlayersbutnotme(player2)).thenReturn(new LinkedList<Player>(){{ add(player1); }});

        round = new Round(1, (LinkedList<Player>) playerList, mockMatch);

        turn = new Turn(player1, round, 3);
    }


    /*
    @Test
    public void runTest() {
        turn.run();
        assertEquals(1, player1.getTurn());
        assertEquals(1, player2.getTurn());

        assertEquals(State.STARTTURNSTATE, player1.getPlayerState());
        assertEquals(State.NOTYOURTURNSTATE, player2.getPlayerState());

        // Fix wait
    }
    */

    @Test
    public void getTurnIdTest() {
        assertEquals(3, turn.getTurnID());
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