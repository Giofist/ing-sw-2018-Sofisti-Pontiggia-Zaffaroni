package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.DiluentePerPastaSaldaException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;

import java.io.Serializable;

//l'ho lasciata stare perchè ho fatto altro
//ma l'idea che ho avuto è dividere l'esecuioni in due DiluentiPerPastaCalda, perchè
//il giocatore deve prima vedere quale colore abbia il dado pescato per poi settarne la sfumatura e piazzarlo, quindi...
//boh in realtà mi sembrava un problema ai tempi, adesso non tantissimo
//beh se qualcuno vuole sistemare prego
//(pon)
public class DiluenteperPastaSalda  extends ToolAction implements Serializable {


    public DiluenteperPastaSalda(){
        this.cost =1;
        this.ID = 11;
    }
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException{

        //removes a dice e puts it into the dicepool, but before we need to remember its color
        try{
            if(player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                throw new DiluentePerPastaSaldaException("7.1");
            }
            DiceColor color = player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex()).getColor();
            player.getGametable().getDicepool().insertDice(color);
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedDiceIndex());

            //poi pescane uno
            player.setDiceforToolCardUse(player.getGametable().getDicepool().extractDice());
            player.setPlayerState(State.MUSTSSETDILUENTEPERPASTASALDASTATE);

        }catch(DicepoolIndexException e){
            throw new DiluentePerPastaSaldaException();
        } catch(IndexOutOfBoundsException e) {
            throw new DiluentePerPastaSaldaException();
        }
    }
}
