package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.DecreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.IncreaseNotAllowedException;

import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable {
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
        if (this.getIntensity() == 6)
            throw new IncreaseNotAllowedException();

        this.INTENSITY += 1;
    }

    public void decreaseIntensity () throws DecreaseNotAllowedException {
        if (this.getIntensity() == 1)
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
