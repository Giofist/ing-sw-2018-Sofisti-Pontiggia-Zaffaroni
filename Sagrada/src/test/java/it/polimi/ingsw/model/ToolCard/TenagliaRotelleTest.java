package it.polimi.ingsw.model.ToolCard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
    1) A cosa serve in TenagliaRotelle il controllo che il giocatore è al secondo round se player.setMustpassTurn(true);
    suppongo faccia cambiare il round da subito senza nemmeno dare la possibilità al giocatore di scegliere la carta?

    2) getDice(selectedDiceIndex) non restituisce errore nel caso si passi un indice negativo
 */

public class TenagliaRotelleTest {

    private TenagliaRotelle toolCard;

    @Before
    public void before() {
        toolCard = new TenagliaRotelle(1, 2, 0);
    }


    // Minor tests
    @Test
    public void getIDTest() {
        assertEquals(8, toolCard.getID());
    }

    @Test
    public void getCardTitleTest() {
        assertEquals("Tenaglia a Rotelle", toolCard.getCardTitle());
    }

    @Test
    public void getCardDescriptionTest() {
        assertEquals("Dopo il tuo primo turno scegli immediatamente un altro dado.\n" +
                "Salta il secondo turno di questo round.", toolCard.getDescription());
    }
}