package it.polimi.ingsw.model;

public class PinzaSgrossatrice implements ToolAction {
    final static int ID = 1;
    final static String cardTitle = "Pinza Sgrossatrice";
    final static String description = "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1.\n" +
                                      "Non puoi cambiare un 6 in 1 o un 1 in 6.";
    private Player player;
    private int selectedDiceIndex;
    // Operation is set to 0 if the player wants to decrease the value of the dice otherwise is set to 1
    private int operation;

    public PinzaSgrossatrice(Player player, int selectedDiceIndex, int operation){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
        this.operation = operation;
    }

    @Override

    public boolean execute() {
        // Forbidden Moves in case the player wants to increase a dice with value 6 or decrease one with value 1
        if ((player.getGametable().getRoundDice(selectedDiceIndex).getIntensity() == 1 && this.operation == 0)||
            (player.getGametable().getRoundDice(selectedDiceIndex).getIntensity() == 6 && this.operation == 1)){
            return false;
        }

        if (this.operation == 0) {  // Decrease selected dice value
            player.getGametable().getRoundDice(this.selectedDiceIndex).decreaseIntensity();
        } else {    // Increase selected dice value
            player.getGametable().getRoundDice(this.selectedDiceIndex).increaseIntensity();
        }

        return true;
    }
}
