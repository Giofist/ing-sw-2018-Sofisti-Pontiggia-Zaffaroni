package it.polimi.ingsw.model.ToolCard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


// Martelletto va ancora finita lato model una volta implementato il sistema dei round

public class MartellettoTest {

    private Martelletto toolCard;

    @Before
    public void before() {
        //toolCard = new Martelletto();
    }

    @Test
    public void execute() {
    }


    // Minor tests
    @Test
    public void getIDTest() {
        assertEquals(7, toolCard.getID());
    }

    @Test
    public void getCardTitleTest() {
        assertEquals("Martelletto", toolCard.getCardTitle());
    }

    @Test
    public void getCardDescriptionTest() {
        assertEquals("Tira nuovamente tutti i dadi della riserva.\n" +
                "Questa carta può essere usata solo durante il tuo secondo turno, prima di scegliere il secondo dado.", toolCard.getDescription());
    }
}