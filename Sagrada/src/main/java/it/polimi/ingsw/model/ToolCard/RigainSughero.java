package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.RigaInSugheroException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.rmi.RemoteException;

//revisionata by pon
public class RigainSughero  extends ToolAction {
    private Dice dice;
    public RigainSughero(){
        this.cost = 1;
        this.ID=9;
        this.cardTitle = "Riga in Sughero";
        this.description = "Dopo aver scelto un dado, piazzalo in una casella che non sia adiacente a un altro dado.\n" +
                "Devi rispettare tutte le restrizioni di piazzamento.";
    }


    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        //ricordarsi di fare get and remove dei dadi, non dimenticare la remove
        try {
            if (player.HassetaDicethisturn()) {
                throw new ToolIllegalOperationException("non puoi piazzare due dadi nello stesso turno");
            }
            dice = player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedRoundDicepoolDiceIndex());
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedRoundDicepoolDiceIndex());
            boolean thereisadicenearyou = player.getScheme().ThereisaDicenearYou(toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1());
            if (thereisadicenearyou) {
                throw new RigaInSugheroException("Non puoi mettere un dado se ce n'è uno vicino!\n");
            } else
                player.getScheme().setDice(dice, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false, false, true);
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)) {
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            } else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(RemoteException e){
            player.getTurn().countDown();
        }catch (Exception e){
            try{
                player.getGametable().getRoundDicepool().addDice(toolRequestClass.getSelectedRoundDicepoolDiceIndex(),dice);
            }catch(Exception er){
                //do nothing
            }
            throw new RigaInSugheroException(RigaInSugheroException.getMsg()+ e.getMessage());
        }


        //not implemented yet
    }


}
