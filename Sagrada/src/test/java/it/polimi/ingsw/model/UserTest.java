package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UserTest {

    private User user;
    private Player mockPlayer;

    @Before
    public void before() {
        user = new User("Xenomit", "ciao!");
        mockPlayer = mock(Player.class);
    }

    // At the moment of creation the user won't be active
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
    public void getPassword() {
        assertEquals("ciao!", user.getPassword());
    }

    @Test
    public void setPassword() {
        assertEquals("ciao!", user.getPassword());
        user.setPassword("ciao!!");
        assertEquals("ciao!!", user.getPassword());
    }

    @Test
    public void setAndGetPlayer() {
        assertNull(user.getPlayer());
        user.setPlayer(mockPlayer);
        assertNotNull(user.getPlayer());
    }

}