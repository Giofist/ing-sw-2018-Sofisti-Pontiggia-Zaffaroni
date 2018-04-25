package it.polimi.ingsw.model;

public class TenagliaRotelle  implements ToolAction  {
    final static int ID = 8;
    final static String cardTitle = "Tenaglia a Rotelle";
    final static String description = "Dopo il tuo primo turno scegli immediatamente un altro dado.\n" +
                                      "Salta il secondo turno di questo round.";
    private Player player;
    private int selectedDiceIndex;
    // Operation is set to 0 if the player wants to decrease the value of the dice otherwise is set to 1
    private int operation;

    public TenagliaRotelle(Player player, int selectedDiceIndex, int operation){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
        this.operation = operation;
    }

    @Override

    public void execute () throws IllegalOperationException{

    try {
        player.getGametable().getDicepool().extractDice();
        //skip next round TBD
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
