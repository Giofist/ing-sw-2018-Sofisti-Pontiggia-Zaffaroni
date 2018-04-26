package it.polimi.ingsw.model;

public class TamponeDiamantato implements ToolAction  {
    final static int ID = 10;
    final static String cardTitle = "Tampone Diamantato";
    final static String description = "Dopo aver scelto un dado, giralo sulla faccia opposta.\n" +
                                      "6 diventa 1, 5 diventa 2, 4 diventa 3 ecc.";
    private Player player;
    private int selectedDiceIndex;

    public TamponeDiamantato(Player player, int selectedDiceIndex){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
    }

    @Override

    public void execute () throws IllegalOperationException{

    try {
        player.getGametable().getRoundDice(this.selectedDiceIndex).setOppositeIntensity();
    } catch  (IllegalOperationException e){
        throw e;
        }
    }

    @Override
    public int getID(){
        return ID;
    }
    @Override
    public String getDescription(){
        return description;
    }
}
