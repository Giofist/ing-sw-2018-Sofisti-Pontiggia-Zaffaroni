package it.polimi.ingsw.model;

public class PennelloperEglomise  implements ToolAction  {
    final static int ID = 2;
    final static String cardTitle = "Pennello per Eglomise";
    final static String description = "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore.\n" +
                                      "Devi rispettare tutte le altre restrizioni di piazzamento.";
    private Player player;
    private int selectedDiceIndex;

    public PennelloperEglomise(Player player, int selectedDiceIndex, int operation){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
    }

    @Override

    public void execute () throws IllegalOperationException{

    try {

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
