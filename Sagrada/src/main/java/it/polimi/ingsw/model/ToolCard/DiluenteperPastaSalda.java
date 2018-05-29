package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

//l'ho lasciata stare perchè ho fatto altro
//ma l'idea che ho avuto è dividere l'esecuioni in due DiluentiPerPastaCalda, perchè
//il giocatore deve prima vedere quale colore abbia il dado pescato per poi settarne la sfumatura e piazzarlo, quindi...
//boh in realtà mi sembrava un problema ai tempi, adesso non tantissimo
//beh se qualcuno vuole sistemare prego
//(pon)
public class DiluenteperPastaSalda  implements ToolAction {
    final static int ID = 11;
    final static String cardTitle = "Diluente per Pasta Salda";
    final static String description = "Dopo aver scelto un dado, riponilo nel Sacchetto, poi pescane uno dal sacchetto.\n" +
                                      "Scegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento.";

    private int selectedDiceIndex;

    public DiluenteperPastaSalda( int selectedDiceIndex){
        this.selectedDiceIndex =selectedDiceIndex;
    }

    @Override
    public void execute (Player player) throws ToolIllegalOperationException{

        //removes a dice e puts it into the dicepool, but before we need to remember its color
        try{
            DiceColor color = player.getGametable().getRoundDicepool().getDice(this.selectedDiceIndex).getColor();
            player.getGametable().getDicepool().insertDice(color);
            player.getGametable().getRoundDicepool().removeDice(this.selectedDiceIndex);

            //poi pescane uno
            player.setDiceforDiluenteperPastaSalda(player.getGametable().getDicepool().extractDice());
        }catch(EmpyDicepoolException e){
            throw new ToolIllegalOperationException(e.getMessage());
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
    public String getCardTitle() {
        return cardTitle;
    }
}
