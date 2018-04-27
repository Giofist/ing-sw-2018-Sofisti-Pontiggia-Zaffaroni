package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class TaglierinaManuale  implements ToolAction {
    final static int ID = 12;
    final static String cardTitle = "Taglierina Manuale";
    final static String description = "Muovi fino a due dadi dello stesso colore di un solo dado di un solo dado sul Tracciato dei Round.\n" +
                                      "Devi rispettare tutte le restrizioni di piazzamento.";
    private Player player;
    private int selectedDiceIndex;
    private int operation;

    public TaglierinaManuale(Player player, int selectedDiceIndex, int operation){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
        this.operation = operation;
    }

    @Override

    public void execute() {

//IMPLEMENT HERE

    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public String getCardTitle() {
        return cardTitle;
    }

    @Override
    public String getDescription(){
        return description;
    }
}
