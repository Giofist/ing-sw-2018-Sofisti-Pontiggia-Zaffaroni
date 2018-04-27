package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class AlesatoreperLaminadiRame  implements ToolAction {
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

    public void execute () throws ToolIllegalOperationException {

    try {
        //implement here
        // Remove the following line when implementing
        throw new ToolIllegalOperationException();
    } catch  (ToolIllegalOperationException e){
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

    @Override
    public String getCardTitle() { return cardTitle; }
}
