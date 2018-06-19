package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SchemeCardDeckTest {

    private SchemeCardDeck schemeCardDeck;
    private SchemeCard schemeCard;

    @Before
    public void before() throws IOException {
        schemeCardDeck = new SchemeCardDeck();
    }

    @Test
    public void getCardTest() throws IOException, MapConstrainReadingException {
        for (int i = 0; i < (SchemeCardDeck.getNumMaps() / 2); i++) {
            schemeCard = schemeCardDeck.getCard();
            assertTrue((schemeCard.getID() % 2) != 0);
            assertTrue((schemeCard.getTwinCard().getID() % 2) == 0);
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getCardException() throws IOException, MapConstrainReadingException {
        for (int i = 0; i < (SchemeCardDeck.getNumMaps() / 2)+1; i++) {
            schemeCard = schemeCardDeck.getCard();
        }
    }

}