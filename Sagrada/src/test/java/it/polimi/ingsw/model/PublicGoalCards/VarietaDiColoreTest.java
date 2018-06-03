package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
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


public class VarietaDiColoreTest {

    private VarietaDiColore publicObjectiveCard;
    private Player mockPlayer;
    private SchemeCard mockSchemeCard;
    private int points;

    @Before
    public void before() {
        publicObjectiveCard = new VarietaDiColore();
        mockPlayer = mock(Player.class);
        mockSchemeCard = mock(SchemeCard.class);
        points = 0;

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                points += 4;
                return null;
            }
        }).when(mockPlayer).addPoints(4);


        //when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
    }

    @Test
    public void calculatePointTest() throws OutOfMatrixException, DiceNotExistantException {

        when(mockSchemeCard.getDiceColour(0,0)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(1,1)).thenReturn(DiceColor.YELLOW);
        when(mockSchemeCard.getDiceColour(2,2)).thenReturn(DiceColor.BLUE);
        when(mockSchemeCard.getDiceColour(3,3)).thenReturn(DiceColor.RED);
        when(mockSchemeCard.getDiceColour(4,4)).thenReturn(DiceColor.VIOLET);

        /*
        when(mockSchemeCard.getDiceColour(4,0)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(2,1)).thenReturn(DiceColor.YELLOW);
        when(mockSchemeCard.getDiceColour(3,2)).thenReturn(DiceColor.BLUE);
        when(mockSchemeCard.getDiceColour(0,3)).thenReturn(DiceColor.RED);
        when(mockSchemeCard.getDiceColour(1,4)).thenReturn(DiceColor.VIOLET);

        when(mockSchemeCard.getDiceColour(3,0)).thenReturn(DiceColor.GREEN);
        when(mockSchemeCard.getDiceColour(4,1)).thenReturn(DiceColor.YELLOW);
        when(mockSchemeCard.getDiceColour(0,2)).thenReturn(DiceColor.BLUE);
        when(mockSchemeCard.getDiceColour(1,3)).thenReturn(DiceColor.RED);
        when(mockSchemeCard.getDiceColour(2,4)).thenReturn(DiceColor.VIOLET);
        */

        // Invoking the calculate point function
        publicObjectiveCard.calculatepoint(mockPlayer);

        // Check if the score was calulated as expected
        assertEquals(4, points);
    }

    @Test
    public void getIdTest() {
        assertEquals(10, publicObjectiveCard.getID());
    }

    @Test
    public void getName() {
        assertEquals("Varietà di Colore", publicObjectiveCard.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("Set di dadi di ogni colore ovunque.", publicObjectiveCard.getDescription());
    }

}