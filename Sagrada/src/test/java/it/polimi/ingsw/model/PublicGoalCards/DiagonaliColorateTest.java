package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class DiagonaliColorateTest {

    private DiagonaliColorate publicObjectiveCard;
    private User user;
    private Player player;
    private SchemeCard schemeCard;
    private SchemeCard schemeCard2;
    private Match mockMatch;

    @Before
    public void before() throws SchemeCardNotExistantException, CardIdNotAllowedException, IOException, MapConstrainReadingException {
        // Tested class
        publicObjectiveCard = new DiagonaliColorate();

        // Classes for setup
        schemeCard = new SchemeCard(2);
        schemeCard2 = new SchemeCard(3);
        schemeCard.setTwinCard(schemeCard2);
        schemeCard2.setTwinCard(schemeCard);
        mockMatch = mock(Match.class);
        doNothing().when(mockMatch).countDown();


        player = new Player();
        player.setMatch(mockMatch);
        player.addExtractedSchemeCard(schemeCard);
        player.setScheme(2);

    }

    @Test
    public void calculatePointsTest() throws OutOfMatrixException, SchemeCardNotExistantException, TileConstrainException {

        player.getScheme().setDice(new Dice(DiceColor.GREEN), 3, 2, true, true, true);
        player.getScheme().setDice(new Dice(DiceColor.GREEN), 2, 3, true, true, true);
        player.getScheme().setDice(new Dice(DiceColor.GREEN), 1, 4, true, true, true);


        publicObjectiveCard.calculatepoint(player);

        assertEquals(3, player.getPoints());
    }

    // Getters Test

    @Test
    public void getIdTest() {
        assertEquals(9, publicObjectiveCard.getID());
    }



}