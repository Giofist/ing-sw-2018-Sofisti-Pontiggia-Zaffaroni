package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TaglierinaManualeException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

import java.util.List;

// DA TERMINARE
public class TaglierinaManuale  implements ToolAction {
    final static int ID = 12;
    final static String cardTitle = "Taglierina Manuale";
    final static String description = "Muovi fino a due dadi dello stesso colore  di un solo dado sul Tracciato dei Round.\n" +
                                      "Devi rispettare tutte le restrizioni di piazzamento.";

    int oldRow1;
    int oldColumn1;
    int newRow1;
    int newColumn1;
    Dice removedDice;



    public TaglierinaManuale( int oldRow1, int oldColumn1,  int newRow1, int newColumn1) {
        this.oldRow1 = oldRow1;
        this.oldColumn1 = oldColumn1;
        this.newRow1 = newRow1;
        this.newRow1 = newRow1;
        this.newColumn1 = newColumn1;
    }

    @Override
    public void execute(Player player)throws ToolIllegalOperationException {
        try{
            removedDice = player.getScheme().getDice(oldRow1,oldColumn1);
            List<DiceColor> diceColors = player.getGametable().getRoundTrack().allColors();
            if (diceColors.contains(removedDice.getColor())){
                player.getScheme().removeDice(oldRow1,oldColumn1);
                player.getScheme().setDice(removedDice, newRow1,newColumn1,false,false,false);
            }else{
                throw new TaglierinaManualeException("Non c'è nessun dado con lo stesso colore nel TRacciato Round\n");
            }



        }


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
