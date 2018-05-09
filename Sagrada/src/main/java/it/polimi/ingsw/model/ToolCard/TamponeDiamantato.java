package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Player;

public class TamponeDiamantato implements ToolAction {
    final static int ID = 10;
    final static String cardTitle = "Tampone Diamantato";
    final static String description = "Dopo aver scelto un dado, giralo sulla faccia opposta.\n" +
                                      "6 diventa 1, 5 diventa 2, 4 diventa 3 ecc.";
    private Player player;
    private int selectedDiceIndex;

    public TamponeDiamantato(Player player, int selectedDiceIndex){
        this.player = player;
        this.selectedDiceIndex = selectedDiceIndex;
    }

    @Override

    public void execute () {
        player.getGametable().getRoundDicepool().getDice(this.selectedDiceIndex).setOppositeIntensity();
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
