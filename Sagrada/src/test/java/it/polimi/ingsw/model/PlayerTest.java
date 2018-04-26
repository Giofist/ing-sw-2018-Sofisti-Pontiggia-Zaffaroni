package it.polimi.ingsw.model;

import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {
    Player player;
    private SchemeCard scheme;

    @Before
    public void before(){
        // Mock directives
        User user = mock(User.class);
        this.scheme = mock(SchemeCard.class);

        when(scheme.getDifficulty()).thenReturn(4);

        this.player = new Player(user);
    }


    @Test
    public void payforTool() {
    }

    @Test
    public void setPublicGoalCards() {
    }

    @Test
    public void getPublicGoalCards() {
    }

    @Test
    public void setPrivateGoalCard() {
    }

    @Test
    public void getPrivateGoalCard() {
    }

    @Test
    public void setScheme() {
    }

    @Test
    public void getScheme() {
    }


    @Test
    public void setGametable() {
    }


    @Test
    public void addPlayer() {
    }

    @Test
    public void getSelectedPlayer() {
    }
}