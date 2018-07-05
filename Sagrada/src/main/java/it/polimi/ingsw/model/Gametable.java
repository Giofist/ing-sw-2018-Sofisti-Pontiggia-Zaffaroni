package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
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


    /**
     * Invokes the method prepareGame() with the number of players playing
     * @param numberPlayers How many players have to be in the match
     * @throws IOException
     */
    public Gametable(int numberPlayers) throws IOException {
        prepareGame(numberPlayers);
    }


    /**
     * This method prepares the Match in particular:
     * - A DicePool with 90 Dices (18 for each color) is prepared
     * - The decks of private and public goal cards are instantiated
     * - A deck with the ToolCards is instantiated
     * - A deck with the SchemeCards is instantiated
     * - A new RoundTrack is instantiated
     * @param numberPlayers How many players have to be in the match
     * @throws IOException
     */
    private void prepareGame(int numberPlayers) throws IOException {
        this.dicepool = new DicePool(18,18,18,18,18);
        this.tooldeck = new ToolCardsDeck();
        this.privategoalcardsdeck = new PrivateGoalCardDeck();
        this.publicGoalCardDeck = new PublicGoalCardDeck();
        this.schemeCardDeck = new SchemeCardDeck();
        this.roundTrack = new RoundTrack();
        this.numberPlayers = numberPlayers;
    }


    /**
     * This method is called at the beginning of each round and it prepares the RoundDicePool
     */
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


    /**
     * This method is called at the end of each round and it moves the remaining dices from the RoundDicePool to the
     * specified round of the RoundTrack
     * @param round Which round of the RoundTrack I want to move the Dices to
     * @throws RoundTrackException Exception thrown when an invalid RoundTrack index is passed
     */
    public void endRound(int round)throws RoundTrackException{
        this.roundTrack.setRoundTrackDices(round, this.roundDicepool.getallDicesbutnotremove()) ;
        this.roundDicepool.removeallDices();
    }


    /**
     * This method allows a player to use a tool card
     * @param toolRequestClass Class containing the tool card id with all the parameters necessary for the action to be performed
     * @param player The player who wants to use the tool card
     * @throws WrongToolCardIDException Exception thrown in case we pass a toolRequestClass with a tool card id not present in game
     * @throws ToolIllegalOperationException Exception thrown when something goes wrong in performing the action of the tool card
     * @throws NotEnoughTokenException Exception thrown when the player doesn't have enough Tokens to buy the tool card
     */
    public void useaToolCard(ToolRequestClass toolRequestClass, Player player) throws WrongToolCardIDException, ToolIllegalOperationException, NotEnoughTokenException {
        this.tooldeck.doAction(toolRequestClass.getToolCardID(),player, toolRequestClass);
    }


    /**
     * @return A random private goal card
     * @throws PrivateGoalCardException Exception thrown when we are not able to get the private goal card
     */
    public synchronized GoalCard getPrivateGoalCard() throws PrivateGoalCardException {
        return this.privategoalcardsdeck.getCard();
    }





    /**
     * @return A list of public goal cards for the match
     */
    public List getPublicGoalCards() {
        return this.publicGoalCardDeck.getCards();
    }



    protected ToolCardsDeck getToolCardsDeck() { return this.tooldeck; }


    /**
     * @return The match's DicePool
     */
    public DicePool getDicepool() { return this.dicepool; }


    /**
     * @return DicePool specific to the round
     */
    public DicePool getRoundDicepool() { return this.roundDicepool; }


    /**
     * @return A scheme card from the scheme cards deck
     * @throws IOException Exception thrown if there was a problem when reading from file
     * @throws MapConstrainReadingException Exception thrown when the read constrain is not evaluated well when trying to create the scheme card
     */
    public SchemeCard getSchemeCard() throws IOException, MapConstrainReadingException {
        return this.schemeCardDeck.getCard();
    }


    /**
     * Method called at the end of the match for calculating the points of each player
     * @param players List of players for which calculate the points
     */
    public void calculatePointsforAllPlayers(List<Player> players){
        for (Player player:  players) {
            this.publicGoalCardDeck.doCalculatePoints(player);
        }
    }


    /**
     * @return The RoundTrack of the current match
     */
    public RoundTrack getRoundTrack() {
        return roundTrack;
    }


    /**
     * @return A list of tool cards from the tool deck
     */
    public List getToolCards() {
        return this.tooldeck.getcards();
    }


    //for testing
    /**
     * @return A deck with public goal cards in it
     */
    public synchronized PublicGoalCardDeck getPublicGoalCardDeck() {
        return publicGoalCardDeck;
    }
}


