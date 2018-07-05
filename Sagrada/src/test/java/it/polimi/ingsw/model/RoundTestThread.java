package it.polimi.ingsw.model;

import java.util.LinkedList;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoundTestThread implements Runnable {

    private Round round;
    private Player mockPlayer1;
    private Player mockPlayer2;
    private Match mockMatch;
    private Gametable mockGametable;
    private LinkedList<Player> players;

    public  void run() {
        // Mock classes
        mockPlayer1 = new Player();
        mockPlayer2 = new Player();
        mockMatch = mock(Match.class);
        mockGametable = mock(Gametable.class);

        players = new LinkedList<>();
        players.add(mockPlayer1);
        players.add(mockPlayer2);


        // Mock behaviour
        when(mockMatch.getGametable()).thenReturn(mockGametable);
        when(mockMatch.getNumberOfPlayers()).thenReturn(2);
        doNothing().when(mockGametable).setupRound();


        round = new Round( players);
        round.run();
    }
}
