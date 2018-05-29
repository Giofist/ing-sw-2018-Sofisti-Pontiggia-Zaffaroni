package it.polimi.ingsw.model;
import java.util.*;

// NB: questa classe si usa sia per il sacchetto dei 90 dadi che per i dadi del round, che però sono due cose concettualmente
//leggermente diverse: proprio per questo sono stati creati metodi diversi per fare cose diverse: fate attenzione a quale
//vi serve davvero quando li invocate
//per esempio fate caso al metodo extract
public class DicePool {
    private LinkedList<Dice> dices;

    // This constructor returns an empty DicePool
    public DicePool() {
        this.dices = new LinkedList<>();
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

    // very useful for diluentePerPastaSalda
    public void insertDice(DiceColor diceColor){ dices.add(new Dice(diceColor)); }

    //add a new dice passed by parameter
    //USE only for RoundDicepool
    public void addDice(Dice dice){ this.dices.add(dice); }

    public void addDice(int selectedDiceIndex,Dice dice ){
        this.dices.add(selectedDiceIndex,dice);
    }



    private void scrambleDicePool() { Collections.shuffle(dices); }



    //do not use for RoundDicepool
    public Dice extractDice() {
        if (dices.isEmpty()){
            return null;
        }
        return dices.removeFirst();
    }

    //do use for RoundDicepool
    // il metodo get non elimina dalla lista
    public Dice getDice(int diceIndex){
        if (dices.isEmpty()){
            return null;
        }
        return dices.get(diceIndex);
    }

    //ho aggiunto questo metodo
    //propongo di lanciare delle eccezioni per gestire questa cosa e il metodo sopra
    public void removeDice(int diceIndex){
        if(diceIndex <= getDicePoolSize()){
            dices.remove(diceIndex);
        }
    }

    public List<Dice> getallDicesbutnotremove(){
        List list = new LinkedList<Dice>();
        list.addAll(this.dices);
        return list;
    }


    //useful for roundTrack
    public void removeallDices(){
        this.dices.clear();
    }

    //useful for RoundTrack
    public void addallDices(List dices){
        this.dices.addAll(dices);
    }

    @Override
    public String toString(){
        String stringDicePool = null;
        for (Dice dice : this.getallDicesbutnotremove()) {
            stringDicePool += dice.toString();
            stringDicePool += "-";
        }
        return stringDicePool;
    }




}
