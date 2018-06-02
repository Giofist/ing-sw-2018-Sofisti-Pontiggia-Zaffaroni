package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.PlayerPackage.Player;

public class TamponeDiamantato extends ToolAction {
    public TamponeDiamantato(){
        this.cost =1;
        this.ID =10;

        this.cardTitle = "Tampone Diamantato";
        this.description = "Dopo aver scelto un dado, giralo sulla faccia opposta.\n" +
                "6 diventa 1, 5 diventa 2, 4 diventa 3 ecc.";

    }
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) {
        try{
            player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDIceIndex()).setOppositeIntensity();
        }catch(EmpyDicepoolException e){

        }
    }


}
