package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.LathekinException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
    Similmente ad AlesatoreperLaminadiRame anche in Lathekin vanno corrette alcune cose:
    1) Supponendo che il giocatore scelga di spostare 2 dadi con posizioni valide in delle caselle in cui
       per il secondo dado vengono dati dei parametri incorretti, si rischia di avere un problema di duplicazione
       in quanto il primo dado verrebbe piazzato correttamente nella nuova posizione ma verrebbe piazzato anche
       nella posizione di partenza nel momento in cui si cerca di ripristinare la situazione iniziale nel ramo catch di execute
    2) Problemi simili possono sorgere quando il giocatore cerca di piazzare 2 dadi diversi nella stessa casella
 */

public class LathekinTest {

    private Lathekin toolCard;
    private Player mockPlayer;
    private SchemeCard mockSchemeCard;
    private Dice mockDice1;
    private Dice mockDice2;

    @Before
    public void before() throws SchemeCardNotExistantException, OutOfMatrixException, DiceNotExistantException {
        toolCard = new Lathekin(0, 1, 1, 2, 2, 3, 3, 3);
        mockPlayer = mock(Player.class);
        mockSchemeCard = mock(SchemeCard.class);
        mockDice1 = mock(Dice.class);
        mockDice2 = mock(Dice.class);

        when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
        when(mockSchemeCard.getDice(0, 1)).thenReturn(mockDice1);
        when(mockSchemeCard.getDice(2, 3)).thenReturn(mockDice2);
    }

    // This method tests for OutOfMatrixException if a user choose a Dice from a non existent Tile
    @Test (expected = LathekinException.class)
    public void executeOutOfMatrixExceptionSourceTest() throws ToolIllegalOperationException, OutOfMatrixException, DiceNotExistantException {
        toolCard = new Lathekin(5, 1, 1, 2, 2, 3, 3, 3);
        when(mockSchemeCard.getDice(5, 1)).thenThrow(OutOfMatrixException.class);

        toolCard.execute(mockPlayer);
    }

    // This method will test for OutOfMatrixException if a user choose to move a Dice in a non existent Tile
    @Test (expected = LathekinException.class)
    public void executeOutOfMatrixExceptionDestinationTest() {

    }

    // This method will test Exception raised by a constrain
    @Test (expected = LathekinException.class)
    public void executeConstrainExceptionTest() {

    }

    // This method will test when a Player choose to place 2 different Dice in the same place
    @Test
    public void twoDicesSameDestinationTest() {

    }

    // This method will test when a Player choose to move the same Dice
    @Test
    public void sameDiceToMoveTest() {

    }


    // Minor tests
    @Test
    public void getIDTest() {
        assertEquals(4, toolCard.getID());
    }

    @Test
    public void getCardTitleTest() {
        assertEquals("Lathekin", toolCard.getCardTitle());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("Muovi esattamente due dadi.\n" +
                            "Rispetta tutte le restrizioni di piazzamento.", toolCard.getDescription());
    }
}