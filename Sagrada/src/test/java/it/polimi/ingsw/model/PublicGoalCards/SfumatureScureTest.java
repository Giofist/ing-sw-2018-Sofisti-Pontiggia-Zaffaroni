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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class SfumatureScureTest {
    private SfumatureScure publicObjectiveCard;
    private Player mockPlayer;
    private SchemeCard mockSchemeCard;
    private int points;

    @Before
    public void before() throws OutOfMatrixException, DiceNotExistantException, NoSuchFieldException, IllegalAccessException {
        publicObjectiveCard = new SfumatureScure();
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

        when(mockSchemeCard.getDiceIntensity(3,4)).thenReturn(5);
        when(mockSchemeCard.getDiceIntensity(2,1)).thenReturn(6);

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(2, points);
    }

    @Test
    public void getIdTest() {
        assertEquals(7, publicObjectiveCard.getID());
    }

    @Test
    public void getName() {
        assertEquals("Sfumature Scure", publicObjectiveCard.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("Set di 5 & 6 ovunque.", publicObjectiveCard.getDescription());
    }
}