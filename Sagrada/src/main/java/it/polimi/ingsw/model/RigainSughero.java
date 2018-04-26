package it.polimi.ingsw.model;

public class RigainSughero  implements ToolAction  {
    final static int ID = 9;
    final static String cardTitle = "Riga in Sughero";
    final static String description = "Dopo aver scelto un dado, piazzalo in una casella che non sia adiacente a un altro dado.\n" +
                                      "Devi rispettare tutte le restrizioni di piazzamento.";
    private Player player;
    private int selectedDiceIndex;
    private int operation;

    public RigainSughero(Player player, int selectedDiceIndex, int operation){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
        this.operation = operation;
    }

    @Override

    public void execute () throws IllegalOperationException{

    try {
//IMPLEMENT HERE
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
