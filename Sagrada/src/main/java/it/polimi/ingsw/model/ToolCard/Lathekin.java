package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.LathekinException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
//revisionata by pon
public class Lathekin  extends ToolAction {


    public Lathekin(){
        this.cost =1;
        this.ID =4;
        this.cardTitle = "Lathekin";
        this.description = "Muovi esattamente due dadi.\n" +
                "Rispetta tutte le restrizioni di piazzamento.";
    }
    Dice removedDice1, removedDice2;

    @Override
    public void execute (Player player, RequestClass requestClass) throws ToolIllegalOperationException {
        try{
            removedDice1 = player.getScheme().getDice(requestClass.getOldRow1(), requestClass.getOldColumn1());
            removedDice2 = player.getScheme().getDice(requestClass.getOldRow2(), requestClass.getOldColumn2());
            player.getScheme().removeDice(requestClass.getOldRow1(), requestClass.getOldColumn1());
            player.getScheme().removeDice(requestClass.getOldRow2(), requestClass.getOldColumn2());
            player.getScheme().setDice(removedDice1, requestClass.getNewRow1(), requestClass.getNewColumn1(), false, false, false);
            player.getScheme().setDice(removedDice2, requestClass.getNewRow2(), requestClass.getNewColumn2(),false,false, false);


        }catch( Exception e){
            try{
                player.getScheme().setDice(removedDice1, requestClass.getOldRow1(), requestClass.getOldColumn1(), false, false, false);
                player.getScheme().setDice(removedDice2, requestClass.getNewRow2(), requestClass.getNewColumn2(),false,false, false);
            }catch(Exception err){
                //do nothing
            }
            throw new LathekinException(LathekinException.getMsg() + e.getMessage());
        }

    }


}
