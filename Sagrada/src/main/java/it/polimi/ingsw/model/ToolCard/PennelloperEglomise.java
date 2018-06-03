package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerEglomiseException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;

public class PennelloperEglomise extends ToolAction {

    private Dice removedDice;
    public PennelloperEglomise(){
        this.cost =1;
        this.ID=2;
        this.cardTitle = "Pennello per Eglomise";
        this.description = "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore.\n" +
                "Devi rispettare tutte le altre restrizioni di piazzamento.";
    }

    @Override

    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException{
        try{
             removedDice = player.getScheme().getDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().removeDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().setDice(removedDice, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), true, false, false);
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(Exception e) {
            try{
                player.getScheme().setDice(removedDice, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(), true, false, false);
            }catch(Exception er){
                //do nothing, sorry!
            }
            throw new PennelloPerEglomiseException(PennelloPerEglomiseException.getMsg() + e.getMessage());
        }
    }
}
