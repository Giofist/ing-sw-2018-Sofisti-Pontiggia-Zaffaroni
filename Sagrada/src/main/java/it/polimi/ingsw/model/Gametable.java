package it.polimi.ingsw.model;

public class Gametable {
    private ToolCardsHandler tooldeck;
    private DicePool dicepool;
    private DicePool roundDicepool; //dicepool of the current round

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
    }

    //to do when preparing a round
    public void setupRound(int numberPlayers){
        this.roundDicepool = new DicePool();
        for(int i=1 ; i < numberPlayers*2 +1; i++) {
            this.roundDicepool.addDice(this.dicepool.getDice());
        }
    }

    //asking for a dice in the dicepool
    public Dice getDice() {
        return this.dicepool.getDice();
    }

    //asking for a specific dice in the round
    public Dice getRoundDice(int position){
        return this.roundDicepool.getDice(position);
    }


}
