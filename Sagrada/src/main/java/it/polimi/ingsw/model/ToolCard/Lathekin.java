package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.LathekinException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
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
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        try{
            removedDice1 = player.getScheme().getDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            removedDice2 = player.getScheme().getDice(toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2());
            player.getScheme().removeDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().removeDice(toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2());
            player.getScheme().setDice(removedDice1, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false, false, false);
            player.getScheme().setDice(removedDice2, toolRequestClass.getNewRow2(), toolRequestClass.getNewColumn2(),false,false, false);


        }catch( Exception e){
            try{
                player.getScheme().setDice(removedDice1, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(), false, false, false);
                player.getScheme().setDice(removedDice2, toolRequestClass.getNewRow2(), toolRequestClass.getNewColumn2(),false,false, false);
            }catch(Exception err){
                //do nothing
            }
            throw new LathekinException(LathekinException.getMsg() + e.getMessage());
        }

    }


}
