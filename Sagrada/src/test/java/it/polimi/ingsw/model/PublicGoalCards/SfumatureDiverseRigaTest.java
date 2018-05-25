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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


public class SfumatureDiverseRigaTest {

    private SfumatureDiverseRiga publicObjectiveCard;
    private Player mockPlayer;
    private SchemeCard mockSchemeCard;
    private int points;

    @Before
    public void before() {
        publicObjectiveCard = new SfumatureDiverseRiga();
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


        //when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
    }

    @Test
    public void calculatePointTest() throws OutOfMatrixException, DiceNotExistantException {

        when(mockSchemeCard.getDiceIntensity(0,0)).thenReturn(4);
        when(mockSchemeCard.getDiceIntensity(0,1)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(0,2)).thenReturn(2);
        when(mockSchemeCard.getDiceIntensity(0,3)).thenReturn(5);
        when(mockSchemeCard.getDiceIntensity(0,4)).thenReturn(6);

        when(mockSchemeCard.getDiceIntensity(1,0)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(1,1)).thenReturn(2);
        when(mockSchemeCard.getDiceIntensity(1,2)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(1,3)).thenReturn(5);
        when(mockSchemeCard.getDiceIntensity(1,4)).thenReturn(6);

        when(mockSchemeCard.getDiceIntensity(3,0)).thenReturn(1);
        when(mockSchemeCard.getDiceIntensity(3,1)).thenReturn(3);
        when(mockSchemeCard.getDiceIntensity(3,2)).thenReturn(6);
        when(mockSchemeCard.getDiceIntensity(3,3)).thenReturn(4);
        when(mockSchemeCard.getDiceIntensity(3,4)).thenReturn(5);

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(10, points);
    }

    @Test
    public void getIdTest() {
        assertEquals(3, publicObjectiveCard.getID());
    }

    @Test
    public void getName() {
        assertEquals("Sfumature diverse - Riga", publicObjectiveCard.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("Righe senza sfumature ripetute.", publicObjectiveCard.getDescription());
    }

}