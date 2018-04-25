package it.polimi.ingsw.model;

public class Lathekin  implements ToolAction  {
    final static int ID = 4;
    final static String cardTitle = "Lathekin";
    final static String description = "Muovi esattamente due dadi.\n" +
                                      "Rispetta tutte le restrizioni di piazzamento.";
    private Player player;
    private int selectedDiceIndex;
    private int operation;

    public Lathekin(Player player, int selectedDiceIndex, int operation){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
        this.operation = operation;
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
