package it.polimi.ingsw.model;


import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/*
    Untested methods:
    This class contains many getter methods for getting informations about public, private, ecc.. cards. These methods
    will be tested in the respective classes.
    Same is for useaToolCard
 */

public class GametableTest {

    private Gametable gametable;

    @Before
    public void before() throws IOException {
        gametable = new Gametable(3);
    }

    @Test
    public void constructorTest() throws PrivateGoalCardException, IOException, MapConstrainReadingException {
        assertNotNull(gametable.getDicepool());
        assertEquals(90, gametable.getDicepool().getDicePoolSize());

        assertNull(gametable.getRoundDicepool());

        assertNotNull(gametable.getToolCardsDeck());
        assertNotNull(gametable.getPrivateGoalCard());
        assertNotNull(gametable.getPublicGoalCardDeck());
        assertNotNull(gametable.getSchemeCard());
        assertNotNull(gametable.getRoundTrack());
        assertEquals(3, gametable.getNumberOfPlayers());
    }


    @Test
    public void setUpRoundTest() {
        assertEquals(90, gametable.getDicepool().getDicePoolSize());
        gametable.setupRound();
        assertNotNull(gametable.getRoundDicepool());

        // Case with 3 Players
        assertEquals(7, gametable.getRoundDicepool().getDicePoolSize());
        assertEquals(83, gametable.getDicepool().getDicePoolSize());
    }


    @Test
    public void endRoundTest() {

    }
}
