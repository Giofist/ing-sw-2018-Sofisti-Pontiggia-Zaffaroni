package it.polimi.ingsw.model.ToolCard;

<<<<<<< HEAD
=======
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.*;
>>>>>>> 0b9e2f6ef25f97857c8719fe848c7c3bd461c939
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class RigainSughero  implements ToolAction {
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

    public void execute () throws ToolIllegalOperationException {
        //not implemented yet
    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public String getCardTitle(){return cardTitle;}

    @Override
    public String getDescription(){
        return description;
    }
}
