package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.DecreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.IncreaseNotAllowedException;

import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable {
    private final DiceColor color;
    private int intensity;


    /**
     * @param diceColor Specify which color you want to obtain for the new Dice
     */
    public Dice(DiceColor diceColor){
        this.color = diceColor;
        setRandomIntensity();
    }


    /**
     * @return DiceColor - The color of my Dice
     */
    public DiceColor getColor() {
        return color;
    }


    /**
     * @return int - The intensity of my Dice
     */
    public int getIntensity() { return intensity; }


    /**
     * Changes the intensity value to a random one in range [1, 6]
     */
    public void setRandomIntensity (){
        Random rn = new Random();
        this.intensity = rn.nextInt(6) + 1;
    }


    /**
     * @param intens Intensity to which set the Dice
     */
    public void setIntensity(int intens){
        this.intensity = intens;
    }


    /**
     * This method increases the current intensity value by 1 only if it's in range [1, 5]
     * @throws IncreaseNotAllowedException
     */
    public void increaseIntensity() throws IncreaseNotAllowedException {
        if (this.getIntensity() == 6)
            throw new IncreaseNotAllowedException();

        this.intensity += 1;
    }


    /**
     * This method decreases the current intensity value by 1 only if it's in range [2, 6]
     * @throws DecreaseNotAllowedException
     */
    public void decreaseIntensity () throws DecreaseNotAllowedException {
        if (this.getIntensity() == 1)
            throw new DecreaseNotAllowedException();

        this.intensity -= 1;
    }

    /**
     * This method flips the Dice to the opposite face
     */
    public void setOppositeIntensity (){
        this.intensity = 7 - this.intensity;
    }

    @Override
    public String toString() {
        return this.intensity + this.getColor().toString();
    }
}
