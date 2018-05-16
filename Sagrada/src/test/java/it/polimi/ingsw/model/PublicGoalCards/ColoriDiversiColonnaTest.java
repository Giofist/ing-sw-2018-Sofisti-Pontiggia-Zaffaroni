package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ColoriDiversiColonnaTest {

    private ColoriDiversiColonna publicObjectiveCard;
    private Player mockPlayer;
    private SchemeCard mockSchemeCard;
    private int points;

    @Before
    public void before() {
        publicObjectiveCard = new ColoriDiversiColonna();
        mockPlayer = mock(Player.class);
        mockSchemeCard = mock(SchemeCard.class);
        points = 0;

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                points += 5;
                return null;
            }
        }).when(mockPlayer).addPoints(5);

        // when(mockSchemeCard.getDiceColour(anyInt(), anyInt())).thenThrow(new DiceNotExistantException());
        when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
    }

    @Test
    public void calculatePointTest() throws OutOfMatrixException, DiceNotExistantException {

        // The first and the second column are going to satisfy the objective
        when(mockSchemeCard.getDiceColour(0,0)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(1,0)).thenReturn(DiceColor.YELLOW);
        when(mockSchemeCard.getDiceColour(2,0)).thenReturn(DiceColor.BLUE);
        when(mockSchemeCard.getDiceColour(3,0)).thenReturn(DiceColor.RED);


        when(mockSchemeCard.getDiceColour(0,3)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(1,3)).thenReturn(DiceColor.YELLOW);
        when(mockSchemeCard.getDiceColour(2,3)).thenReturn(DiceColor.BLUE);
        when(mockSchemeCard.getDiceColour(3,3)).thenReturn(DiceColor.VIOLET);

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(10, points);
    }

    // Implement also the situation in which the objective is not satisfied
    @Test
    public void calculatePointNoNewPointsTest() throws OutOfMatrixException, DiceNotExistantException {
        when(mockSchemeCard.getDiceColour(0,0)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(1,0)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(2,0)).thenReturn(DiceColor.BLUE);
        when(mockSchemeCard.getDiceColour(3,0)).thenReturn(DiceColor.RED);


        when(mockSchemeCard.getDiceColour(0,3)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(1,3)).thenReturn(DiceColor.YELLOW);
        when(mockSchemeCard.getDiceColour(2,3)).thenReturn(DiceColor.BLUE);
        when(mockSchemeCard.getDiceColour(3,3)).thenReturn(DiceColor.RED);

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        assertEquals(5, points);

    }


    @Test
    public void getIdTest() {
        assertEquals(2, publicObjectiveCard.getID());
    }

    @Test
    public void getName() {
        assertEquals("Colori Diversi - Colonna", publicObjectiveCard.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("Colonne senza colori ripetuti.", publicObjectiveCard.getDescription());
    }
}