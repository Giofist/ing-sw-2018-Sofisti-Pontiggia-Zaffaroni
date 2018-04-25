package it.polimi.ingsw.model;

public class PennelloperPastaSalda  implements ToolAction  {
    final static int ID = 6;
    final static String cardTitle = "Pennello per Pasta Salda";
    final static String description = "Dopo aver scelto un dado tira nuovamente quel dado.\n" +
            "Se non puoi piazzarlo, riponilo nella riserva.";
    private Player player;
    private int selectedDiceIndex;

    public PennelloperPastaSalda(Player player, int selectedDiceIndex){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
    }

    @Override

    public void execute () throws IllegalOperationException{

        try {

        } catch (IllegalOperationException e){
            throw e;
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
