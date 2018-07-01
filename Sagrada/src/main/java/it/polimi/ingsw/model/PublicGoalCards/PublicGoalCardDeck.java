package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PublicGoalCardDeck {
    private LinkedList<GoalCard> deck = new LinkedList<>();
    private ArrayList<Integer> cardsID = new ArrayList<>();


    /**
     * The constructor is going to create a deck with 3 random public goal cards
     */
    public PublicGoalCardDeck(){
        for(int i=1; i<=10;i++){
            this.cardsID.add(i);
        }

        Collections.shuffle(cardsID);
        addCardToDeck();
        addCardToDeck();
        addCardToDeck();
    }


    /**
     * @return A random integer value corresponding to the id of a public goal card
     */
    public int getValue(){
        int value =this.cardsID.get(0);
        this.cardsID.remove(0);
        return value;
    }


    /**
     *  This method adds a random public goal card to the public goal deck
     */
    public void addCardToDeck(){
        int cardID = this.getValue();

        switch (cardID){
            case 1: this.deck.add(new ColoriDiversiRiga()); break;
            case 2 : this.deck.add(new ColoriDiversiColonna()); break;
            case 3 : this.deck.add(new SfumatureDiverseRiga()); break;
            case 4 : this.deck.add(new SfumatureDiverseColonna());break;
            case 5 : this.deck.add(new SfumatureChiare());break;
            case 6 : this.deck.add(new SfumatureMedie());break;
            case 7 : this.deck.add(new SfumatureScure());break;
            case 8 : this.deck.add(new SfumatureDiverse());break;
            case 9 : this.deck.add(new DiagonaliColorate());break;
            case 10 : this.deck.add(new VarietaDiColore());break;
            default: break; // add an Exception here
        }

    }


    /**
     * This method calculates how many points a player has scored in all the public goal cards in the match
     * @param player
     */
    public void doCalculatePoints(Player player){

        for (GoalCard goalcard:this.deck) {
            goalcard.calculatepoint(player);
        }
        GoalCard CalculatoreforFreeSpaces =  new FreeSpaces();
        CalculatoreforFreeSpaces.calculatepoint(player);
    }


    /**
     * @return A list containing all the 3 public goal cards previously extracted
     */
    public List getCards(){
        List list = new LinkedList();
       for (GoalCard goalCard: this.deck){
           list.add(goalCard);
       }
       return list;
    }




}
