package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.IllegalOperationException;

import java.util.Random;

public class Dice {
    private final DiceColor color;
    private int INTENSITY;

    public Dice(DiceColor diceColor){
        this.color = diceColor;
        setRandomIntensity();
    }

    // Getters
    public DiceColor getColor() { return color; }
    public int getIntensity() { return INTENSITY; }


    public void setRandomIntensity (){
        Random rn = new Random();
        this.INTENSITY = rn.nextInt(6) + 1;
    }

    public void setIntensity(int intens){
        this.INTENSITY = intens;
    }

    public void increaseIntensity() throws IllegalOperationException {
        if (this.INTENSITY == 6)
            throw new IllegalOperationException();

        this.INTENSITY += 1;
    }

    public void decreaseIntensity () throws IllegalOperationException{
        if (this.INTENSITY == 1)
            throw new IllegalOperationException();

        this.INTENSITY -= 1;
    }

    public void setOppositeIntensity () throws IllegalOperationException{
        switch (this.INTENSITY){
            case 1:
                this.INTENSITY=6;
                break;
            case 2:
                this.INTENSITY=5;
                break;
            case 3:
                this.INTENSITY=4;
                break;
            case 4:
                this.INTENSITY=3;
                break;
            case 5:
                this.INTENSITY=2;
                break;
            case 6:
                this.INTENSITY=1;
                break;
            default:
                break;
        }
    }




    @Override
    public String toString() {
        return "[DICE] color: " + this.color + ", intensity: "+ this.INTENSITY;
    }
}
