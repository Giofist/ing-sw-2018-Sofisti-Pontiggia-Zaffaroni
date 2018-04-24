package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class PublicGoalCardDeck {
    private LinkedList<GoalCard> deck = new LinkedList<GoalCard>();
    private ArrayList<Integer> cardsID = new ArrayList<>();


    public PublicGoalCardDeck(){
        for(int i=1; i<=10;i++){
            this.cardsID.add(i);
        }

        Collections.shuffle(cardsID);
        addCardToDeck();
        addCardToDeck();
        addCardToDeck();

    }

    public int getValue(){
        int value =this.cardsID.get(0);
        this.cardsID.remove(0);
        return value;
    }
    public void addCardToDeck(){
        int cardID = this.getValue();
        switch (cardID){
            case 2 : this.deck.add(new ColoriDiversiColonna());

        }
        this.deck.add(new ColoriDiversiColonna());
    }


    //calculate all points for a specyfic player for all the public goal cards
    public void doCalculatePoints(Player player){
        for (GoalCard goalcard:this.deck) {
            goalcard.calculatepoint(player);
        }
    }


    public ArrayList getIDs(){
         ArrayList<Integer> IDs = new ArrayList<Integer>();
         for (GoalCard goalCard: this.deck) {
            IDs.add(goalCard.getID());
         }
         return IDs;

    }
    public ArrayList getDescriptions(){
        ArrayList<String> descriptions = new ArrayList<String>();
        for (GoalCard goalCard: this.deck) {
            descriptions.add(goalCard.getDescription());
        }
        return descriptions;
    }


}
