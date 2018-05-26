package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SchemeCardDeckTest {

    private SchemeCardDeck schemeCardDeck;
    private SchemeCard mockSchemeCard;

    @Before
    public void before() throws IOException {
        schemeCardDeck = new SchemeCardDeck();
        mockSchemeCard = mock(SchemeCard.class);
    }

    @Test
    public void getCardTest() throws IOException, MapConstrainReadingException {
        mockSchemeCard = schemeCardDeck.getCard();
        //mockSchemeCard.setTwinCard(mockSchemeCard.getID());
    }

    @Test
    public void getRandomIdTest() {

    }

    @Test
    public void getNumMapsTest() throws IOException {
        assertEquals(24, schemeCardDeck.getNumMaps());
    }
}