package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class PennelloperPastaSalda  implements ToolAction {
    final static int ID = 6;
    final static String cardTitle = "Pennello per Pasta Salda";
    final static String description = "Dopo aver scelto un dado tira nuovamente quel dado.\n" +
            "Se non puoi piazzarlo, riponilo nella riserva.";

    private int selectedDiceIndex;

    public PennelloperPastaSalda( int selectedDiceIndex){
        this.selectedDiceIndex = selectedDiceIndex;
    }

    @Override

    public void execute (Player player) {
        //ricordarsi sempre di fare gt and remove
        Dice dice= player.getGametable().getRoundDicepool().getDice(this.selectedDiceIndex);
        player.getGametable().getRoundDicepool().getDice(this.selectedDiceIndex).setRandomIntensity();
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

        player.setMustsetdice(settable);

    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public String getCardTitle(){ return cardTitle;};

}
