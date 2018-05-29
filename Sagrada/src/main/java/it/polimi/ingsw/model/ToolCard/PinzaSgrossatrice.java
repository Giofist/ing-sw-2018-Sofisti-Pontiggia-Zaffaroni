package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DecreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.IncreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PinzaSgrossatriceException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class PinzaSgrossatrice  extends ToolAction {



    public PinzaSgrossatrice(){
        this.cost =1;
        this.ID=1;
        this.cardTitle = "Pinza Sgrossatrice";
        this.description = "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1.\n" +
                "Non puoi cambiare un 6 in 1 o un 1 in 6.";
    }


    @Override
    public void execute (Player player, RequestClass requestClass) throws ToolIllegalOperationException {
        try{
            if (requestClass.getOperationforPinzaSgrossatrice() ==0) {  // Decrease selected dice value
                player.getGametable().getRoundDicepool().getDice(requestClass.getSelectedDIceIndex()).decreaseIntensity();
            } else {    // Increase selected dice value
                player.getGametable().getRoundDicepool().getDice(requestClass.getSelectedDIceIndex()).increaseIntensity(); }
        }catch(DecreaseNotAllowedException e){
            throw new PinzaSgrossatriceException(PinzaSgrossatriceException.getMsg()+ e.getMessage());
        }catch (IncreaseNotAllowedException e){
            throw new PinzaSgrossatriceException(PinzaSgrossatriceException.getMsg()+ e.getMessage());
        }catch (EmpyDicepoolException e){

        }
    }


}
