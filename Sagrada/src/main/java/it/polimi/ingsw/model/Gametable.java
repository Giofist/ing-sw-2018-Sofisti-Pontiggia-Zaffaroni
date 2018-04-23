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
    public void prepareGame(){
        this.dicepool = new DicePool();
        this.tooldeck =  new ToolCardsHandler();
    }
    public void setupRound(int numberPlayers){
        this.roundDicepool = new DicePool();
        for(int i=1 ; i < numberPlayers*2 +1; i++) {
            this.roundDicepool.addDice(this.dicepool.getDice());
        }
    }
    public Dice getDice() {
        return this.dicepool.getDice();
    }
    public Dice getRoundDice(int position){
        return this.roundDicepool.getDice(position);
    }


}
