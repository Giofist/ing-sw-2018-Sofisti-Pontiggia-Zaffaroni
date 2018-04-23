package it.polimi.ingsw.model;

import it.polimi.ingsw.model.DiceColor;

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



    @Override
    public String toString() {
        return "[DICE] color: " + this.color + ", intensity: "+ this.INTENSITY;
    }
}
