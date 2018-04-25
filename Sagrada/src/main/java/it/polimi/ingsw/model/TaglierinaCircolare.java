package it.polimi.ingsw.model;

public class TaglierinaCircolare  implements ToolAction  {
    final static int ID = 5;
    final static String cardTitle = "Taglierina circolare";
    final static String description = "Dopo aver scelto un dado, scambia quel dado con un dado sul Tracciato Round.\n";
    private Player player;
    private int selectedDiceIndex;
    private int operation;

    public TaglierinaCircolare(Player player, int selectedDiceIndex, int operation){
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
