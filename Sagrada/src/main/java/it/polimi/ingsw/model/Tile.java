package it.polimi.ingsw.model;

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

    public boolean haveColor_constrain() {
        return haveColor_constrain;
    }

    public boolean haveNumber_constrain() {
        return haveNumber_constrain;
    }

    public DiceColor getColor_Constrain(){
        return color_constrain;}

    public int getNumber_Constrain(){ return number_constrain;}

    public boolean setDice(Dice dice){
        if(isOccupied()==false && (dice.getColor() == getColor_Constrain()||getColor_Constrain()==null)&&(dice.getIntensity()==getNumber_Constrain()||getNumber_Constrain() == 0)) {
            this.dice = dice;
            setOccupied();  //useless if you check tile free?
            return true;
        }
        else return false;
    }

    public Dice removeDice(){
        setFree();
        return dice;
    }

    public boolean isOccupied(){ return occupied;} //we can return the dice

    public void setOccupied(){ this.occupied = true;}

    public void setColourConstrain(DiceColor tile_color_constrain){this.color_constrain = tile_color_constrain;}

    public void setNumberConstrain(int number_constrain){this.number_constrain = number_constrain;}

    public void setFree(){this.occupied = false;}
}
