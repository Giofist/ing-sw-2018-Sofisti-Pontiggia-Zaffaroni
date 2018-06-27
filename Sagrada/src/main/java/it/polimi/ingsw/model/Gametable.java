package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import it.polimi.ingsw.model.PrivateGoalCards.PrivateGoalCardDeck;
import it.polimi.ingsw.model.PublicGoalCards.PublicGoalCardDeck;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.SchemeDeck.SchemeCardDeck;
import it.polimi.ingsw.model.ToolCard.ToolCardsDeck;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.io.IOException;
import java.util.List;



public class Gametable {
    private ToolCardsDeck tooldeck;
    private DicePool dicepool;
    private DicePool roundDicepool;//dicepool of the current round
    private PrivateGoalCardDeck privategoalcardsdeck;
    private PublicGoalCardDeck publicGoalCardDeck;
    private SchemeCardDeck schemeCardDeck;
    private RoundTrack roundTrack;
    private int numberPlayers;



    //constructor
    public Gametable(int numberPlayers) throws IOException {
        prepareGame(numberPlayers);
    }
    //first to do when preparing a game
    private void prepareGame(int numberPlayers) throws IOException {
        this.dicepool = new DicePool(18,18,18,18,18);
        this.tooldeck = new ToolCardsDeck();
        this.privategoalcardsdeck = new PrivateGoalCardDeck();
        this.publicGoalCardDeck = new PublicGoalCardDeck();
        this.schemeCardDeck = new SchemeCardDeck();
        this.roundTrack = new RoundTrack();
        this.numberPlayers = numberPlayers;

    }

    //to do when preparing a round
    public void setupRound() {
        this.roundDicepool = new DicePool();
        for (int i = 0; i < numberPlayers * 2 + 1; i++) {
            try{
                this.getRoundDicepool().addDice(this.getDicepool().extractDice());
            }catch(DicepoolIndexException e){
                System.err.println("Error in Gametable.setupRound");
            }
        }
    }

    //to do when a round ends
    public void endRound(int round)throws RoundTrackException{
        this.roundTrack.setRoundTrackDices(round, this.roundDicepool.getallDicesbutnotremove()) ;
        this.roundDicepool.removeallDices();
    }

    //per la gestione delle toolAction
    public void useaToolCard(ToolRequestClass toolRequestClass, Player player) throws Exception {
        this.tooldeck.doAction(toolRequestClass.getToolCardID(),player, toolRequestClass);
    }

    //to get the private Goal card
    public synchronized GoalCard getPrivateGoalCard() throws PrivateGoalCardException {
        return this.privategoalcardsdeck.getCard();
    }

    // Get public goal cards
    public synchronized PublicGoalCardDeck getPublicGoalCardDeck() {
        return publicGoalCardDeck;
    }


    //to get IDs and description and names of the public goals
    public List getPublicGoalCards() {
        return this.publicGoalCardDeck.getCards();
    }


    // Get functions useful for testing
    public ToolCardsDeck getToolCardsDeck() { return this.tooldeck; }
    public DicePool getDicepool() { return this.dicepool; }
    public DicePool getRoundDicepool() { return this.roundDicepool; }

    // to get a scheme card
    public SchemeCard getSchemeCard() throws IOException, MapConstrainReadingException {
        return this.schemeCardDeck.getCard();
    }



    //to calculate all points for all players, but only per the public goal card
    public void calculatePointsforAllPlayers(List<Player> players){
        for (Player player:  players) {
            this.publicGoalCardDeck.doCalculatePoints(player);
        }
    }

    public RoundTrack getRoundTrack() {
        return roundTrack;
    }

    public List getToolCards() {
        return this.tooldeck.getcards();
    }
}


