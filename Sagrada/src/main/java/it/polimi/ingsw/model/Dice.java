package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.DecreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.IncreaseNotAllowedException;

import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable {
    private final DiceColor color;
    private int intensity;

    public Dice(DiceColor diceColor){
        this.color = diceColor;
        setRandomIntensity();
    }

    // Getters
    public DiceColor getColor() { return color; }
    public int getIntensity() { return intensity; }


    public void setRandomIntensity (){
        Random rn = new Random();
        this.intensity = rn.nextInt(6) + 1;
    }

    public void setIntensity(int intens){
        this.intensity = intens;
    }

    public void increaseIntensity() throws IncreaseNotAllowedException {
        if (this.getIntensity() == 6)
            throw new IncreaseNotAllowedException();

        this.intensity += 1;
    }

    public void decreaseIntensity () throws DecreaseNotAllowedException {
        if (this.getIntensity() == 1)
            throw new DecreaseNotAllowedException();

        this.intensity -= 1;
    }

    public void setOppositeIntensity (){
        this.intensity = 7 - this.intensity;
    }

    @Override
    public String toString() {
        return this.intensity + this.getColor().toString();
    }
}
