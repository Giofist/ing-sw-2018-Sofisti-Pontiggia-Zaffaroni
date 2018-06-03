/*
package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.CardIdNotAllowedException;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.Turn.NotYourTurnState;
import it.polimi.ingsw.model.Turn.PlayerState;
import it.polimi.ingsw.model.Turn.Turn;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

    private Player player;
    private GoalCard mockPrivateGoalCard;
    private User mockUser;
    private Match mockMatch;
    private NotYourTurnState mockPlayerState;
    private Turn mockTurn;
    private Dice mockDice;
    private Gametable mockGameTable;
    private SchemeCard mockSchemeCard;

    @Before
    public void before() {
        player = new Player();

        mockPrivateGoalCard = mock(GoalCard.class);
        mockUser = mock(User.class);
        mockMatch = mock(Match.class);
        mockPlayerState = mock(NotYourTurnState.class);
        mockTurn = mock(Turn.class);
        mockDice = mock(Dice.class);
        mockGameTable = mock(Gametable.class);
        mockSchemeCard = mock(SchemeCard.class);

        when(mockPrivateGoalCard.getID()).thenReturn(3);
        when(mockUser.getName()).thenReturn("Xenomit");
        when(mockMatch.getName()).thenReturn("Partita1");
        when(mockMatch.getGametable()).thenReturn(mockGameTable);
    }

    @Test
    public void getAndSetSegnalini_favore() {
        assertEquals(0, player.getSegnalini_favore());
        player.setSegnalini_favore(5);
        assertEquals(5, player.getSegnalini_favore());
    }


    @Test
    public void payforToolEnoughPoints() throws NotEnoughSegnaliniException {
        player.setSegnalini_favore(5);
        player.payforToolAction(5);
        assertEquals(0, player.getSegnalini_favore());
    }

    @Test (expected = NotEnoughSegnaliniException.class)
    public void payforToolNotEnoughPoints() throws NotEnoughSegnaliniException {
        player.setSegnalini_favore(5);
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
        player.setUser(mockUser);
        player.getScheme();
    }


    @Test (expected = CardIdNotAllowedException.class)
    public void setAndGetSchemeException2() throws CardIdNotAllowedException {
        SchemeCard mockTwinCard = mock(SchemeCard.class);

        player.addExtractedSchemeCard(mockSchemeCard);
        when(mockSchemeCard.getID()).thenReturn(2);
        when(mockSchemeCard.getTwinCard()).thenReturn(mockTwinCard);
        when(mockTwinCard.getID()).thenReturn(3);
        player.setScheme(100);
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
    public void setAndGetPoints() {
        assertEquals(0, player.getPoints());
        player.setPoints(40);
        assertEquals(40, player.getPoints());
    }


    @Test
    public void setAndGetUser() {
        assertNull(player.getAssociatedUser());
        player.setUser(mockUser);
        assertEquals("Xenomit", player.getAssociatedUser().getName());
    }


    @Test
    public void setAndGetMatch() {
        assertNull(player.getMatch());
        player.setMatch(mockMatch);
        assertNotNull(player.getMatch());
        assertEquals("Partita1", player.getMatch().getName());
    }



    @Test
    public void addAndGetExtractedSchemeCards() {
        SchemeCard mockTwinSchemeCard = mock(SchemeCard.class);
        when(mockSchemeCard.displayScheme()).thenReturn("YB__1G_5_43_R_G2__BY");
        when(mockSchemeCard.getTwinCard()).thenReturn(mockTwinSchemeCard);
        when(mockTwinSchemeCard.displayScheme()).thenReturn("YB__1G_5_43_R_G3__BY");

        assertEquals("\n", player.getExtractedSchemeCards());
        player.addExtractedSchemeCard(mockSchemeCard);
        assertNotEquals("\n", player.getExtractedSchemeCards());
    }

    @Test
    public void setAndGetDiceForDiluentePerPastaSalda() throws DiceNotExistantException {
        when(mockDice.getColor()).thenReturn(DiceColor.GREEN);
        when(mockDice.getIntensity()).thenReturn(4);

        player.setDiceforDiluenteperPastaSalda(mockDice);
        assertEquals(DiceColor.GREEN, player.getdiceforDiluenteperPastaSalda().getColor());
        assertEquals(4, player.getdiceforDiluenteperPastaSalda().getIntensity());
    }

    @Test (expected = DiceNotExistantException.class)
    public void setAndGetDiceForDiluentePerPastaSaldaException() throws DiceNotExistantException {
        player.getdiceforDiluenteperPastaSalda();
    }

    @Test (expected = DiceNotExistantException.class)
    public void removeDiceForDiluenteperPastaSalda() throws DiceNotExistantException {
        player.setDiceforDiluenteperPastaSalda(mockDice);
        player.removediceforDiluenteperPastaSalda();
        player.getdiceforDiluenteperPastaSalda();
    }

    @Test
    public void setAndGetMustSetDice() {
        assertFalse(player.getMustsetDice());
        player.setMustsetdice(true);
        assertTrue(player.getMustsetDice());
    }


    @Test
    public void setAndGetColorConstrainForTaglierinaManuale() {
        assertNull(player.getColorConstrainForTaglierinaManuale());
        player.setColorConstrainForTaglierinaManuale(DiceColor.GREEN);
        assertEquals(DiceColor.GREEN, player.getColorConstrainForTaglierinaManuale());
    }


    @Test
    public void setAndGetMustPassTurn() {
        assertFalse(player.mustpassTurn());
        player.setMustpassTurn(true);
        assertTrue(player.mustpassTurn());
    }


    @Test
    public void setAndGetPlayerState() {
        assertNull(player.getPlayerState());
        player.setPlayerState(mockPlayerState);
        assertTrue(mockPlayerState == player.getPlayerState());
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
        player2.setPoints(2);

        assertEquals(-2, player.compareTo(player2));
        assertEquals(2, player2.compareTo(player));
    }

    @Test
    public void toStringTest() {
        player.setUser(mockUser);
        assertEquals("Xenomit 0\n", player.toString());
    }
}

*/