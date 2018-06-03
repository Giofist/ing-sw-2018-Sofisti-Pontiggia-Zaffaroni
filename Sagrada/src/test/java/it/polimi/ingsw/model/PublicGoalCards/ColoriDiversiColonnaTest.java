package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.SchemeDeck.Tile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ColoriDiversiColonnaTest {

    private ColoriDiversiColonna publicObjectiveCard;
    private Player player;
    private SchemeCard mockSchemeCard;
    private ColumnIterator columnIterator;
    private RowIterator rowIterator;
    private Tile mockTile;
    private Dice mockDice;
    private Match mockMatch;
    private int points;

    @Before
    public void before() throws SchemeCardNotExistantException, CardIdNotAllowedException, IOException, MapConstrainReadingException {
        // Tested class
        publicObjectiveCard = new ColoriDiversiColonna();

        // Mock classes
        mockMatch = mock(Match.class);
        SchemeCard schemeCard = new SchemeCard(2);
        SchemeCard schemeCard2 = new SchemeCard(3);
        schemeCard.setTwinCard(schemeCard2);
        player = new Player();
        player.setMatch(mockMatch);
        player.addExtractedSchemeCard(schemeCard);
        player.setScheme(2);

        // Auxiliary variables
        points = 0;



        //when(mockPlayer.getScheme()).thenReturn(mockSchemeCard);
        //when(mockSchemeCard.columnIterator(0)).thenReturn(columnIterator);
        //when(mockSchemeCard.rowIterator(columnIterator.getCurrentColumn())).thenReturn(new RowIterator<Tile>(mockSchemeCard, columnIterator.getCurrentColumn()));

        // when(mockSchemeCard.getDiceColour(anyInt(), anyInt())).thenThrow(new DiceNotExistantException());
    }


    @Test
    public void calculatePointsTest() throws OutOfMatrixException, DiceNotExistantException, SchemeCardNotExistantException, TileConstrainException {
        player.getScheme().setDice(new Dice(DiceColor.GREEN), 0, 0, true, true, true);
        player.getScheme().setDice(new Dice(DiceColor.BLUE), 1, 0, true, true, true);
        player.getScheme().setDice(new Dice(DiceColor.RED), 2, 0, true, true, true);
        player.getScheme().setDice(new Dice(DiceColor.YELLOW), 3, 0, true, true, true);


        publicObjectiveCard.calculatepoint(player);

        assertEquals(5, player.getPoints());
    }



    /*
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
*/

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