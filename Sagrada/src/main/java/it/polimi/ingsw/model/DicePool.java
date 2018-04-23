package it.polimi.ingsw.model;
import java.util.*;

public class DicePool {
    private LinkedList<Dice> dices = new LinkedList<Dice>();

    public DicePool() {

        // Initialize dice sequence
        for (DiceColor diceColor : DiceColor.values()) {
            for (int i=0; i<18; i++){
                dices.add(new Dice(diceColor));
            }
        }

        // Scramble the dice list
        //scrambleDicePool();
    }

    public void scrambleDicePool() {
        Collections.shuffle(dices);
    }

    public Dice getDice() {
        if (dices.size() == 0){
            return null;
        }
        return dices.removeFirst();
    }

    public boolean insertDice(DiceColor diceColor){
        dices.add(new Dice(diceColor));
        return true;
    }

    @Override
    public String toString() {
        String toString = new String();
        for (Dice dice : dices) {
            toString += dice.toString() + "\n";
        }

        return toString;
    }
}
