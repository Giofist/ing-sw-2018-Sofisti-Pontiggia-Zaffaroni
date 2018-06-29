package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class DicePool {
    private LinkedList<Dice> dices;


    /**
     * Creates an empty Dicepool
     */
    public DicePool() {
        this.dices = new LinkedList<>();
    }


    /**
     * Creates a Dicepool containing the number of Dices specified for each color as argument.
     * After the creation the Dicepool gets scrambled.
     * If any of the parameters passed is negative, an empty Dicepool is returned.
     * @param blueDices Number of blue dices to add
     * @param greenDices Number of green dices to add
     * @param redDices Number of red dices to add
     * @param violetDices Number of violet dices to add
     * @param yellowDices Number of yellow dices to add
     */
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


    /**
     * @return - Returns how many Dices are in the Dicepool
     */
    public int getDicePoolSize() { return this.dices.size(); }


    /**
     * This method adds a new Dice of a specific color to the Dicepool.
     * Very useful for diluentePerPastaSalda.
     * @param diceColor The color of the dice I want to add
     */
    public void insertDice(DiceColor diceColor){ dices.add(new Dice(diceColor)); }


    /**
     * This method adds a Dice specified by the user as parameter.
     * Used only for RoundDicepool.
     * @param dice The dice I want to add
     */
    public void addDice(Dice dice){ this.dices.add(dice); }


    /**
     * Add the specified Dice in a specific position in the Dicepool
     * @param selectedDiceIndex Index of the Dicepool where to insert the Dice
     * @param dice The Dice I want to insert
     */
    public void addDice(int selectedDiceIndex,Dice dice ){
        this.dices.add(selectedDiceIndex,dice);
    }


    /**
     * Changes randomly the order of the Dices in the Dicepool
     */
    private void scrambleDicePool() { Collections.shuffle(dices); }


    /**
     * This method returns the first Dice and removes it from the Dicepool
     * @return The Dice I want to extract
     * @throws DicepoolIndexException - Exception thrown when the Dicepool is empty
     */
    public Dice extractDice() throws DicepoolIndexException {
        if (dices.isEmpty()){
            throw new DicepoolIndexException();
        }

        return dices.removeFirst();
    }


    /**
     * This method returns the Dice in a specified position in the Dicepool
     * @param diceIndex The position from where to get the Dice
     * @return  The Dice in position diceIndex
     * @throws DicepoolIndexException Exception thrown in case a wrong index is specified by the user
     */
    public Dice getDice(int diceIndex)throws DicepoolIndexException{
        if (dices.isEmpty() || diceIndex <0 || diceIndex > dices.size() ){
            throw new DicepoolIndexException();
        }

        return dices.get(diceIndex);
    }


    /**
     * This method removes the Dice in the specified position
     * @param diceIndex Position from where to remove the Dice
     * @throws DicepoolIndexException Exception thrown in case the user specifies an invalid index
     */
    public void removeDice(int diceIndex)throws DicepoolIndexException{
        if (dices.isEmpty() || diceIndex <0 || diceIndex > dices.size() ){
            throw new DicepoolIndexException();
        }

        dices.remove(diceIndex);
    }


    /**
     * This method returns all the Dices contained in the Dicepool without removing them from it
     * @return A list of Dices
     */
    public LinkedList<Dice> getallDicesbutnotremove(){
        LinkedList list = new LinkedList<Dice>();
        list.addAll(this.dices);
        return list;
    }


    /**
     * This method removes every contained Dice from the Dicepool, the result is an empty Dicepool
     */
    public void removeallDices(){
        this.dices.clear();
    }


    /**
     * This method adds a list of Dices to my Dicepool
     * @param dices List of Dices I want to add to the Dicepool
     */
    public void addallDices(List dices){
        this.dices.addAll(dices);
    }


    @Override
    public String toString(){
        String stringDicePool = "";
        for (Dice dice : this.getallDicesbutnotremove()) {
            stringDicePool += dice.toString();
            stringDicePool += "-";
        }
        return stringDicePool;
    }

}
