package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SfumatureChiareTest {
    private SfumatureChiare publicObjectiveCard;
    private Player mockPlayer;
    private SchemeCard mockSchemeCard;
    private int points;

    @Before
    public void before() throws OutOfMatrixException, DiceNotExistantException, NoSuchFieldException, IllegalAccessException {
        publicObjectiveCard = new SfumatureChiare();
        mockPlayer = mock(Player.class);
        mockSchemeCard = mock(SchemeCard.class);
        points = 0;

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                points += 2;
                return null;
            }
        }).when(mockPlayer).addPoints(2);

        //when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
        //doThrow(new DiceNotExistantException()).when(mockPlayer).getScheme().getDiceIntensity(anyInt(), anyInt());
    }


    @Test
    public void calculatePointsTest() throws OutOfMatrixException, DiceNotExistantException, NoSuchFieldException{

        when(mockSchemeCard.getDiceIntensity(1,1)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(3,2)).thenReturn(2);

        when(mockSchemeCard.getDiceIntensity(1,3)).thenReturn(4);
        when(mockSchemeCard.getDiceIntensity(3,3)).thenReturn(3);

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(2, points);
    }

    @Test
    public void calculateDubleThePointsTest() throws OutOfMatrixException, DiceNotExistantException, NoSuchFieldException{

        when(mockSchemeCard.getDiceIntensity(1,1)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(3,2)).thenReturn(2);

        when(mockSchemeCard.getDiceIntensity(1,3)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(3,1)).thenReturn(2);

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(4, points);
    }

    @Test
    public void calculateOnePointsTest() throws OutOfMatrixException, DiceNotExistantException, NoSuchFieldException{

        when(mockSchemeCard.getDiceIntensity(3,2)).thenReturn(2);

        when(mockSchemeCard.getDiceIntensity(1,3)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(3,1)).thenReturn(2);

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(2, points);
    }


    @Test
    public void getIdTest() {
        assertEquals(5, publicObjectiveCard.getID());
    }

    @Test
    public void getName() {
        assertEquals("Sfumature Chiare", publicObjectiveCard.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("Set di 1 & 2 ovunque.", publicObjectiveCard.getDescription());
    }
}