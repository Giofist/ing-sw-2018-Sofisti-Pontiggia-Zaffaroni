package it.polimi.ingsw.model.PlayerPackage;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

    private Player player;
    private GoalCard mockPrivateGoalCard;
    private User mockUser;
    private Match mockMatch;
    private PlayerState mockPlayerState;
    private Turn mockTurn;
    private Dice mockDice;
    private Gametable mockGameTable;
    private SchemeCard mockSchemeCard;

    @Before
    public void before() throws IOException, MapConstrainReadingException {
        player = new Player();

        mockPrivateGoalCard = mock(GoalCard.class);
        mockUser = mock(User.class);
        mockMatch = mock(Match.class);
        mockPlayerState = mock(PlayerState.class);
        mockTurn = mock(Turn.class);
        mockDice = mock(Dice.class);
        mockGameTable = mock(Gametable.class);
        mockSchemeCard = new SchemeCard(2);

        when(mockPrivateGoalCard.getID()).thenReturn(3);
        when(mockUser.getName()).thenReturn("Xenomit");
        when(mockMatch.getName()).thenReturn("Partita1");
        when(mockMatch.getGametable()).thenReturn(mockGameTable);
        when(mockDice.getColor()).thenReturn(DiceColor.GREEN);
        when(mockDice.getIntensity()).thenReturn(4);


        mockSchemeCard = new SchemeCard(2);
        mockSchemeCard.setTwinCard(new SchemeCard(3));
        player.addExtractedSchemeCard(mockSchemeCard);
        player.addExtractedSchemeCard(mockSchemeCard);
    }

    @Test
    public void getAndSetToken() {
        assertEquals(0, player.getToken());
        player.setToken(5);
        assertEquals(5, player.getToken());
    }


    @Test
    public void payforToolEnoughPoints() throws NotEnoughSegnaliniException {
        player.setToken(5);
        player.payforToolAction(5);
        assertEquals(0, player.getToken());
    }

    @Test (expected = NotEnoughSegnaliniException.class)
    public void payforToolNotEnoughPoints() throws NotEnoughSegnaliniException {
        player.setToken(5);
        player.payforToolAction(6);
    }

    @Test
    public void setAndGetPrivateGoalCard() {
        assertNull(player.getPrivateGoalCard());
        player.setPrivateGoalCard(mockPrivateGoalCard);
        assertNotNull(player.getPrivateGoalCard());
        assertEquals(3, player.getPrivateGoalCard().getID());
    }


    @Test (expected = SchemeCardNotExistantException.class)
    public void setAndGetSchemeException() throws SchemeCardNotExistantException {
        player.getScheme();
    }


    @Test (expected = CardIdNotAllowedException.class)
    public void setSchemeException() throws CardIdNotAllowedException {
          player.setScheme(1);
    }


    @Test
    public void getGametable() {
        player.setMatch(mockMatch);
        when(mockMatch.getGametable()).thenReturn(null);
        assertNull(player.getGametable());

        when(mockMatch.getGametable()).thenReturn(mockGameTable);
        assertEquals(player.getGametable(), mockGameTable);
    }

    @Test
    public void addPoints() {
        assertEquals(0, player.getPoints());
        player.addPoints(100);
        assertEquals(100, player.getPoints());
        player.addPoints(-200);
        assertEquals(-100, player.getPoints());
    }





    @Test
    public void setAndGetMatch() {
        assertNull(player.getMatch());
        player.setMatch(mockMatch);
        assertNotNull(player.getMatch());
        assertEquals("Partita1", player.getMatch().getName());
    }


    @Test
    public void addAndGetExtractedSchemeCards() throws CardIdNotAllowedException {
        doNothing().when(mockMatch).countDown();
        player.setMatch(mockMatch);

        player.setScheme(2);
    }


    @Test
    public void setAndGetDiceForToolCardUse() throws DiceNotExistantException {
        player.setDiceforToolCardUse(mockDice);
        assertEquals(DiceColor.GREEN, player.getdiceforToolCardUse().getColor());
        assertEquals(4, player.getdiceforToolCardUse().getIntensity());
    }


    @Test (expected = DiceNotExistantException.class)
    public void setAndGetDiceForToolCardUseException() throws DiceNotExistantException {
        doNothing().when(mockMatch).countDown();
        player.setMatch(mockMatch);

        player.setDiceforToolCardUse(mockDice);
        player.removediceforToolCardUse();
        player.getdiceforToolCardUse();
    }


    @Test
    public void setAndGetMustPassTurn() {
        assertFalse(player.mustpassTurn());
        player.setMustpassTurn(true);
        assertTrue(player.mustpassTurn());
    }



    @Test
    public void setAndGetTurn() {
        assertNull(player.getTurn());
        player.setTurn(mockTurn);
        assertNotNull(player.getTurn());
        assertTrue(player.getTurn() == mockTurn);
    }

    @Test
    public void compareTo() {
        Player player2 = new Player();
        player2.addPoints(2);

        assertEquals(-2, player.compareTo(player2));
        assertEquals(2, player2.compareTo(player));
    }

}