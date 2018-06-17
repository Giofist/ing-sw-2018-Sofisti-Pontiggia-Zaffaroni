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


public class SfumatureDiverseTest {

    private SfumatureDiverse publicObjectiveCard;
    private User user;
    private Player player;
    private SchemeCard schemeCard;
    private SchemeCard schemeCard2;
    private Match mockMatch;

    @Before
    public void before() throws SchemeCardNotExistantException, CardIdNotAllowedException, IOException, MapConstrainReadingException {
        // Tested class
        publicObjectiveCard = new SfumatureDiverse();

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
        Dice dice1 = new Dice(DiceColor.GREEN);
        dice1.setIntensity(1);
        Dice dice2 = new Dice(DiceColor.GREEN);
        dice2.setIntensity(2);
        Dice dice3 = new Dice(DiceColor.GREEN);
        dice3.setIntensity(3);
        Dice dice4 = new Dice(DiceColor.GREEN);
        dice4.setIntensity(4);
        Dice dice5 = new Dice(DiceColor.GREEN);
        dice5.setIntensity(4);
        Dice dice6 = new Dice(DiceColor.GREEN);
        dice6.setIntensity(5);
        Dice dice7 = new Dice(DiceColor.GREEN);
        dice7.setIntensity(6);

        player.getScheme().setDice(dice1, 0, 0, true, true, true);
        player.getScheme().setDice(dice2, 1, 1, true, true, true);
        player.getScheme().setDice(dice3, 1, 3, true, true, true);
        player.getScheme().setDice(dice4, 3, 4, true, true, true);
        player.getScheme().setDice(dice5, 1, 0, true, true, true);
        player.getScheme().setDice(dice6, 1, 2, true, true, true);
        player.getScheme().setDice(dice7, 2, 3, true, true, true);


        publicObjectiveCard.calculatepoint(player);

        assertEquals(5, player.getPoints());
    }


    // Getters tests

    @Test
    public void getIdTest() {
        assertEquals(8, publicObjectiveCard.getID());
    }

    @Test
    public void getName() {
        assertEquals("Sfumature Diverse", publicObjectiveCard.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("Set di dadi di ogni valore ovunque.", publicObjectiveCard.getDescription());
    }
}