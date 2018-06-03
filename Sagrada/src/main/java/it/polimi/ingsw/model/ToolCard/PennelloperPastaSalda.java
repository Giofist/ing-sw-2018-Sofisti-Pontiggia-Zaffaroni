package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;

public class PennelloperPastaSalda  extends ToolAction {


    public PennelloperPastaSalda(){
        this.cost =1;
        this.ID =6;
        this.cardTitle = "Pennello per Pasta Salda";
        this.description = "Dopo aver scelto un dado tira nuovamente quel dado.\n" +
                "Se non puoi piazzarlo, riponilo nella riserva.";
    }


    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass)throws ToolIllegalOperationException {
        //ricordarsi sempre di fare gt and remove
        try{
            Dice dice= player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDIceIndex());
            dice.setRandomIntensity();
            if(player.HassetaDicethisturn()){
                throw new ToolIllegalOperationException("non puoi piazzare due dadi nello stesso turno");
            }

        boolean settable = false;
        for (int row =0; row <4; row ++){
            for (int column =0; column <5; column ++){
              try{
                  settable = settable || player.getScheme().SettableHere(dice, row,column,false, false);
              }catch (SchemeCardNotExistantException e){
                  //do nothing
              }
            }
        }

        if(settable){
            player.setDiceforToolCardUse(dice);
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedDIceIndex());
            player.setPlayerState(State.MUSTSETPENNELLOPERPASTASALDASTATE);

        }

        }catch (EmpyDicepoolException e){

        }

    }



}
