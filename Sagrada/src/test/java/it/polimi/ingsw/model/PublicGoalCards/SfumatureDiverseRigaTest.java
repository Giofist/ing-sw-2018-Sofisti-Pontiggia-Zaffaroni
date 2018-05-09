package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Player;
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
    private int points;

    @Before
    public void before() throws OutOfMatrixException, DiceNotExistantException, NoSuchFieldException, IllegalAccessException {
        publicObjectiveCard = new SfumatureDiverseRiga();
        mockPlayer = mock(Player.class);
        points = 0;

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                points += 5;
                return null;
            }
        }).when(mockPlayer).addPoints(5);


        doThrow(new DiceNotExistantException()).when(mockPlayer).getScheme().getDiceIntensity(anyInt(), anyInt());
    }

    @Test
    public void calculatePointTest() throws OutOfMatrixException, DiceNotExistantException {

        when(mockPlayer.getScheme().getDiceColour(0,0)).thenReturn(DiceColor.GREEN);
        when(mockPlayer.getScheme().getDiceColour(0,1)).thenReturn(DiceColor.YELLOW);
        when(mockPlayer.getScheme().getDiceColour(0,2)).thenReturn(DiceColor.BLUE);
        when(mockPlayer.getScheme().getDiceColour(0,3)).thenReturn(DiceColor.RED);
        when(mockPlayer.getScheme().getDiceColour(0,4)).thenReturn(DiceColor.VIOLET);


        publicObjectiveCard.calculatepoint(mockPlayer);

        assertEquals(5, points);
    }

}