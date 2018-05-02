package it.polimi.ingsw.model;
import java.util.*;

public class DicePool {
    private LinkedList<Dice> dices;

    // This constructor returns an empty DicePool
    public DicePool() {
        this.dices = new LinkedList<Dice>();
    }

    // This constructor returns a DicePool with the specified amount of Dice for each color
    // If one of the passed parameters is negative an empty DicePool will be returned
    public DicePool(int blueDices, int greenDices, int redDices, int violetDices, int yellowDices) {
        this.dices = new LinkedList<Dice>();

        if (blueDices < 0 || greenDices < 0 || redDices < 0 || violetDices < 0 || yellowDices < 0){
            return;
        }

        int[] dicesColor = {blueDices, greenDices, redDices, violetDices, yellowDices};
        // Initialize dice sequence
        for (int i = 0; i < dicesColor.length; i++){
            for (int j = 0; j < dicesColor[i]; j++){
                this.dices.add(new Dice(DiceColor.values()[i]));
            }
        }

        // Scramble the dice list
        scrambleDicePool();
    }

    public int getDicePoolSize() { return this.dices.size(); }

    // a che serve? Per una carta utensile? Però aggiunge un NUOVO dado, bohboh
    public void insertDice(DiceColor diceColor){ dices.add(new Dice(diceColor)); }

    //add a new dice passed by parameter
    public void addDice(Dice dice){ this.dices.add(dice); }

    public void scrambleDicePool() { Collections.shuffle(dices); }



    //do not use for RoundDicepool
    public Dice extractDice() {
        if (dices.isEmpty()){
            return null;
        }
        return dices.removeFirst();
    }

    //do use for RoundDicepool
    public Dice getDice(int position){
        if (dices.isEmpty()){
            return null;
        }
        return dices.get(position);
    }


    //non è inutile questo metodo? Boh
    public Dice removeDice(int diceIndex){
        if(diceIndex<=getDicePoolSize()){
            return dices.remove(diceIndex);
        }
        else return null;
    }

}
