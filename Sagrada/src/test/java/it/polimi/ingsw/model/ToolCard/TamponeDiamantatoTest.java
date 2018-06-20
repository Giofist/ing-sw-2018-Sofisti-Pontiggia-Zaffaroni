package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DicePool;
import it.polimi.ingsw.model.Gametable;
import it.polimi.ingsw.model.PlayerPackage.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
     In TamponeDiamantato bisogna gestire correttamente il caso in  cui l'utente spefichi un indice non valido per il Dice
     che vuole invertire, al momento la classe TamponeDiamantato non lancia e non gestisce alcuna eccezione di questo tipo
 */

public class TamponeDiamantatoTest {

    private TamponeDiamantato toolCard;
    private Player mockPlayer;
    private Gametable mockGameTable;
    private DicePool mockRoundDicePool;
    private Dice mockDice;

    @Before
    public void before() {
        toolCard = new TamponeDiamantato();
        mockPlayer = mock(Player.class);
        mockGameTable = mock(Gametable.class);
        mockRoundDicePool = mock(DicePool.class);
        mockDice = mock(Dice.class);

        when(mockPlayer.getGametable()).thenReturn(mockGameTable);
        when(mockGameTable.getDicepool()).thenReturn(mockRoundDicePool);
    }

    // This method will test when the specified index is out of range
    @Test
    public void execute() {
    }


    // Minor tests
    @Test
    public void getIDTest() {
        assertEquals(10, toolCard.getID());
    }

    @Test
    public void getCardTitleTest() {
        assertEquals("Tampone Diamantato", toolCard.getCardTitle());
    }

    @Test
    public void getCardDescriptionTest() {
        assertEquals("Dopo aver scelto un dado, giralo sulla faccia opposta.\n" +
                "6 diventa 1, 5 diventa 2, 4 diventa 3 ecc.", toolCard.getDescription());
    }
}