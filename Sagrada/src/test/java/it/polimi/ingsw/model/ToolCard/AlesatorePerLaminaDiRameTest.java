package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.AlesatorePerLaminadiRameException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Dovrebbe essere fixato
// Uno dei grossi problemi evidenziati da questo test è il fatto che nel momento in cui un giocatore
// specifica una newRow e una newColumn che per qualche ragione danno errore, io perdo il dado in posizione (row, column)
// che volevo spostare.
// Il problema si presenta quindi o quando ho un errore legato ad un constrain, o quando ho una newRow newCol OutOfMatrix

// POSSIBILE SOLUZIONE: CORREGGERE AlesatorePerLaminaDiRame COME PennelloperEglomise

public class AlesatorePerLaminaDiRameTest {

    private AlesatoreperLaminadiRame alesatorePerLaminaDiRame;
    private Player mockPlayer;
    private Dice mockDice;
    private SchemeCard mockSchemeCard;

    @Before
    public void before() throws SchemeCardNotExistantException {
        this.alesatorePerLaminaDiRame = new AlesatoreperLaminadiRame(0,1, 1, 2);
        this.mockPlayer = mock(Player.class);
        this.mockDice = mock(Dice.class);
        this.mockSchemeCard = mock(SchemeCard.class);

        //when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
    }

    // This method tests if a Player specifies by mistake a wrong position on the schemeCard from where to pick a Dice
    @Test (expected = AlesatorePerLaminadiRameException.class)
    public void executeOutOfMatrixExceptionTest() throws ToolIllegalOperationException, OutOfMatrixException, DiceNotExistantException {
        this.alesatorePerLaminaDiRame = new AlesatoreperLaminadiRame(0,5, 2, 3);
        when(mockSchemeCard.getDice(0, 5)).thenThrow(OutOfMatrixException.class);

        this.alesatorePerLaminaDiRame.execute(mockPlayer);
    }

    // This method tests when there is a constrain in placing the Dice
    @Test (expected = AlesatorePerLaminadiRameException.class)
    public void executeTileConstrainException() throws OutOfMatrixException, TileConstrainException {
        // Grosso dubbio su come testare il caso in cui ci sia un constrain
        // when(mockSchemeCard.setDice(mockDice, 1 , 2, false, true, false)).thenThrow(TileConstrainException.class);
    }

    // This method tests when a Player choose a Tile without any Dice in it
    @Test (expected = AlesatorePerLaminadiRameException.class)
    public void executeDiceNotExistantException() throws OutOfMatrixException, DiceNotExistantException, ToolIllegalOperationException {
        when(mockSchemeCard.getDice(0, 1)).thenThrow(DiceNotExistantException.class);

        alesatorePerLaminaDiRame.execute(mockPlayer);
    }

    // This method tests that when everything should work no Exception is thrown
    @Test
    public void executeNoException() throws OutOfMatrixException, DiceNotExistantException, ToolIllegalOperationException {
        when(mockSchemeCard.getDice(0, 1)).thenReturn(mockDice);
        alesatorePerLaminaDiRame.execute(mockPlayer);
    }


    // Minor tests
    @Test
    public void testGetId() {
        assertEquals(3, this.alesatorePerLaminaDiRame.getID());
    }

    @Test
    public void testGetCardTitle() {
        assertEquals("Alesatore per lamina di rame", alesatorePerLaminaDiRame.getCardTitle());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore.\n" +
                        "Devi rispettare tutte le altre restrizioni di piazzamento.",
                      alesatorePerLaminaDiRame.getDescription());
    }
}
