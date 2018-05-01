package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Player;

public class DiluenteperPastaSalda  implements ToolAction {
    final static int ID = 11;
    final static String cardTitle = "Diluente per Pasta Salda";
    final static String description = "Dopo aver scelto un dado, riponilo nel Sacchetto, poi pescane uno dal sacchetto.\n" +
                                      "Scegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento.";
    private Player player;
    private int diceIntensity;
    DiceColor diceColour;
    private int selectedDiceIndex;

    public DiluenteperPastaSalda(Player player, int selectedDiceIndex, int diceIntensity){
        this.player = player;
        this.selectedDiceIndex =selectedDiceIndex;
        this.diceIntensity = diceIntensity;
        this.diceColour = diceColour;
    }

    @Override

    public void execute (){
        player.getGametable().getDicepool().addDice(player.getGametable().getRoundDicepool().removeDice(selectedDiceIndex));
        player.getGametable().getDice().setIntensity(diceIntensity);
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
