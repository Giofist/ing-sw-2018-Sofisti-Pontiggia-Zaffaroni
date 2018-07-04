package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

//implementa comparable per ordinare i giocatori in base al punteggio nellav lista di player
public class Player  implements Comparable<Player>, Serializable {

    private int points;
    private String name;

    private transient int privateGoalCardpoints;
    private transient GoalCard privateGoalCard;
    private transient int token;
    private transient LinkedList<SchemeCard> extractedschemeCards;
    private transient SchemeCard scheme;
    private transient Match match;
    private transient Turn turn;
    private transient Observable playerState;
    private transient Dice diceforToolCard;
    private transient boolean mustpassTurn;

    /**
     * Creates a new player without an initial state
     */
    public Player(){
        this.diceforToolCard = null;
        this.mustpassTurn = false;
        this.points = 0;
        this.privateGoalCardpoints =0;
        this.extractedschemeCards = new LinkedList<>();
        this.scheme = null;
        this.token = 0;
        this.playerState = new PlayerState();
    }





    // Getters and setters methods
    /**
     * @return A string with the name of the player
     */
    public String getName(){
        return name;
    }


    /**
     * This method allows to add and remove points to the player for the private Goal
     * It's used only in compareTo in case two players have obtained the same points
     * @param points How many points we want to add or remove
     */
    public void addPrivateGoalCardpoints(int points){
        this.privateGoalCardpoints += points;
    }

    /**
     * @return Number of points of the player obtained for the private goal card
     */
    public int getPrivateGoalCardpoints() {
        return privateGoalCardpoints;
    }

    /**
     * @param name The name of the player
     */


    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return The number of Segnalini Favore
     */
    public int getToken() {
        return token;
    }


    /**
     * @param token How many Segnalini Favore needs to be set to the player
     */
    public void setToken(int token) {
        this.token = token;
    }


    /**
     * This method let the player pay for using the tool card
     * @param cost How many Segnalini Favori does the tool card costs
     * @throws NotEnoughTokenException Exception thrown if the player doesn't have sufficient points to pay for the tool card
     */
    public void payforToolAction(int cost)throws NotEnoughTokenException {
        if(this.getToken() < cost){
            throw new NotEnoughTokenException();
        }
        else {
            this.setToken(this.getToken()-cost);
        }
    }


    /**
     * @param privateGoalCard The private goal card that we want to assign to the player
     */
    public  void setPrivateGoalCard ( GoalCard privateGoalCard){
        this.privateGoalCard = privateGoalCard;
    }


    /**
     * @return The tool card assigned to the player
     */
    public GoalCard getPrivateGoalCard(){
        return this.privateGoalCard;
    }


    /**
     * This method allows to set the scheme card from the extracted ones
     * @param cardid Id of the scheme card to set
     * @throws CardIdNotAllowedException Exception thrown in case the specified id in not in the list of the available scheme cards
     */
    public  void setScheme ( int cardid) throws CardIdNotAllowedException{
        if (cardid == this.extractedschemeCards.getFirst().getID()){
            this.scheme = this.extractedschemeCards.getFirst();
        }else if (cardid == this.extractedschemeCards.getFirst().getTwinCard().getID()) {
            this.scheme = this.extractedschemeCards.getFirst().getTwinCard();
        }else if (cardid == this.extractedschemeCards.getLast().getID()){
            this.scheme = this.extractedschemeCards.getLast();
        }else if (cardid == this.extractedschemeCards.getLast().getTwinCard().getID()) {
            this.scheme = this.extractedschemeCards.getLast().getTwinCard();
        }else throw new CardIdNotAllowedException();
        this.token = this.scheme.getDifficulty();
        this.getMatch().countDown();
    }


    /**
     * @return The scheme card assigned to the player
     * @throws SchemeCardNotExistantException Exception thrown in case this method gets called when the player doesn't have any scheme card assigned to it
     */
    public SchemeCard getScheme() throws SchemeCardNotExistantException{
        if(this.scheme !=null){
            return this.scheme;
        }
        throw new SchemeCardNotExistantException(this);
    }


    /**
     * @return The gametable of the current match
     */
    public Gametable getGametable(){
        return this.getMatch().getGametable();
    }


    /**
     * This method allows to add and remove points to the player
     * @param points How many points we want to add or remove
     */
    public void addPoints(int points){
        this.points += points;
    }


    /**
     * @return Number of points of the player
     */
    public int getPoints(){
        return this.points;
    }


    /**
     * @return The match in which the player is in
     */
    public Match getMatch() {
        return this.match;
    }


    /**
     * @param match The match to assign to the player
     */
    public void setMatch(Match match) {
        this.match = match;
    }


    /**
     * @return A List of the random extracted scheme cards
     */
    public List<SchemeCard> getExtractedSchemeCards(){
        return this.extractedschemeCards;
    }


    // Methods for the attributes of the tool cards

    /**
     * This method is for setting a Dice to be used in some particular tool cards
     * @param dice The dice we want to set
     */
    public void setDiceforToolCardUse(Dice dice){
        this.diceforToolCard = dice;
    }


    /**
     * @return The dice that the special tool card needs to complete the action
     * @throws DiceNotExistantException Exception thrown if there isn't any dice to be used for the tool card set
     */
    public Dice getdiceforToolCardUse() throws DiceNotExistantException{
        if (this.diceforToolCard ==null){
            throw new DiceNotExistantException();
        } else {
            return this.diceforToolCard;
        }
    }


    /**
     * Sets to null the dice that the special tool card needs to complete the action
     */
    public void  removediceforToolCardUse(){
        this.diceforToolCard = null;
    }


    /**
     * @return Boolean value if the player is in a condition in which he is obliged to pass the turn
     */
    public boolean mustpassTurn() {
        return mustpassTurn;
    }


    /**
     * @param mustpassTurn Boolean value whether the player must be obliged to pass the turn
     */
    public void setMustpassTurn( boolean mustpassTurn) {
        this.mustpassTurn = mustpassTurn;
    }


    /**
     * This method adds a specified tool card to the list of extracted scheme cards only if the list contains 2 or less scheme cards
     * @param schemeCard The scheme card to be added
     */
    public void addExtractedSchemeCard(SchemeCard schemeCard){
        if(this.extractedschemeCards.size() <2){
            this.extractedschemeCards.add(schemeCard);
        }
        else return;
    }


    /**
     * @return The State in which the player is currently in
     */
    public Observable getPlayerState() {
        return playerState;
    }


    /**
     * @param playerState State to which we want to set the player at
     */
    public void setPlayerState(State playerState){
        this.playerState.setState(playerState);
        try{
            this.playerState.notifyObservers();
        }catch(RemoteException e){
            UsersList.Singleton().getUser(this.getName()).setActive(false);
        }
    }


    /**
     * @return The number of the turn to which the player is set
     */
    public Turn getTurn() {
        return turn;
    }


    /**
     * @param turn The number of the turn to set
     */
    public void setTurn(Turn turn){
        this.turn = turn;
    }


    /**
     * Method used to compare 2 players
     * @param player The player I want to compare with
     * @return A positive  value if this player cames before the param Player
     */
    @Override
    public int compareTo(Player player) {
        if(this.getPoints() != player.getPoints()){
            return this.getPoints() - player.getPoints();
        }
        else if(this.getPrivateGoalCardpoints() != player.getPrivateGoalCardpoints()){
            return this.getPrivateGoalCardpoints() - player.getPrivateGoalCardpoints();
        }
        else if (this.getToken() != player.getToken()){
            return this.getToken()- player.getToken();
        }
        else return player.getMatch().getallPlayers().indexOf(player) - this.getMatch().getallPlayers().indexOf(this);
    }

}