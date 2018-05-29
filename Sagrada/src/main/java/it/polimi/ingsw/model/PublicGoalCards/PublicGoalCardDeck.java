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


    public PublicGoalCardDeck(){
        for(int i=1; i<=10;i++){
            this.cardsID.add(i);
        }

        Collections.shuffle(cardsID);
        addCardToDeck();
        addCardToDeck();
        addCardToDeck();


    }
    //get a ramdom value
    //yes, actually it's the first, but we have shuffled before
    public int getValue(){
        int value =this.cardsID.get(0);
        this.cardsID.remove(0);
        return value;
    }
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


    //calculate all points for a specyfic player for all the public goal cards
    public void doCalculatePoints(Player player){
        for (GoalCard goalcard:this.deck) {
            goalcard.calculatepoint(player);
        }
          GoalCard CalculatoreforFreeSpaces =  new FreeSpaces();
        CalculatoreforFreeSpaces.calculatepoint(player);
    }

    //to get the IDs, descriptions and Names of public goal cards
    public String getIDs(){
        String IDs = "";
        for (GoalCard goalCard: this.deck) {
           IDs += goalCard.getID();
        }
         return IDs;

    }
    public String getDescriptions(){
        String descriptions = "";
        for (GoalCard goalCard: this.deck) {
            descriptions += goalCard.getDescription();
        }
        return descriptions;
    }

    public String getCardsNames() {
        String names = "";
        for (GoalCard goalCard : this.deck) {
            names += goalCard.getName();

        }
        return names;
    }
}
