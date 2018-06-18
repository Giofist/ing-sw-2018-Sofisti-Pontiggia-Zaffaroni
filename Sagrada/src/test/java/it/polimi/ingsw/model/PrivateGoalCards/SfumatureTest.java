package it.polimi.ingsw.model.PrivateGoalCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PublicGoalCards.ColoriDiversiColonna;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class SfumatureTest {

    private GoalCard privateGoalCard;

    private User user;
    private Player player;
    private SchemeCard schemeCard;
    private SchemeCard schemeCard2;
    private Match mockMatch;
    private PrivateGoalCardDeck privateGoalCardDeck;

    @Before
    public void before() throws CardIdNotAllowedException, IOException, MapConstrainReadingException, PrivateGoalCardException {
        // Tested class
        privateGoalCardDeck = new PrivateGoalCardDeck();
        privateGoalCard = privateGoalCardDeck.getCard();

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
    public void calculatepointTest() throws SchemeCardNotExistantException, OutOfMatrixException, TileConstrainException {
        Dice dice1 = new Dice(DiceColor.GREEN);
        dice1.setIntensity(3);
        Dice dice2 = new Dice(DiceColor.BLUE);
        dice2.setIntensity(2);
        Dice dice3 = new Dice(DiceColor.RED);
        dice3.setIntensity(3);
        Dice dice4 = new Dice(DiceColor.YELLOW);
        dice4.setIntensity(5);
        Dice dice5 = new Dice(DiceColor.YELLOW);
        dice5.setIntensity(1);

        player.getScheme().setDice(dice1, 0, 0, true, true, true);
        player.getScheme().setDice(dice2, 1, 0, true, true, true);
        player.getScheme().setDice(dice3, 2, 0, true, true, true);
        player.getScheme().setDice(dice4, 3, 0, true, true, true);
        player.getScheme().setDice(dice5, 3, 1, true, true, true);

        privateGoalCard.calculatepoint(player);

        switch (privateGoalCard.getID()){
            case 1:
                assertEquals(3, player.getPoints());
                assertEquals("Sfumature RED.", privateGoalCard.getName());
                assertEquals("Somma dei valori su tutti i dadi RED.", privateGoalCard.getDescription());
                break;
            case 2:
                assertEquals(6, player.getPoints());
                assertEquals("Sfumature YELLOW.", privateGoalCard.getName());
                assertEquals("Somma dei valori su tutti i dadi YELLOW.", privateGoalCard.getDescription());
                break;
            case 3:
                assertEquals(3, player.getPoints());
                assertEquals("Sfumature GREEN.", privateGoalCard.getName());
                assertEquals("Somma dei valori su tutti i dadi GREEN.", privateGoalCard.getDescription());
                break;
            case 4:
                assertEquals(2, player.getPoints());
                assertEquals("Sfumature BLUE.", privateGoalCard.getName());
                assertEquals("Somma dei valori su tutti i dadi BLUE.", privateGoalCard.getDescription());
                break;
            case 5:
                assertEquals(0, player.getPoints());
                assertEquals("Sfumature VIOLET.", privateGoalCard.getName());
                assertEquals("Somma dei valori su tutti i dadi VIOLET.", privateGoalCard.getDescription());
                break;
            default:
                throw new IndexOutOfBoundsException();
        }


    }


}