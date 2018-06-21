package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;


// COMPLETE, maybe some problem in Setter implementation

public class PinzaSgrossatriceTest {

    private PinzaSgrossatrice toolCard;
    private ToolRequestClass toolRequestClass;
    private Player player;
    private Match mockMatch;
    private Turn mockTurn;
    private DicePool roundDicePool;
    private Gametable mockGameTable;

    @Before
    public void before() throws RemoteException {
        toolCard = new PinzaSgrossatrice();
        toolRequestClass = new ToolRequestClass();

        roundDicePool = new DicePool();
        roundDicePool.addDice(new Dice(DiceColor.RED));
        roundDicePool.addDice(new Dice(DiceColor.YELLOW));

        player = new Player();
        mockMatch = mock(Match.class);
        mockGameTable = mock(Gametable.class);
        mockTurn = mock(Turn.class);
        player.setMatch(mockMatch);
        player.setTurn(mockTurn);
        player.setPlayerState(State.STARTTURNSTATE);

        when(mockMatch.getGametable()).thenReturn(mockGameTable);
        when(mockGameTable.getRoundDicepool()).thenReturn(roundDicePool);
    }



    // This test will test the situation in which we try to decrement the intensity of a Dice with value 1
    @Test
    public void testExecuteOperation0DecreaseException () throws EmpyDicepoolException, ToolIllegalOperationException {
        // Here we prepare the PinzaSgrossatrice object with 0 as Dice index to change and we try to decrease its intensity
        // in a situation where is equal to 1

        player.getMatch().getGametable().getRoundDicepool().getDice(0).setIntensity(1);
        toolRequestClass.setSelectedRoundDicepoolDiceIndex(0);
        toolRequestClass.setOperationforPinzaSgrossatrice(0);

        try {
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        // We don't want to end here
        assertFalse(0==0);

    }


    // This test will test the situation in which we try to increment the intensity of a Dice with value 6
    @Test
    public void testExecuteOperation1 () throws EmpyDicepoolException {
        // Here we prepare the PinzaSgrossatrice object with 0 as Dice index to change and we try to increase its intensity
        // in a situation where is equal to 6
        player.getMatch().getGametable().getRoundDicepool().getDice(0).setIntensity(6);
        toolRequestClass.setSelectedRoundDicepoolDiceIndex(0);
        toolRequestClass.setOperationforPinzaSgrossatrice(1);

        try {
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        // We don't want to end here
        assertFalse(0==0);

    }


    @Test
    public void executeOK () throws EmpyDicepoolException, ToolIllegalOperationException {
        player.getMatch().getGametable().getRoundDicepool().getDice(0).setIntensity(2);
        toolRequestClass.setSelectedRoundDicepoolDiceIndex(0);
        toolRequestClass.setOperationforPinzaSgrossatrice(1);


        toolCard.execute(player, toolRequestClass);
        assertEquals(3, player.getMatch().getGametable().getRoundDicepool().getDice(0).getIntensity());
        assertEquals(State.HASUSEDATOOLCARDACTIONSTATE, player.getPlayerState().getState());

    }


    @Test
    public void testGetId() {
        assertEquals(1, toolCard.getID());
    }

    @Test
    public void testGetCardTitle() {
        assertEquals("Pinza Sgrossatrice", toolCard.getCardTitle());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1.\n" +
                              "Non puoi cambiare un 6 in 1 o un 1 in 6.", toolCard.getDescription());
    }

}
