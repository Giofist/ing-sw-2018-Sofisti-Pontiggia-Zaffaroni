package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.TileException;
import it.polimi.ingsw.model.Player;

public class Lathekin  implements ToolAction {
    final static int ID = 4;
    final static String cardTitle = "Lathekin";
    final static String description = "Muovi esattamente due dadi.\n" +
                                      "Rispetta tutte le restrizioni di piazzamento.";
    private Player player;
    int oldRow1, oldColumn1, newRow1, newColumn1, oldRow2, oldColumn2, newRow2, newColumn2;
    Dice remuvedDice1, remuvedDice2;

    public Lathekin(Player player, int oldRow1, int oldColumn1, int newRow1, int newColumn1, int oldRow2, int oldColumn2, int newRow2, int newColumn2){
        this.player = player;
        this.oldRow1 = oldRow1;
        this.oldRow2 = oldRow2;
        this.oldColumn1 = oldColumn1;
        this.oldColumn2 = oldColumn2;
        this.newRow1 = newRow1;
        this.newRow2 = newRow2;
        this.newColumn1 = newColumn1;
        this.newColumn2 = newColumn2;
    }

    @Override

    public void execute () throws TileException {
        remuvedDice1 = player.getScheme().removeDice(oldRow1, oldColumn1);
        player.getScheme().setDice(remuvedDice1, newRow1, newColumn1, false, false);
        remuvedDice2 = player.getScheme().removeDice(oldRow2, oldColumn2);
        player.getScheme().setDice(remuvedDice2, newRow2, newColumn2,false,false);
    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public String getCardTitle(){return cardTitle;}

    @Override
    public String getDescription(){
        return description;
    }
}
