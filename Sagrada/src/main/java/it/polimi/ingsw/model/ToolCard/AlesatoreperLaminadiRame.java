package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.AlesatorePerLaminadiRameException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.rmi.RemoteException;

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
    public void execute (Player player, ToolRequestClass toolRequestClass) throws  ToolIllegalOperationException {
        try{
            removedDice = player.getScheme().getDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().removeDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().setDice(removedDice, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false, true, false);

            // parte relativa alla stato
            try{
                if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                    player.setPlayerState(State.MUSTPASSTURNSTATE);
                }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
            }catch (RemoteException e){
                player.getTurn().countDown();
            }

        }catch (Exception e) {
            try {
                player.getScheme().setDice(removedDice, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(), true, false, false);
            } catch (Exception er) {
                //do nothing
            }
            throw new AlesatorePerLaminadiRameException(AlesatorePerLaminadiRameException.getMsg() + e.getMessage());
        }
    }

}
