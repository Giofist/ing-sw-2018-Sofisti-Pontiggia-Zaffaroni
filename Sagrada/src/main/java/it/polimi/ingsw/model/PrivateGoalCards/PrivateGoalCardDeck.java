package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.GoalCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//command pattern just like schemecarddeck
public class PrivateGoalCardDeck {
    private ArrayList<Integer> cards;
    //constructor
    public PrivateGoalCardDeck() {
        this.cards = new ArrayList<>();
        for(int i=1; i<6;i++){
            this.cards.add(i);
        }

        Collections.shuffle(cards);

    }
    public GoalCard getCard() throws PrivateGoalCardException{
        int CardID = this.getRandomID();
        switch (CardID) {
            case 1: return new SfumatureRosse();
            case 2: return new SfumatureGialle();
            case 3: return new SfumatureVerdi();
            case 4: return new SfumatureBlu();
            case 5: return new SfumatureViola();
            default: throw new PrivateGoalCardException();
        }
    }
    public int getRandomID(){
        int value = this.cards.get(0);
        this.cards.remove(0);
        return value;
    }

}
