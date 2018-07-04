package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.GameNotExistantException;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.MatchStartedYetException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchesListTest {

    private MatchesList matchesList;
    private Player mockPlayer1;
    private Player mockPlayer2;
    private Match mockMatch;

    @Before
    public void before() {
        // Tested class
        matchesList = MatchesList.singleton();

        // Mock classes
        mockPlayer1 = mock(Player.class);
        mockPlayer2 = mock(Player.class);
        mockMatch = mock(Match.class);

        // Mock behaviours
        doNothing().when(mockPlayer1).setMatch(mockMatch);
        doNothing().when(mockPlayer2).setMatch(mockMatch);
        doNothing().when(mockMatch).join(mockPlayer2);
    }


    @Test
    synchronized public void createMatch() throws HomonymyException, GameNotExistantException {
        matchesList.clearMatchesList();
        matchesList.createMatch(mockPlayer1, "Match1");
        matchesList.createMatch(mockPlayer1, "Match2");
        assertEquals(2, matchesList.getActiveMatches().size());
        //assertEquals("Match1", matchesList.getMatch("Match1").getName());
        //assertEquals("Match2", matchesList.getMatch("Match2").getName());
    }


    @Test
    synchronized public void createMatchOmonimyException() throws HomonymyException {
        matchesList.clearMatchesList();
        try {
            matchesList.createMatch(mockPlayer1, "Match1");
            matchesList.createMatch(mockPlayer1, "Match1");
        } catch (HomonymyException e) {
            assertEquals(1, matchesList.getActiveMatches().size());
            return;
        }

        assertFalse(0 == 0);
    }



    @Test
    synchronized public void getActiveMatchesTest() throws GameNotExistantException, HomonymyException {
        matchesList.clearMatchesList();
        matchesList.createMatch(mockPlayer1, "Match1");
        matchesList.createMatch(mockPlayer2, "Match2");
        assertEquals(2, matchesList.getActiveMatches().size());
    }



    @Test (expected = GameNotExistantException.class)
    synchronized public void joinMatchGameNotExistantExceptionTest() throws GameNotExistantException, MatchStartedYetException, HomonymyException {
        matchesList.join(mockPlayer2, "djasdoajd");
    }


    @Test
    synchronized public void joinMatchGameTest() throws GameNotExistantException, HomonymyException, MatchStartedYetException {
        matchesList.createMatch(mockPlayer1, "Match1");
        matchesList.join(mockPlayer2, "Match1");
    }
}