package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.NotRespectedNumberConstrainException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.NotRispectedColorConstrainException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;

public class Tile {
    private DiceColor color_constrain;
    private boolean haveColor_constrain;
    private int number_constrain;
    private boolean haveNumber_constrain;
    private Dice dice;
    private int row;
    private int column;

    public Tile (int row, int column){
        this.haveColor_constrain = false;
        this.haveNumber_constrain = false;
        this.dice = null;
        this.row = row;
        this.column= column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setHaveColor_constrain(boolean haveColor_constrain) {
        this.haveColor_constrain = haveColor_constrain;
    }
    public void setHaveNumber_constrain(boolean haveNumber_constrain) { this.haveNumber_constrain = haveNumber_constrain; }

    public void setColourConstrain(DiceColor tile_color_constrain){this.color_constrain = tile_color_constrain;}
    public void setNumberConstrain(int number_constrain){this.number_constrain = number_constrain;}

    public DiceColor getColor_Constrain(){
        return color_constrain;
    }
    public int getNumber_Constrain(){ return number_constrain;}


    public void setDice(Dice dice, boolean IgnoreColor, boolean IgnoreNumber) throws TileConstrainException {
        if (this.haveColor_constrain() && !IgnoreColor){
            if (dice.getColor() != this.getColor_Constrain()){
                throw new NotRispectedColorConstrainException();
            }
        }
        if (this.haveNumber_constrain() && !IgnoreNumber){
            if(dice.getIntensity() != this.getNumber_Constrain()){
                throw new NotRespectedNumberConstrainException();
            }
        }
        this.dice = dice;

    }

    //useful for PennelloPerPastaSalda
    public boolean settableDiceHere(Dice dice,boolean IgnoreColor, boolean IgnoreNumber){

            if (this.haveColor_constrain() && !IgnoreColor){
                if (dice.getColor() != this.getColor_Constrain()){
                    return false;
                }
            }
            if (this.haveNumber_constrain() && !IgnoreNumber){
                if(dice.getIntensity() != this.getNumber_Constrain()){
                    return false;
                }
            }
            return true;

    }


    //getandremove viene splittata nei due metodi sottostanti
    //lo ritengo più sicuro nelle toolcard che la chiamano: prima che il ddo che mi serve, poi lo rimuovo

    //to get the dice of this tile for inspection
    public Dice getDice()throws DiceNotExistantException {
        if(!this.isOccupied()){
            throw new DiceNotExistantException();
        }
        return this.dice;
    }


    //to remove the dice
    public void removeDice() throws DiceNotExistantException{
        if(!this.isOccupied()){
            throw new DiceNotExistantException();
        }else
            this.dice = null;
    }

    public boolean haveColor_constrain() { return haveColor_constrain; }
    public boolean haveNumber_constrain() { return haveNumber_constrain; }


    //to see if this this is occupied
    public boolean isOccupied(){
        if (this.dice == null){
            return false;
        }return true;

        }



}
