package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GametableTest {

    private Gametable gametable;

    @Before
    public void before() throws IOException {
        gametable = new Gametable();
    }

    @Test
    public void constructorTest() throws PrivateGoalCardException, IOException, MapConstrainReadingException {
        assertNotNull(gametable.getToolCardsDeck());
        assertNotNull(gametable.getDicepool());
        assertNotNull(gametable.getRoundDicepool());
        assertNotNull(gametable.getPrivateGoalCard());
        assertNotNull(gametable.getPublicGoalCardDeck());
        assertNotNull(gametable.getSchemeCard());
    }
}