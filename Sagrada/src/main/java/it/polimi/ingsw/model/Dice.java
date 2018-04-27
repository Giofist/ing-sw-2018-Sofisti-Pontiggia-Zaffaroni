package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.DecreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.IncreaseNotAllowedException;

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

    public void increaseIntensity() throws IncreaseNotAllowedException {
        if (this.INTENSITY == 6)
            throw new IncreaseNotAllowedException();

        this.INTENSITY += 1;
    }

    public void decreaseIntensity () throws DecreaseNotAllowedException {
        if (this.INTENSITY == 1)
            throw new DecreaseNotAllowedException();

        this.INTENSITY -= 1;
    }

    public void setOppositeIntensity (){
        this.INTENSITY = 7 - this.INTENSITY;
    }

    @Override
    public String toString() {
        return "[DICE] color: " + this.color + ", intensity: "+ this.INTENSITY;
    }
}
