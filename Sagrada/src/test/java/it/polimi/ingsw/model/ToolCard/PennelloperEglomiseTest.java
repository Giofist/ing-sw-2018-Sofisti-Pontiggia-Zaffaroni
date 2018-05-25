package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.AlesatorePerLaminadiRameException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerEglomiseException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PennelloperEglomiseTest {

    private PennelloperEglomise toolCard;
    private Player mockPlayer;
    private Dice mockDice;
    private SchemeCard mockSchemeCard;

    @Before
    public void before() throws SchemeCardNotExistantException {
        toolCard = new PennelloperEglomise(0, 1, 1, 2);
        this.mockPlayer = mock(Player.class);
        this.mockDice = mock(Dice.class);
        this.mockSchemeCard = mock(SchemeCard.class);

        when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
    }


    // This method tests if a Player specifies by mistake a wrong position on the schemeCard from where to pick a Dice
    @Test (expected = PennelloPerEglomiseException.class)
    public void executeOutOfMatrixExceptionTest() throws ToolIllegalOperationException, OutOfMatrixException, DiceNotExistantException {
        toolCard = new PennelloperEglomise(0,5, 2, 3);
        when(mockSchemeCard.getDice(0, 5)).thenThrow(OutOfMatrixException.class);

        toolCard.execute(mockPlayer);
    }


    // This method tests when a Player choose a Tile without any Dice in it
    @Test (expected = PennelloPerEglomiseException.class)
    public void executeDiceNotExistantException() throws OutOfMatrixException, DiceNotExistantException, ToolIllegalOperationException {
        when(mockSchemeCard.getDice(0, 1)).thenThrow(DiceNotExistantException.class);

        toolCard.execute(mockPlayer);
    }


    // This method tests when there is a constrain in placing the Dice
    @Test (expected = PennelloPerEglomiseException.class)
    public void executeTileConstrainException() throws OutOfMatrixException, TileConstrainException {
        // Dubbio identico ad AlesatorePerLaminaDiRameTest
    }


    // Minor tests
    @Test
    public void getIDTest() {
        assertEquals(2, toolCard.getID());
    }

    @Test
    public void getCardTitleTest() {
        assertEquals("Pennello per Eglomise", toolCard.getCardTitle());
    }

    @Test
    public void getCardDescriptionTest() {
        assertEquals("Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore.\n" +
                "Devi rispettare tutte le altre restrizioni di piazzamento.", toolCard.getDescription());
    }
}