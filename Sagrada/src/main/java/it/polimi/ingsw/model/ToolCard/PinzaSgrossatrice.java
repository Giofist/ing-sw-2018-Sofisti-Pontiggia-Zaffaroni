package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DecreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.IncreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PinzaSgrossatriceException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class PinzaSgrossatrice  implements ToolAction {
    static final int ID = 1;
    static final String cardTitle = "Pinza Sgrossatrice";
    static final String description = "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1.\n" +
                                        "Non puoi cambiare un 6 in 1 o un 1 in 6.";



    private int selectedDiceIndex;
    // Operation is set to 0 if the player wants to decrease the value of the dice otherwise is set to 1
    private int operation;

    public PinzaSgrossatrice( int selectedDiceIndex, int operation){

        this.selectedDiceIndex = selectedDiceIndex;
        this.operation = operation;
    }

    @Override
    public void execute (Player player) throws ToolIllegalOperationException {
        try{
            if (this.operation == 0) {  // Decrease selected dice value
                player.getGametable().getRoundDicepool().getDice(this.selectedDiceIndex).decreaseIntensity();
            } else {    // Increase selected dice value
                player.getGametable().getRoundDicepool().getDice(this.selectedDiceIndex).increaseIntensity(); }
        }catch(DecreaseNotAllowedException e){
            throw new PinzaSgrossatriceException(PinzaSgrossatriceException.getMsg()+ e.getMessage());
        }catch (IncreaseNotAllowedException e){
            throw new PinzaSgrossatriceException(PinzaSgrossatriceException.getMsg()+ e.getMessage());
        }
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
    public String getCardTitle(){return cardTitle;}

}
