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

public class FreeSpacesTest {
    private FreeSpaces publicObjectiveCard;
    private Player mockPlayer;
    private SchemeCard mockSchemeCard;
    private int points;

    @Before
    public void before() throws OutOfMatrixException, DiceNotExistantException, NoSuchFieldException, IllegalAccessException {
        publicObjectiveCard = new FreeSpaces();
        mockPlayer = mock(Player.class);
        mockSchemeCard = mock(SchemeCard.class);
        points = 0;

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                points--;
                return null;
            }
        }).when(mockPlayer).addPoints(-1);


        //when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
        //doThrow(new DiceNotExistantException()).when(mockPlayer).getScheme().getDiceIntensity(anyInt(), anyInt());
    }


    @Test
    public void calculateZeroDicesPointsTest() throws OutOfMatrixException, DiceNotExistantException, NoSuchFieldException{


        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(-20, points);
    }

    @Test
    public void calculateTenDicesPointsTest() throws OutOfMatrixException, DiceNotExistantException, NoSuchFieldException{     //test to fix

        when(mockSchemeCard.getDiceIntensity(0,0)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(1,0)).thenReturn(2);
        when(mockSchemeCard.getDiceIntensity(2,0)).thenReturn(3);
        when(mockSchemeCard.getDiceIntensity(3,0)).thenReturn(4);
        when(mockSchemeCard.getDiceIntensity(0,1)).thenReturn(5);
        when(mockSchemeCard.getDiceIntensity(1,1)).thenReturn(6);
        when(mockSchemeCard.getDiceIntensity(2,1)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(3,1)).thenReturn(2);
        when(mockSchemeCard.getDiceIntensity(0,2)).thenReturn(3);
        when(mockSchemeCard.getDiceIntensity(1,2)).thenReturn(4);

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(-10, points);
    }

    @Test
    public void getIdTest() {
        assertEquals(-1, publicObjectiveCard.getID());
    }

    @Test
    public void getName() {
        assertEquals("Free Spaces", publicObjectiveCard.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("A point less for every blank space in your window.", publicObjectiveCard.getDescription());
    }
}