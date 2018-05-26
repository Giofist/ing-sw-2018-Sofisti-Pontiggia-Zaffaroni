package it.polimi.ingsw.model.PrivateGoalCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.SchemeDeck.Tile;

//obiettivo privato
public class SfumatureRosse implements GoalCard {
    static private int ID = 1;
    static private String name = "Sfumature Rosse";
    static private String description = "Somma dei valori su tutti i dadi rossi.";


    @Override
    public void calculatepoint(Player player) {
        DiceColor color = DiceColor.RED;
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
