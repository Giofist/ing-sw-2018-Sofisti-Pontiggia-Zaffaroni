package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.RigaInSugheroException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
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
    public void execute (Player player, RequestClass requestClass) throws ToolIllegalOperationException {
        //ricordarsi di fare get and remove dei dadi, non dimenticare la remove
        try {
            dice = player.getGametable().getRoundDicepool().getDice(requestClass.getSelectedRoundDicepoolDiceIndex());
            player.getGametable().getRoundDicepool().removeDice(requestClass.getSelectedRoundDicepoolDiceIndex());
            boolean thereisadicenearyou = player.getScheme().ThereisaDicenearYou(requestClass.getNewRow1(),requestClass.getNewColumn1());
            if (thereisadicenearyou) {
                throw new RigaInSugheroException("Non puoi mettere un dado se ce n'è uno vicino!\n");
            } else player.getScheme().setDice(dice,requestClass.getNewRow1(),requestClass.getNewColumn1(), false, false, true);

        }catch (Exception e){
            try{
                player.getGametable().getRoundDicepool().addDice(requestClass.getSelectedRoundDicepoolDiceIndex(),dice);
            }catch(Exception er){
                //do nothing
            }
            throw new RigaInSugheroException(RigaInSugheroException.getMsg()+ e.getMessage());
        }


        //not implemented yet
    }


}
