package it.polimi.ingsw.model;

public class Lathekin  implements ToolAction  {
    final static int ID = 4;
    final static String cardTitle = "Lathekin";
    final static String description = "Muovi esattamente due dadi.\n" +
                                      "Rispetta tutte le restrizioni di piazzamento.";
    private Player player;
    private int operation;

    public Lathekin(Player player, int oldrow1, int oldcolumn1, int newrow1, int newcolumn1, int oldrow2, int oldcolumn2, int newrow2, int newcolumn2, int operation){
        this.player = player;
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
