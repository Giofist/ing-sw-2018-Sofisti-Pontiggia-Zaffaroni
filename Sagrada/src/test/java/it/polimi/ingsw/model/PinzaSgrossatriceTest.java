package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PinzaSgrossatriceTest {
    private PinzaSgrossatrice pinzaSgrossatrice;
    private Player player;
    private int selectedDiceIndex;
    private int operation;
    private int getIntensityReturnValue;

    @Before
    public void before() {
        this.player = mock(Player.class);
        when(player.getGametable().getRoundDice(selectedDiceIndex).getIntensity()).thenReturn(getIntensityReturnValue);
    }

    @Test
    public void testExecute() throws IllegalOperationException {

        // Test for normal operation
        try {
            this.selectedDiceIndex = 3;
            this.operation = 0;
            this.getIntensityReturnValue = 3;
            this.pinzaSgrossatrice = new PinzaSgrossatrice(player, this.selectedDiceIndex, this.operation);
            this.pinzaSgrossatrice.execute();
        } catch (IllegalOperationException e){
            throw e;
        }


        // Test for illegal operation
        try {
            this.selectedDiceIndex = 5;
            this.operation = 1;
            this.getIntensityReturnValue = 6;
            this.pinzaSgrossatrice = new PinzaSgrossatrice(player, this.selectedDiceIndex, this.operation);
            this.pinzaSgrossatrice.execute();
        } catch (IllegalOperationException e){
            throw e;
        }

    }
}