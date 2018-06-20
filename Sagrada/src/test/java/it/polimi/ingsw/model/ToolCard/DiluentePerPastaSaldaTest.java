package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.CardIdNotAllowedException;
import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.DiluentePerPastaSalda2Exception;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiluentePerPastaSaldaTest {

    private DiluenteperPastaSalda toolCard;
    private Player player;
    private Dice mockDice;
    private SchemeCard schemeCard;
    private ToolRequestClass toolRequestClass;
    private Match mockMatch;
    private Gametable mockGametable;

    @Before
    public void before() throws SchemeCardNotExistantException, IOException, MapConstrainReadingException, CardIdNotAllowedException {
        toolCard = new DiluenteperPastaSalda();
        player = new Player();
        mockDice = mock(Dice.class);
        mockMatch = mock(Match.class);
        doNothing().when(mockMatch).countDown();
        schemeCard = new SchemeCard(3);
        schemeCard.setTwinCard(new SchemeCard(4));
        toolRequestClass = new ToolRequestClass();

        player.addExtractedSchemeCard(schemeCard);
        player.setMatch(mockMatch);
        player.setPlayerState(State.STARTTURNSTATE);
        player.setScheme(3);


        when(mockDice.getColor()).thenReturn(DiceColor.VIOLET);
        when(mockDice.getIntensity()).thenReturn(2);

    }

    @Test
    public void executeEverythingOk() throws ToolIllegalOperationException {
        DicePool roundDicePool = new DicePool();
        DicePool dicePool = new DicePool();
        mockGametable = mock(Gametable.class);

        when(mockMatch.getGametable()).thenReturn(mockGametable);
        when(mockGametable.getRoundDicepool()).thenReturn(roundDicePool);
        when(mockGametable.getDicepool()).thenReturn(dicePool);

        roundDicePool.addDice(mockDice);
        dicePool.addDice(new Dice(DiceColor.RED));

        assertEquals(1, roundDicePool.getDicePoolSize());

        toolRequestClass.setSelectedDIceIndex(0);
        toolCard.execute(player, toolRequestClass);

        assertEquals(0, roundDicePool.getDicePoolSize());
        assertEquals(State.MUSTSSETDILUENTEPERPASTASALDASTATE, player.getPlayerState().getState());
    }


    @Test
    public void executeWrongIndexException() throws ToolIllegalOperationException, EmpyDicepoolException {
        mockGametable = mock(Gametable.class);
        when(mockMatch.getGametable()).thenReturn(mockGametable);
        DicePool roundDicePool = new DicePool();
        //DicePool dicePool = new DicePool();
        roundDicePool.addDice(mockDice);
        //dicePool.addDice(new Dice(DiceColor.RED));
        when(mockGametable.getRoundDicepool()).thenReturn(roundDicePool);
        //when(mockGametable.getDicepool()).thenReturn(dicePool);


        assertEquals(1, roundDicePool.getDicePoolSize());
        toolRequestClass.setSelectedDIceIndex(5);

        try {
            toolCard.execute(player, toolRequestClass);
        } catch (ToolIllegalOperationException e) {
            assertEquals(1, roundDicePool.getDicePoolSize());
            assertTrue(player.getMatch().getGametable().getRoundDicepool().getDice(0) == mockDice);
            assertEquals(State.STARTTURNSTATE, player.getPlayerState().getState());
            return;
        }

        // We don't want to end here
        assertEquals(0,1);
    }

}
