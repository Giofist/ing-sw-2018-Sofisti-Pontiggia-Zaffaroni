package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

//obiettivo privato
public class SfumatureViola implements GoalCard {
    static private int ID = 5;
    static private String name = "Sfumature Blu";
    static private String description = "Somma dei valori su tutti i dadi viola.";


    @Override
    public void calculatepoint(Player player) {
        DiceColor color = DiceColor.VIOLET;
        try{
            for(Tile tile: player.getScheme()) {
                try{
                    if (tile.getDice().getColor()== color) {
                        player.addPoints(tile.getDice().getIntensity());
                    }
                }catch (DiceNotExistantException e){
                    //zorry, there is no dice
                    //
                }
            }
        } catch (SchemeCardNotExistantException e){
            //??
        }
    }
    @Override
    public  int getID() {
        return ID;
    }

    @Override
    public String getName(){return name;}

    @Override
    public  String getDescription() {
        return description;
    }


}
