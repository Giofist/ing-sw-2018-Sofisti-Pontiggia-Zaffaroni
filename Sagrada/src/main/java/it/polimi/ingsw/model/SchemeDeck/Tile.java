package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.NotRespectedNumberConstrainException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.NotRispectedColorConstrainException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;

import java.io.Serializable;

public class Tile implements Serializable {
    private DiceColor color_constrain;
    private boolean haveColor_constrain;
    private int number_constrain;
    private boolean haveNumber_constrain;
    private Dice dice;
    private int row;
    private int column;

    /**
     * This constructor creates an empty tile with no color or intensity constrains assigned to the coordinates that we pass
     * @param row The row that we want to assign to the tile
     * @param column The column that we want to assign to the tile
     */
    public Tile (int row, int column){
        this.haveColor_constrain = false;
        this.haveNumber_constrain = false;
        this.dice = null;
        this.row = row;
        this.column= column;
    }


    /**
     * @return The column that we have assigned to the tile
     */
    public int getColumn() {
        return column;
    }


    /**
     * @return The row that we have assigned to the tile
     */
    public int getRow() {
        return row;
    }


    /**
     * This method allows to set if the tile has a color constrain
     * @param haveColor_constrain True or false if we want the color constrain
     */
    public void setHaveColor_constrain(boolean haveColor_constrain) {
        this.haveColor_constrain = haveColor_constrain;
    }


    /**
     * This method allows to set if the tile has an intensity constrain
     * @param haveNumber_constrain True or false if we want the intensity constrain
     */
    public void setHaveNumber_constrain(boolean haveNumber_constrain) { this.haveNumber_constrain = haveNumber_constrain; }


    /**
     * This method allows to specify the color that will be the constrain in the tile
     * @param tile_color_constrain The color that will be the constrain
     */
    public void setColourConstrain(DiceColor tile_color_constrain){this.color_constrain = tile_color_constrain;}


    /**
     * This method allows to specify the intensity that will be the constrain in the tile
     * @param number_constrain The intensity that will be the constrain
     */
    public void setNumberConstrain(int number_constrain){this.number_constrain = number_constrain;}


    /**
     * @return A color which will be the constrain for placing a dice in this tile
     */
    public DiceColor getColor_Constrain(){
        return color_constrain;
    }


    /**
     * @return An intensity which will be the constrain for placing a dice in this tile
     */
    public int getNumber_Constrain(){ return number_constrain;}


    /**
     * @param dice The dice we want to set
     * @param IgnoreColor Boolean value for ignoring or not the color constrain when placing the dice
     * @param IgnoreNumber Boolean value for ignoring or not the intensity constrain when placing the dice
     * @throws TileConstrainException Exception thrown if there is some constrain not respected when trying to place the dice
     */
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


    /**
     * This method is useful for "pennello per pasta salda" tool card
     * @param dice The dice we would like to place
     * @param IgnoreColor Boolean value for ignoring or not the color constrain
     * @param IgnoreNumber Boolean value for ignoring or not the intensity constrain
     * @return True if we can place the dice in this tile
     */
    public boolean settableDiceHere(Dice dice,boolean IgnoreColor, boolean IgnoreNumber){

            if (this.haveColor_constrain() && !IgnoreColor){
                if (dice.getColor() != this.getColor_Constrain()){
                    return false;
                }
            }
            if (this.haveNumber_constrain() && !IgnoreNumber){
                return dice.getIntensity() == this.getNumber_Constrain();
            }
            return true;

    }


    /**
     * @return The dice placed on the tile
     * @throws DiceNotExistantException Exception thrown if the tile is empty
     */
    public Dice getDice()throws DiceNotExistantException {
        if(!this.isOccupied()){
            throw new DiceNotExistantException();
        }
        return this.dice;
    }


    /**
     * With this method we can remove the dice from the tile
     * @throws DiceNotExistantException Exception thrown if the tile is empty
     */
    public void removeDice() throws DiceNotExistantException{
        if(!this.isOccupied()){
            throw new DiceNotExistantException();
        }else
            this.dice = null;
    }


    /**
     * @return True if the tile has a color constrain
     */
    public boolean haveColor_constrain() { return haveColor_constrain; }


    /**
     * @return True if the tile has an intensity constrain
     */
    public boolean haveNumber_constrain() { return haveNumber_constrain; }


    /**
     * @return True if the tile is already occupied by another dice
     */
    public boolean isOccupied(){
        return this.dice != null;
    }



}
