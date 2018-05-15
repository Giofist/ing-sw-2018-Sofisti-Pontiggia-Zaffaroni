/*
package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;
import it.polimi.ingsw.model.Exceptions.PlayerNotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

    private Player player;
    private Player otherPlayer;

    private User mockUserForPlayer;
    private User mockUserForOtherPlayer;

    @Before
    public void before() {
        this.mockUserForPlayer = mock(User.class);
        this.mockUserForOtherPlayer = mock(User.class);
        when(mockUserForOtherPlayer.getName()).thenReturn("Diego");

        this.player = new Player(mockUserForPlayer);
        this.otherPlayer = new Player(mockUserForOtherPlayer);
    }

    // Tests for very minimal getter and setter methods were skipped

    // This test tests everything about points: setting, getting, adding
    @Test
    public void pointsTest() {
        this.player.setPoints(10);
        assertEquals(10, this.player.getPoints());

        this.player.addPoints(3);
        assertEquals(13, this.player.getPoints());
    }

    @Test
    public void segnaliniFavoreTest() {
        this.player.setSegnalini_favore(4);
        assertEquals(4, this.player.getSegnalini_favore());
    }

    @Test
    public void payForToolTest() throws NotEnoughSegnaliniException {
        // Case in which you have more money
        this.player.setSegnalini_favore(4);
        this.player.payforTool(3);
        assertEquals(1, this.player.getSegnalini_favore());

        // Case with same money as tool card's cost
        this.player.setSegnalini_favore(3);
        this.player.payforTool(3);
        assertEquals(0, this.player.getSegnalini_favore());
    }

    @Test (expected = NotEnoughSegnaliniException.class)
    public void payForToolTestException() throws NotEnoughSegnaliniException {
        // Case with not enough money
        this.player.setSegnalini_favore(4);
        this.player.payforTool(5);
    }

    @Test
    public void getSelectedPlayerTest() throws PlayerNotFoundException {
        this.player.addPlayerToState(this.otherPlayer);
        Player playerFound = this.player.getSelectedPlayer("Diego");
        assertEquals("Diego", playerFound.getAssociatedUser().getName());
    }

    @Test (expected = PlayerNotFoundException.class)
    public void getSelectedPlayerTestException() throws PlayerNotFoundException {
        this.player.addPlayerToState(this.otherPlayer);
        Player playerFound = this.player.getSelectedPlayer("Antonio");
    }
}

*/