package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Gametable {
    private ToolCardsHandler tooldeck;
    private DicePool dicepool;
    private DicePool roundDicepool;//dicepool of the current round
    private PrivateGoalCardDeck privategoalcardsdeck;
    private PublicGoalCardHandler publicGoalCardDeck;

    //constructor
    public Gametable(){
        prepareGame();
    };


    public ToolCardsHandler getTooldeck() {
        return tooldeck;
    }

    //first to do when preparing a game
    public void prepareGame(){
        this.dicepool = new DicePool();
        this.tooldeck =  new ToolCardsHandler();
        this.privategoalcardsdeck= new PrivateGoalCardDeck();
        this.publicGoalCardDeck = new PublicGoalCardHandler();

    }

    //to do when preparing a round
    public void setupRound(int numberPlayers){
        this.roundDicepool = new DicePool();
        for(int i=1 ; i < numberPlayers*2 +1; i++) {
            this.roundDicepool.addDice(this.dicepool.extractDice());
        }
    }
    //to get the private Goal card
     public GoalCard getPrivateGoalCard(){
        return this.privategoalcardsdeck.getCard();
     }


    //asking for a dice in the dicepool
    public Dice getDice() {
        return this.dicepool.extractDice();
    }

    //asking for a specific dice in the round
    public Dice getRoundDice(int position){
        return this.roundDicepool.getDice(position);
    }

    //to get IDs
    public ArrayList getPublicGoalDescriptions(){
        return this.publicGoalCardDeck.getDescriptions();
    }
    public ArrayList getPublicGoalIDs(){
        return this.publicGoalCardDeck.getIDs();
    }


}
