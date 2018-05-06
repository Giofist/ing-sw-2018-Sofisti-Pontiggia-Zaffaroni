package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.DiceColor;
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
    private Player player;
    private int selectedDiceIndex;

    public DiluenteperPastaSalda(Player player, int selectedDiceIndex){
        this.player = player;
        this.selectedDiceIndex =selectedDiceIndex;
    }

    @Override
    public void execute (){

        //removes a dice e puts it into the dicepool
        //DiceColor color = player.getGametable().getRoundDice(this.selectedDiceIndex).getColor();
        //player.getGametable().
        //player.getGametable().getDicepool().addDice(player.getGametable().getRoundDicepool().removeDice(selectedDiceIndex));
        //player.getGametable().extractDice().setIntensity(diceIntensity);
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
