package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.GoalCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//command pattern just like schemecarddeck
public class PrivateGoalCardDeck {
    private ArrayList<Integer> cards;


    /**
     * The constructor creates a private goal cards deck with 5 private goal cards
     */
    public PrivateGoalCardDeck() {
        this.cards = new ArrayList<Integer>(Arrays.asList( 1, 2, 3, 4, 5 ));

        Collections.shuffle(cards);
    }


    /**
     * @return A random Sfumature private goal card
     * @throws PrivateGoalCardException
     */
    public GoalCard getCard() throws PrivateGoalCardException{
        // Getting the first card is like getting a random one because the deck is shuffled
        int CardID = this.cards.remove(0);

        switch (CardID) {
            case 1: return new Sfumature(1,DiceColor.RED);
            case 2: return new Sfumature(2, DiceColor.YELLOW);
            case 3: return new Sfumature(3,DiceColor.GREEN);
            case 4: return new Sfumature(4,DiceColor.BLUE);
            case 5: return new Sfumature(5,DiceColor.VIOLET);
            default: throw new PrivateGoalCardException();
        }
    }

}
