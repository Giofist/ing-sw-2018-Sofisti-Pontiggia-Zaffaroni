package it.polimi.ingsw.model;

public class AlesatoreperLaminadiRame  implements ToolAction  {
    final static int ID = 3;
    final static String cardTitle = "Alesatore per lamina di rame";
    final static String description = "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore.\n" +
                                      "Devi rispettare tutte le altre restrizioni di piazzamento.";
    private Player player;
    private int selectedDiceIndex;
    private int operation;

    public AlesatoreperLaminadiRame(Player player, int selectedDiceIndex){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
        this.operation = operation;
    }

    @Override

    public void execute () throws IllegalOperationException{

    try {
        //implement here
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
