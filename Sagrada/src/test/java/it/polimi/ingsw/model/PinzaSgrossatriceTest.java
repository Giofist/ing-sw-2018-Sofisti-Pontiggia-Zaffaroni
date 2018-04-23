package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PinzaSgrossatriceTest {
    private PinzaSgrossatrice pinzaSgrossatrice;
    private int selectedDiceIndex = 1;
    private int operation = 0;

    @Before
    public void before() {
        Player player = mock(Player.class);
        when(player.getGametable().getRoundDice(selectedDiceIndex).getIntensity()).thenReturn(1);

        this.pinzaSgrossatrice = new PinzaSgrossatrice(player, this.selectedDiceIndex, this.operation);
    }

    @Test
    public void testExecute() {

    }
}