package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.*;

public class Tile {
    private DiceColor color_constrain;
    private boolean haveColor_constrain;
    private int number_constrain;
    private boolean haveNumber_constrain;
    private Dice dice;
    private boolean occupied;

    public Tile (){
        this.haveColor_constrain = false;
        this.haveNumber_constrain = false;
        this.occupied = false;

    }

    public void setHaveColor_constrain(boolean haveColor_constrain) {
        this.haveColor_constrain = haveColor_constrain;
    }

    public void setHaveNumber_constrain(boolean haveNumber_constrain) {
        this.haveNumber_constrain = haveNumber_constrain;
    }


    public DiceColor getColor_Constrain(){
        return color_constrain;}

    public int getNumber_Constrain(){ return number_constrain;}


    public void setDice(Dice dice, boolean IgnoreColor, boolean IgnoreNumber){
        if (this.isOccupied()){
            throw new OccupiedTileException();
        }
        if (this.haveColor_constrain() && !IgnoreColor){
            if (dice.getColor() != this.getColor_Constrain()){
                throw new NotRispectedColorConstrainException;
            }
        }
        if (this.haveNumber_constrain() && !IgnoreNumber){
            if(dice.getIntensity() != this.getNumber_Constrain()){
                throw new NotRespectedNumberConstrainException;
            }
        }
        this.dice = dice;
        setOccupied();
    }



    public Dice removeDice(){
        setFree();
        return dice;
    }
    public boolean isOccupied(){ return occupied;}

    public boolean haveColor_constrain() {
        return haveColor_constrain;
    }

    public boolean haveNumber_constrain() {
        return haveNumber_constrain;
    }

    public void setOccupied(){ this.occupied = true;}

    public void setFree(){this.occupied = false;}

    public void setColourConstrain(DiceColor tile_color_constrain){this.color_constrain = tile_color_constrain;}

    public void setNumberConstrain(int number_constrain){this.number_constrain = number_constrain;}


}
