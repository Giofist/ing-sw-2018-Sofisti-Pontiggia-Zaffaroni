package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class SfumatureDiverseTest {

    private SfumatureDiverse publicObjectiveCard;
    private Player mockPlayer;
    private SchemeCard mockSchemeCard;
    private int points;

    @Before
    public void before() {
        publicObjectiveCard = new SfumatureDiverse();
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

        // when(mockSchemeCard.getDiceIntensity(anyInt(), anyInt())).thenThrow(new DiceNotExistantException());
        when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
    }

    @Test
    public void calculateNoPointTest() throws OutOfMatrixException, DiceNotExistantException {

        // The first and the second column are going to satisfy the objective
        when(mockSchemeCard.getDiceIntensity(0,0)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(1,0)).thenReturn(2);
        when(mockSchemeCard.getDiceIntensity(2,0)).thenReturn(2);
        when(mockSchemeCard.getDiceIntensity(3,0)).thenReturn(3);

        when(mockSchemeCard.getDiceIntensity(0,3)).thenReturn(2);
        when(mockSchemeCard.getDiceIntensity(1,3)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(2,3)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(3,3)).thenReturn(3);

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(0, points);
    }

    // Implement also the situation in which the objective is not satisfied
    @Test
    public void calculatePointTest() throws OutOfMatrixException, DiceNotExistantException {
        when(mockSchemeCard.getDiceIntensity(0,3)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(1,1)).thenReturn(6);
        when(mockSchemeCard.getDiceIntensity(2,0)).thenReturn(3);
        when(mockSchemeCard.getDiceIntensity(3,2)).thenReturn(2);


        when(mockSchemeCard.getDiceIntensity(3,3)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(1,3)).thenReturn(2);
        when(mockSchemeCard.getDiceIntensity(3,3)).thenReturn(4);
        when(mockSchemeCard.getDiceIntensity(3,1)).thenReturn(5);


        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        assertEquals(5, points);

    }


    @Test
    public void getIdTest() {
        assertEquals(8, publicObjectiveCard.getID());
    }

    @Test
    public void getName() {
        assertEquals("Sfumature Diverse", publicObjectiveCard.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("Set di dadi di ogni valore ovunque.", publicObjectiveCard.getDescription());
    }
}