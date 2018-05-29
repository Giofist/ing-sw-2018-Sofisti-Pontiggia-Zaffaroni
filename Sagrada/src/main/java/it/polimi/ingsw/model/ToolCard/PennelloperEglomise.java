package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerEglomiseException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class PennelloperEglomise  implements ToolAction {
    final static int ID = 2;
    final static String cardTitle = "Pennello per Eglomise";
    final static String description = "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore.\n" +
                                      "Devi rispettare tutte le altre restrizioni di piazzamento.";


    private Dice removedDice;

    @Override

    public void execute (Player player, RequestClass requestClass) throws ToolIllegalOperationException{
        try{
             removedDice = player.getScheme().getDice(requestClass.getOldRow1(), requestClass.getOldColumn1());
            player.getScheme().removeDice(requestClass.getOldRow1(), requestClass.getOldColumn1());
            player.getScheme().setDice(removedDice, requestClass.getNewRow1(), requestClass.getNewColumn1(), true, false, false);

        }catch(Exception e) {
            try{
                player.getScheme().setDice(removedDice, requestClass.getOldRow1(), requestClass.getOldColumn1(), true, false, false);
            }catch(Exception er){
                //do nothing, sorry!
            }
            throw new PennelloPerEglomiseException(PennelloPerEglomiseException.getMsg() + e.getMessage());
        }
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
