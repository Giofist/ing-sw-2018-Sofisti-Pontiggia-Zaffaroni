package it.polimi.ingsw.model;

import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.SchemeDeck.SchemeCardDeck;
import it.polimi.ingsw.model.ToolCard.ToolAction;
import it.polimi.ingsw.model.ToolCard.ToolCardsHandler;

import java.util.ArrayList;



//Gametable non deve esporre la propria implementazione, ma deve fare tutto da sè
public class Gametable {
    private ToolCardsHandler tooldeck;
    private DicePool dicepool;
    private DicePool roundDicepool;//dicepool of the current round
    private PrivateGoalCardDeck privategoalcardsdeck;
    private PublicGoalCardDeck publicGoalCardDeck;
    private SchemeCardDeck schemeCardDeck;

    //constructor
    public Gametable() {
        prepareGame();
    }



    //first to do when preparing a game
    public void prepareGame() {
        this.dicepool = new DicePool();
        this.tooldeck = new ToolCardsHandler();
        this.privategoalcardsdeck = new PrivateGoalCardDeck();
        this.publicGoalCardDeck = new PublicGoalCardDeck();
        this.schemeCardDeck = new SchemeCardDeck();


    }

    //to do when preparing a round
    public void setupRound(int numberPlayers) {
        this.roundDicepool = new DicePool();
        for (int i = 1; i < numberPlayers * 2 + 1; i++) {
            this.roundDicepool.addDice(this.dicepool.extractDice());
        }
    }


    //per la gestione delle toolAction
    public int costofToolAction (int id){
        return this.tooldeck.getCost(id);
    }



    public void useaToolCard(ToolAction toolAction){
       this.tooldeck.doAction(toolAction);

    }

    //to get the private Goal card
    public GoalCard getPrivateGoalCard() {
        return this.privategoalcardsdeck.getCard();
    }


    //asking for a dice in the dicepool of 90 dices
    public Dice getDice() {
        return this.dicepool.extractDice();
    }

    //asking for a specific dice in the round
    public Dice getRoundDice(int position) {
        return this.roundDicepool.getDice(position);
    }

    //to get IDs and description of the public goals
    public ArrayList getPublicGoalDescriptions() {
        return this.publicGoalCardDeck.getDescriptions();
    }

    public ArrayList getPublicGoalIDs() {
        return this.publicGoalCardDeck.getIDs();
    }


    //
    public SchemeCard getSchemeCard() {
        return this.schemeCardDeck.getCard();
    }
}


