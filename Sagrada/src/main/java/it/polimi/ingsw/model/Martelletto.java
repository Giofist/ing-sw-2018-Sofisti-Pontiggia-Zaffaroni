package it.polimi.ingsw.model;

public class Martelletto  implements ToolAction  {
    final static int ID = 7;
    final static String cardTitle = "Martelletto";
    final static String description = "Tira nuovamente tutti i dadi della riserva.\n" +
                                      "Questa carta può essere usata solo durante il tuo secondo turno, prima di scegliere il secondo dado.";
    private Player player;
    private int selectedDiceIndex;
    private int operation;

    public Martelletto(Player player, int selectedDiceIndex, int operation){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
        this.operation = operation;
    }

    @Override

    public void execute () throws IllegalOperationException{

    try {
        //player.getGametable().getReserve().scramble;// ti be implemented
//TO BE IMPLEMENTED
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
