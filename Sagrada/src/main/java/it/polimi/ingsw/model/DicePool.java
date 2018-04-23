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
        scrambleDicePool();
    }

    public void scrambleDicePool() {
        Collections.shuffle(dices);
    }
    //do not use for RoundDicepool
    public Dice getDice() {
        if (dices.isEmpty()){
            return null;
        }
        return dices.removeFirst();
    }

    //do use for RoundDice
    public Dice getDice(int position){
        if (dices.isEmpty()){
            return null;
        }
        return dices.get(position);
    }

    // a che serve? Per una carta utensile? Però aggiunge un NUOVO dado, bohboh
    public boolean insertDice(DiceColor diceColor){
        dices.add(new Dice(diceColor));
        return true;
    }

    //add a new dice passed by parameter
    public void addDice(Dice dice){
        this.dices.add(dice);
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
