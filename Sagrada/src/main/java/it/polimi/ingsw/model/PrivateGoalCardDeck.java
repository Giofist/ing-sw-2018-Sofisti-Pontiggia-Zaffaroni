package it.polimi.ingsw.model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//command pattern just like schemecarddeck
public class PrivateGoalCardDeck {
    private ArrayList<Integer> cards = new ArrayList<>();

    public PrivateGoalCardDeck() {
        this.cards = new ArrayList<>();
        for(int i=1; i<6;i++){
            this.cards.add(i);
        }

        Collections.shuffle(cards);

    }
    public GoalCard getCard(){
        /*int CardID = this.getRandomID();
        switch (CardID){
            case 1: return new ObiettivoPrivato1();
            case 2: break;
            default: return new ObiettivoPrivato1();
        }


         */
        return new SfumatureRosse();
    }


    public int getRandomID(){
        int value = this.cards.get(0);
        this.cards.remove(0);
        return value;
    }

}
