package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Player;

public class TamponeDiamantato extends ToolAction {
    public TamponeDiamantato(){
        this.cost =1;

        this.cardTitle = "Tampone Diamantato";
        this.description = "Dopo aver scelto un dado, giralo sulla faccia opposta.\n" +
                "6 diventa 1, 5 diventa 2, 4 diventa 3 ecc.";

    }
    @Override
    public void execute (Player player, RequestClass requestClass) {
        try{
            player.getGametable().getRoundDicepool().getDice(requestClass.getSelectedDIceIndex()).setOppositeIntensity();
        }catch(EmpyDicepoolException e){

        }
    }


}
