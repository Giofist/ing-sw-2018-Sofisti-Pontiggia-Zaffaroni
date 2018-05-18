package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class AlesatorePerLaminaDiRameTest {

    private AlesatoreperLaminadiRame alesatorePerLaminaDiRame;
    private Player mockPlayer;

    @Before
    public void before(){
        this.alesatorePerLaminaDiRame = new AlesatoreperLaminadiRame(0,1, 0, 1);
        this.mockPlayer = mock(Player.class);
    }

    @Test (expected = OutOfMatrixException.class)
    public void OutOfMatrixExceptionTest() throws ToolIllegalOperationException {
        this.alesatorePerLaminaDiRame = new AlesatoreperLaminadiRame(0,1, 5, 3);
        this.alesatorePerLaminaDiRame.execute(mockPlayer);
    }

    @Test
    public void testGetId() {
        assertEquals(1, this.alesatorePerLaminaDiRame.getID());
    }

    @Test
    public void testGetCardTitle() {
        assertEquals("Alesatore per lamina di rame", alesatorePerLaminaDiRame.getCardTitle());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("\"Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore.\\n\" +\n" +
                              "Devi rispettare tutte le altre restrizioni di piazzamento.\"",
                      alesatorePerLaminaDiRame.getDescription());
    }
}
