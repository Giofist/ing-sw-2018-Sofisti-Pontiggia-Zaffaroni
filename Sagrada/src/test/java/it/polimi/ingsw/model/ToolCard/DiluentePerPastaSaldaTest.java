package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.ClientView.Setter;
import it.polimi.ingsw.ServerController.ClientHandler;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
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


// MANCA PARTE 2

public class DiluentePerPastaSaldaTest {

    private DiluenteperPastaSalda toolCard;
    private Player player;
    private Dice mockDice;
    private SchemeCard schemeCard;
    private ToolRequestClass toolRequestClass;
    private Match mockMatch;
    private Gametable mockGametable;
    private DicePool roundDicePool;
    private Turn mockTurn;

    @Before
    public void before() throws IOException, MapConstrainReadingException, CardIdNotAllowedException, SchemeCardNotExistantException, OutOfMatrixException, TileConstrainException {
        // Tested class
        toolCard = new DiluenteperPastaSalda();

        // Required classes for game interaction
        player = new Player();
        mockDice = mock(Dice.class);
        mockMatch = mock(Match.class);
        mockGametable = mock(Gametable.class);
        schemeCard = new SchemeCard(3);
        toolRequestClass = new ToolRequestClass();
        roundDicePool = new DicePool();
        mockTurn = mock(Turn.class);

        // Scheme Card setup
        schemeCard.setTwinCard(new SchemeCard(4));

        // Mock behaviours
        doNothing().when(mockMatch).countDown();
        when(mockMatch.getGametable()).thenReturn(mockGametable);
        when(mockGametable.getRoundDicepool()).thenReturn(roundDicePool);
        when(mockDice.getColor()).thenReturn(DiceColor.VIOLET);
        when(mockDice.getIntensity()).thenReturn(2);


        // Player setup
        player.addExtractedSchemeCard(schemeCard);
        player.setMatch(mockMatch);
        player.setPlayerState(State.STARTTURNSTATE);
        player.setScheme(3);
        player.setTurn(mockTurn);
    }

    @Test
    public void executeEverythingOk() throws ToolIllegalOperationException {

        // Part 1
        DicePool roundDicePool = new DicePool();
        DicePool dicePool = new DicePool();
        mockGametable = mock(Gametable.class);

        when(mockMatch.getGametable()).thenReturn(mockGametable);
        when(mockGametable.getRoundDicepool()).thenReturn(roundDicePool);
        when(mockGametable.getDicepool()).thenReturn(dicePool);

        roundDicePool.addDice(mockDice);
        dicePool.addDice(new Dice(DiceColor.RED));

        assertEquals(1, roundDicePool.getDicePoolSize());

        toolRequestClass.setSelectedDiceIndex(0);
        toolCard.execute(player, toolRequestClass);

        assertEquals(0, roundDicePool.getDicePoolSize());
        assertEquals(State.MUSTSSETDILUENTEPERPASTASALDASTATE, player.getPlayerState().getState());

        // Part2


    }


    @Test
    public void executeWrongIndexException() throws DicepoolIndexException {
        mockGametable = mock(Gametable.class);
        when(mockMatch.getGametable()).thenReturn(mockGametable);
        DicePool roundDicePool = new DicePool();
        //DicePool dicePool = new DicePool();
        roundDicePool.addDice(mockDice);
        //dicePool.addDice(new Dice(DiceColor.RED));
        when(mockGametable.getRoundDicepool()).thenReturn(roundDicePool);
        //when(mockGametable.getDicepool()).thenReturn(dicePool);


        assertEquals(1, roundDicePool.getDicePoolSize());
        toolRequestClass.setSelectedDiceIndex(5);

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

    // Getter test
    @Test
    public void getIdTest() {
        assertEquals(11, toolCard.getID());
    }

}
