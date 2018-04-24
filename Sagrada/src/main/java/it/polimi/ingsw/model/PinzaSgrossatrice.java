package it.polimi.ingsw.model;

public class PinzaSgrossatrice  implements ToolAction  {
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

    public void execute () throws IllegalOperationException{

    try {
        if (this.operation == 0) {  // Decrease selected dice value
            player.getGametable().getRoundDice(this.selectedDiceIndex).decreaseIntensity();
        } else {    // Increase selected dice value
            player.getGametable().getRoundDice(this.selectedDiceIndex).increaseIntensity();

        }
    } catch  (IllegalOperationException e){
        throw e;
    }

    }

    @Override
    public int getID(){
        return ID;
    }

    public String getDescription(){
        return description;
    }
}
