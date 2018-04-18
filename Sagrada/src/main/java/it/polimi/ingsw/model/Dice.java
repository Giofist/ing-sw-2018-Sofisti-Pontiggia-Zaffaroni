package it.polimi.ingsw.model;

public class Dice {
    private final DiceColor color;
    private final int INTENSITY;

    public Dice(DiceColor diceColor, int diceIntensity){
        this.color = diceColor;
        this.INTENSITY = diceIntensity;
    }

    public DiceColor getColor() { return color; }

    public int getIntensity() { return INTENSITY; }

    @Override
    public String toString() {
        return "[DICE] color: " + this.color + ", intensity: "+ this.INTENSITY;
    }
}
