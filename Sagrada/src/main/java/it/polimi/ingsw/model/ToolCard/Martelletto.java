package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.MartellettoException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class Martelletto  extends ToolAction {
    public Martelletto(){
        this.cost =1;
        this.cardTitle = "Martelletto";
        this.description = "Tira nuovamente tutti i dadi della riserva.\n" +
                "Questa carta può essere usata solo durante il tuo secondo turno, prima di scegliere il secondo dado.";

    }

    @Override

    public void execute (Player player, RequestClass requestClass)throws ToolIllegalOperationException{
        //se non lancia eccezioni ci siamo dimenticati di qualcosa
        try{
            if (player.getTurn().getTurnID() == 1){
                throw new MartellettoException("non puo igiocare Martelletto nel primo turno");
            }
            for(int i=0; i<player.getGametable().getRoundDicepool().getDicePoolSize();i++)
                player.getGametable().getRoundDicepool().getDice(i).setRandomIntensity();
        }catch (EmpyDicepoolException e){

        }

    }


}
