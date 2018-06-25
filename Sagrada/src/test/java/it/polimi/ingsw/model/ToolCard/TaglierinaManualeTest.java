package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// COMPLETE L'ULTIMO TEST MOSTRA COME NELL'IMPLEMENTAZIONE NON RIMETTE CORRETTAMENTE A POSTO IL DADO IN CASO DI ERRORE

public class TaglierinaManualeTest {

    private TaglierinaManuale toolCard;
    private ToolRequestClass toolRequestClass;
    private Player player;
    private Match mockMatch;
    private Turn mockTurn;
    private DicePool roundDicePool;
    private Gametable mockGameTable;
    private RoundTrack roundTrack;
    private SchemeCard schemeCard;
    private Dice mockDice1;
    private Dice mockDice2;
    private Dice mockDiceTrack;

    @Before
    public void before() throws IOException, MapConstrainReadingException, CardIdNotAllowedException {
        // Tested Class
        toolCard = new TaglierinaManuale();

        // Required classes for game interaction
        player = new Player();
        toolRequestClass = new ToolRequestClass();
        mockMatch = mock(Match.class);
        mockGameTable = mock(Gametable.class);
        mockTurn = mock(Turn.class);
        roundTrack = new RoundTrack();
        schemeCard = new SchemeCard(3);
        mockDice1 = mock(Dice.class);
        mockDice2 = mock(Dice.class);
        mockDiceTrack = mock(Dice.class);

        // Player setup
        player.addExtractedSchemeCard(schemeCard);
        player.setMatch(mockMatch);
        player.setTurn(mockTurn);
        player.setPlayerState(State.STARTTURNSTATE);
        player.setScheme(3);

        // Scheme card setup
        schemeCard.setTwinCard(new SchemeCard(4));

        // Mock behaviour
        when(mockMatch.getGametable()).thenReturn(mockGameTable);
        when(mockGameTable.getRoundDicepool()).thenReturn(roundDicePool);
        when(mockGameTable.getRoundTrack()).thenReturn(roundTrack);
    }


    @Test
    public void executeOKTwoDices() throws ToolIllegalOperationException, SchemeCardNotExistantException, OutOfMatrixException, TileConstrainException, RoundTrackException, DiceNotExistantException {
        // Mock Dices behaviour
        when (mockDice1.getColor()).thenReturn(DiceColor.RED);
        when (mockDice2.getColor()).thenReturn(DiceColor.RED);
        when(mockDiceTrack.getColor()).thenReturn(DiceColor.RED);

        // Set the two Dices on the SchemeCard
        player.getScheme().setDice(mockDice1, 0, 0, false, false, false);
        player.getScheme().setDice(mockDice2, 1, 1, false, false, false);


        // Set mockDiceTrack on roundtrack
        List<Dice> roundTrackDices = new LinkedList<>();
        roundTrackDices.add(mockDiceTrack);
        roundTrack.setRoundTrackDices(1, roundTrackDices);


        // Prepare the toolRequestClass
        toolRequestClass.setNumberofDicesyouwanttomove(2);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(0);
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(2);

        toolRequestClass.setOldRow2(1);
        toolRequestClass.setOldColumn2(1);
        toolRequestClass.setNewRow2(1);
        toolRequestClass.setNewColumn2(3);


        // Tested method
        toolCard.execute(player, toolRequestClass);


        // Final checks
        assertFalse(player.getScheme().IsTileOccupied(0,0));
        assertFalse(player.getScheme().IsTileOccupied(1,1));
        assertTrue(player.getScheme().getDice(0, 2) == mockDice1);
        assertTrue(player.getScheme().getDice(1, 3) == mockDice2);
        assertEquals(State.HASUSEDATOOLCARDACTIONSTATE, player.getPlayerState().getState());
    }



    @Test
    public void executeOKOneDice() throws ToolIllegalOperationException, SchemeCardNotExistantException, OutOfMatrixException, TileConstrainException, RoundTrackException, DiceNotExistantException {
        // Mock Dices behaviour
        when (mockDice1.getColor()).thenReturn(DiceColor.RED);
        when(mockDiceTrack.getColor()).thenReturn(DiceColor.RED);

        // Set the two Dices on the SchemeCard
        player.getScheme().setDice(mockDice1, 0, 0, false, false, false);


        // Set mockDiceTrack on roundtrack
        List<Dice> roundTrackDices = new LinkedList<>();
        roundTrackDices.add(mockDiceTrack);
        roundTrack.setRoundTrackDices(1, roundTrackDices);


        // Prepare the toolRequestClass
        toolRequestClass.setNumberofDicesyouwanttomove(1);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(0);
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(2);


        // Tested method
        toolCard.execute(player, toolRequestClass);


        // Final checks
        assertFalse(player.getScheme().IsTileOccupied(0,0));
        assertTrue(player.getScheme().getDice(0, 2) == mockDice1);
        assertEquals(State.HASUSEDATOOLCARDACTIONSTATE, player.getPlayerState().getState());
    }


    @Test
    public void executeNotProperColorInRoundTrack() throws ToolIllegalOperationException, SchemeCardNotExistantException, OutOfMatrixException, TileConstrainException, RoundTrackException, DiceNotExistantException {
        // Mock Dices behaviour
        when (mockDice1.getColor()).thenReturn(DiceColor.RED);
        when(mockDiceTrack.getColor()).thenReturn(DiceColor.YELLOW);

        // Set the two Dices on the SchemeCard
        player.getScheme().setDice(mockDice1, 0, 0, false, false, false);


        // Set mockDiceTrack on roundtrack
        List<Dice> roundTrackDices = new LinkedList<>();
        roundTrackDices.add(mockDiceTrack);
        roundTrack.setRoundTrackDices(1, roundTrackDices);


        // Prepare the toolRequestClass
        toolRequestClass.setNumberofDicesyouwanttomove(1);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(0);
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(2);


        try {
            // Tested method
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            // Final checks
            assertTrue(player.getScheme().getDice(0, 0) == mockDice1);
            assertFalse(player.getScheme().IsTileOccupied(0,2));
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        assertFalse(0 == 0);
    }


    @Test
    public void executeNewInvalidPosition() throws SchemeCardNotExistantException, OutOfMatrixException, TileConstrainException, RoundTrackException, DiceNotExistantException {
        // Mock Dices behaviour
        when (mockDice1.getColor()).thenReturn(DiceColor.RED);
        when(mockDiceTrack.getColor()).thenReturn(DiceColor.RED);

        // Set the two Dices on the SchemeCard
        player.getScheme().setDice(mockDice1, 0, 0, false, false, false);


        // Set mockDiceTrack on roundtrack
        List<Dice> roundTrackDices = new LinkedList<>();
        roundTrackDices.add(mockDiceTrack);
        roundTrack.setRoundTrackDices(1, roundTrackDices);


        // Prepare the toolRequestClass
        toolRequestClass.setNumberofDicesyouwanttomove(1);

        toolRequestClass.setOldRow1(0);
        toolRequestClass.setOldColumn1(0);
        toolRequestClass.setNewRow1(0);
        toolRequestClass.setNewColumn1(5);


        try {
            // Tested method
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            // Final checks
            assertTrue(player.getScheme().getDice(0, 0) == mockDice1);
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        assertFalse(0 == 0);
    }


    @Test
    public void getID() {
        assertEquals(12, toolCard.getID());
    }

}