package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

//obiettivo privato
public class Sfumature implements GoalCard {
    private int ID;
    private DiceColor color;
    private String name;
    private String description;

    public Sfumature(int id, DiceColor diceColor){
        this.color = diceColor;
        this.ID = id;
        this.name =  "Sfumature " + diceColor.toString() + ".";
        this.description = "Somma dei valori su tutti i dadi "+ diceColor.toString() + ".";
    }

    @Override
    public void calculatepoint(Player player) {
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

