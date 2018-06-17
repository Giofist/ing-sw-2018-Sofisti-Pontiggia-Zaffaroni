package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class VarietaDiColoreTest {

    private VarietaDiColore publicObjectiveCard;
    private User user;
    private Player player;
    private SchemeCard schemeCard;
    private SchemeCard schemeCard2;
    private Match mockMatch;

    @Before
    public void before() throws SchemeCardNotExistantException, CardIdNotAllowedException, IOException, MapConstrainReadingException {
        // Tested class
        publicObjectiveCard = new VarietaDiColore();

        // Classes for setup
        schemeCard = new SchemeCard(2);
        schemeCard2 = new SchemeCard(3);
        schemeCard.setTwinCard(schemeCard2);
        schemeCard2.setTwinCard(schemeCard);
        mockMatch = mock(Match.class);
        doNothing().when(mockMatch).countDown();

        user = new User("User1", "pass");

        player = new Player();
        player.setMatch(mockMatch);
        player.setUser(user);
        player.addExtractedSchemeCard(schemeCard);
        player.setScheme(2);

    }


    @Test
    public void calculatePointsTest() throws OutOfMatrixException, SchemeCardNotExistantException, TileConstrainException {
        player.getScheme().setDice(new Dice(DiceColor.GREEN), 0, 0, true, true, true);
        player.getScheme().setDice(new Dice(DiceColor.BLUE), 1, 0, true, true, true);
        player.getScheme().setDice(new Dice(DiceColor.RED), 2, 0, true, true, true);
        player.getScheme().setDice(new Dice(DiceColor.YELLOW), 3, 0, true, true, true);
        player.getScheme().setDice(new Dice(DiceColor.VIOLET), 3, 1, true, true, true);
        player.getScheme().setDice(new Dice(DiceColor.VIOLET), 3, 2, true, true, true);


        publicObjectiveCard.calculatepoint(player);

        assertEquals(4, player.getPoints());
    }


    // Getters tests

    @Test
    public void getIdTest() {
        assertEquals(10, publicObjectiveCard.getID());
    }

    @Test
    public void getName() {
        assertEquals("Varietà di Colore", publicObjectiveCard.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("Set di dadi di ogni colore ovunque.", publicObjectiveCard.getDescription());
    }

}