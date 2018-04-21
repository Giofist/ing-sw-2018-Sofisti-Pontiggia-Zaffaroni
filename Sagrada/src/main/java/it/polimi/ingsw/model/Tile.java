package it.polimi.ingsw.model;

public class Tile {
    private final DiceColor color_constrain;
    private int number_constrain=0;
    private Dice dice;
    private boolean occupied;

    public Tile (DiceColor tile_color_constrain, int tile_number_constrain, Dice dice, boolean occ){
        this.color_constrain = tile_color_constrain;
        this.number_constrain = tile_number_constrain;
        this.dice = dice;
        this.occupied = occ;
    }

    public DiceColor getColor_Constrain(){ return color_constrain}

    public int getNumber_Constrain(){ return number_constrain}

    public boolean setDice(Dice dice){
        if(isOccupied()==false && (dice.getColor() == getColor_Constrain()||getColor_Constrain()==NULL)&&(dice.getIntensity()==getNumber_Constrain()||getNumber_Constrain() == 0)) {
            this.dice = dice;
            setOccupied();  //useless if you check tile free?
            return true;
        }
        else return false;
    }

    public boolean removeDice(){
        // not present in UML
    }
    public boolean isOccupied(){ return occupied;}

    public void setOccupied(){ this.occupied = true;}

    public void setFree(){this.occupied = false;}
}
