package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class PinzaSgrossatriceTest {
    private PinzaSgrossatrice pinzaSgrossatrice;
    private Player mockPlayer;
    private Gametable mockGametable;
    private int selectedDiceIndex;
    private int operation;
    private int getIntensityReturnValue;
    private Dice mockDice;

    @Before
    public void before() {
        this.mockPlayer = mock(Player.class);
        this.mockGametable = mock(Gametable.class);
        this.mockDice = mock(Dice.class);

        when(mockPlayer.getGametable()).thenReturn(mockGametable);
        when(mockGametable.getRoundDice(selectedDiceIndex)).thenReturn(mockDice);
        when(mockDice.getIntensity()).thenReturn(getIntensityReturnValue);
    }

    @Test
    public void testExecute() throws IllegalOperationException {

        // Test for normal operation
        try {
            this.selectedDiceIndex = 3;
            this.operation = 0;
            this.getIntensityReturnValue = 3;
            this.pinzaSgrossatrice = new PinzaSgrossatrice(this.mockPlayer, this.selectedDiceIndex, this.operation);
            this.pinzaSgrossatrice.execute();
        } catch (IllegalOperationException e){
            throw e;
        }


        // Test for illegal operation
        try {
            this.selectedDiceIndex = 5;
            this.operation = 1;
            this.getIntensityReturnValue = 6;
            this.pinzaSgrossatrice = new PinzaSgrossatrice(this.mockPlayer, this.selectedDiceIndex, this.operation);
            this.pinzaSgrossatrice.execute();
        } catch (IllegalOperationException e){
            throw e;
        }

    }
}