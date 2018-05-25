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


public class ColoriDiversiRigaTest {

    private ColoriDiversiRiga publicObjectiveCard;
    private Player mockPlayer;
    private SchemeCard mockSchemeCard;
    private int points;

    @Before
    public void before() {
        publicObjectiveCard = new ColoriDiversiRiga();
        mockPlayer = mock(Player.class);
        mockSchemeCard = mock(SchemeCard.class);
        points = 0;

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                points += 6;
                return null;
            }
        }).when(mockPlayer).addPoints(6);


        //when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
    }

    @Test
    public void calculatePointTest() throws OutOfMatrixException, DiceNotExistantException {

        when(mockSchemeCard.getDiceColour(0,0)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(0,1)).thenReturn(DiceColor.YELLOW);
        when(mockSchemeCard.getDiceColour(0,2)).thenReturn(DiceColor.BLUE);
        when(mockSchemeCard.getDiceColour(0,3)).thenReturn(DiceColor.RED);
        when(mockSchemeCard.getDiceColour(0,4)).thenReturn(DiceColor.VIOLET);

        when(mockSchemeCard.getDiceColour(1,0)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(1,1)).thenReturn(DiceColor.YELLOW);
        when(mockSchemeCard.getDiceColour(1,2)).thenReturn(DiceColor.BLUE);
        when(mockSchemeCard.getDiceColour(1,3)).thenReturn(DiceColor.RED);
        when(mockSchemeCard.getDiceColour(1,4)).thenReturn(DiceColor.VIOLET);

        when(mockSchemeCard.getDiceColour(3,0)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(3,1)).thenReturn(DiceColor.YELLOW);
        when(mockSchemeCard.getDiceColour(3,2)).thenReturn(DiceColor.BLUE);
        when(mockSchemeCard.getDiceColour(3,3)).thenReturn(DiceColor.RED);
        when(mockSchemeCard.getDiceColour(3,4)).thenReturn(DiceColor.VIOLET);

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(18, points);
    }

    @Test
    public void getIdTest() {
        assertEquals(1, publicObjectiveCard.getID());
    }

    @Test
    public void getName() {
        assertEquals("Colori Diversi - Riga", publicObjectiveCard.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("Righe senza colori ripetuti.", publicObjectiveCard.getDescription());
    }

}