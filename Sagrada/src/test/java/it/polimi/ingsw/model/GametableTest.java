package it.polimi.ingsw.model;


import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.ToolCard.ToolCardsDeck;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
    Untested methods:
    This class contains many getter methods for getting informations about public, private, ecc.. cards. These methods
    will be tested in the respective classes.
    Same is for useaToolCard
 */

public class GametableTest {

    private Gametable gametable;
    private Player player1;
    private Player player2;
    private SchemeCard schemeCard1;
    private SchemeCard schemeCard2;
    private Match mockMatch;
    private Dice mockDice;

    @Before
    public void before() throws IOException, MapConstrainReadingException, CardIdNotAllowedException {
        // Tested class
        gametable = new Gametable(2);

        // Useful classes
        mockMatch = mock(Match.class);
        player1 = new Player();
        player2 = new Player();
        schemeCard1 = new SchemeCard(3);
        schemeCard2 = new SchemeCard(5);
        mockDice = mock(Dice.class);

        doNothing().when(mockMatch).countDown();

        // Scheme Card setup
        schemeCard1.setTwinCard(new SchemeCard(4));
        schemeCard2.setTwinCard(new SchemeCard(6));


        // Player setup
        player1.setMatch(mockMatch);
        player2.setMatch(mockMatch);

        player1.addExtractedSchemeCard(schemeCard1);
        player1.setScheme(3);

        player2.addExtractedSchemeCard(schemeCard2);
        player2.setScheme(5);

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
    }


    @Test
    public void setUpRoundTest() {
        assertEquals(90, gametable.getDicepool().getDicePoolSize());
        gametable.setupRound();
        assertNotNull(gametable.getRoundDicepool());

        // Case with 2 Players
        assertEquals(5, gametable.getRoundDicepool().getDicePoolSize());
        assertEquals(85, gametable.getDicepool().getDicePoolSize());
    }


    @Test
    public void getRoundTrackTest() {
        assertEquals(RoundTrack.class, gametable.getRoundTrack().getClass());
    }


    @Test
    public void endRoundTest() throws RoundTrackException, DicepoolIndexException {
        gametable.setupRound();
        gametable.endRound(1);

        assertEquals(5, gametable.getRoundTrack().getroundTrackDices(1).getDicePoolSize());
        assertEquals(0, gametable.getRoundDicepool().getDicePoolSize());
    }


    @Test
    public void useToolCardTest() throws Exception {
        ToolRequestClass toolRequest = new ToolRequestClass();
        toolRequest.setToolCardID(1000);

        player1.setPlayerState(State.STARTTURNSTATE);

        try {
            gametable.useaToolCard(toolRequest, player1);
        } catch (WrongToolCardIDException e) {
            return;
        }

        // We don't want to end here
        assertFalse(0 == 0);
    }

    @Test
    public void calculatePointsTest() {
        List<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        gametable.calculatePointsforAllPlayers(players);

        assertEquals(-20, player1.getPoints());
        assertEquals(-20, player2.getPoints());
    }


    @Test
    public void getToolCardsTest() {
        List toolCards = gametable.getToolCards();
        assertEquals(3, toolCards.size());
    }


}
