package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.AlesatorePerLaminadiRameException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerEglomiseException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
//revisionata by pon
public class AlesatoreperLaminadiRame  extends ToolAction {



    public AlesatoreperLaminadiRame(){
        this.cost =1;
        this.ID = 3;
        this.cardTitle = "Alesatore per lamina di rame";
        this.description = "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore.\n" +
                "Devi rispettare tutte le altre restrizioni di piazzamento.";
    }
    private Dice removedDice;

    @Override
    public void execute (Player player, RequestClass requestClass) throws  ToolIllegalOperationException {
        try{
            removedDice = player.getScheme().getDice(requestClass.getOldRow1(), requestClass.getOldColumn1());
            player.getScheme().removeDice(requestClass.getOldRow1(), requestClass.getOldColumn1());
            player.getScheme().setDice(removedDice, requestClass.getNewRow1(), requestClass.getNewColumn1(), false, true, false);
        }catch (Exception e) {
            try {
                player.getScheme().setDice(removedDice, requestClass.getOldRow1(), requestClass.getOldColumn1(), true, false, false);
            } catch (Exception er) {
                //do nothing
            }
            throw new AlesatorePerLaminadiRameException(AlesatorePerLaminadiRameException.getMsg() + e.getMessage());
        }
    }

}
