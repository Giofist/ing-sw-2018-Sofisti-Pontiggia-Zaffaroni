package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RowIteratorTest {

    private RowIterator schemeCardIterator;
    private SchemeCard schemeCard;

    @Before
    public void before() throws IOException, MapConstrainReadingException {
        schemeCard = new SchemeCard(2);
        schemeCardIterator = new RowIterator(schemeCard, 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void nextHasNextTestException() {
        for (int i = 0; i < schemeCard.getMaxRow(); i++) {
            assertTrue(schemeCardIterator.hasNext());
            assertEquals(i, schemeCardIterator.getCurrentRow());
            schemeCardIterator.next();
        }

        assertFalse(schemeCardIterator.hasNext());
        schemeCardIterator.next();
    }


    @Test (expected = NoSuchElementException.class)
    public void hasNextWrongRowException() {
        schemeCardIterator = new RowIterator(schemeCard, -1);
        assertTrue(schemeCardIterator.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void nextWrongRowException() {
        schemeCardIterator = new RowIterator(schemeCard, -1);
        schemeCardIterator.next();
    }

}