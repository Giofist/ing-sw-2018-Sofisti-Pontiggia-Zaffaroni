package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {

    private User user;
    private Player mockPlayer;
    private Observer mockObserver;
    private Observable mockPlayerState;

    @Before
    public void before() {
        // Tested class
        user = new User("Xenomit", "ciao!");

        // Useful classes
        mockPlayer = mock(Player.class);
        mockObserver = mock(Observer.class);
        mockPlayerState = mock(Observable.class);

        // Mock behaviours
        when(mockPlayer.toString()).thenReturn("Xenomit");
        when(mockPlayer.getPlayerState()).thenReturn(mockPlayerState);
        doNothing().when(mockPlayerState).removeObserver(mockObserver);
    }


    /**
     * This method verifies that new user is not in an active state once created
     */
    @Test
    public void isActive() {
        assertFalse(user.isActive());
    }

    @Test
    public void setActive() {
        user.setActive(true);
        assertTrue(user.isActive());
    }

    @Test
    public void getName() {
        assertEquals("Xenomit", user.getName());
    }

    @Test
    public void setName() {
        assertEquals("Xenomit", user.getName());
        user.setName("Xenomit2");
        assertEquals("Xenomit2", user.getName());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("ciao!", user.getPassword());
    }

    @Test
    public void setPasswordTest() {
        assertEquals("ciao!", user.getPassword());
        user.setPassword("ciao!!");
        assertEquals("ciao!!", user.getPassword());
    }

    @Test
    public void setAndGetPlayerTest() {
        assertNull(user.getPlayer());
        user.setPlayer(mockPlayer);
        assertEquals("Xenomit", user.getPlayer().toString());
        user.removePlayer(mockObserver);
        assertNull(user.getPlayer());
    }

    @Test
    public void equalsTest() {
        User user2 = new User("Paolo", "pass");
        assertFalse(user.equals(user2));

        user2 = new User("Xenomit", "ciao!");
        assertTrue(user.equals(user2));

        user2 = new User("Xenomit", "ajsdosdja");
        assertTrue(user.equals(user2));

        user2 = new User("Paolo", "ciao!");
        assertFalse(user.equals(user2));
    }

}