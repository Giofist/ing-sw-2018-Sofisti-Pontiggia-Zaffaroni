package it.polimi.ingsw.model.PlayerPackage;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.CardIdNotAllowedException;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

//implementa comparable per ordinare i giocatori in base al punteggio nellav lista di player
public class Player  implements Comparable<Player> {
    private transient User user;
    private GoalCard privateGoalCard;
    private int token;
    private LinkedList<SchemeCard> extractedschemeCards;
    private SchemeCard scheme;
    private int points;
    private Match match;
    private PlayerState playerState;
    private Turn turn;






    //per la gestione delle toolCard, potremo pensare ad un'ottimizzazione
    private Dice diceforToolCard;
    private boolean mustpassTurn;

    //costruttore
    public Player(){
        super();
        this.diceforToolCard = null;
        this.mustpassTurn = false;
        this.points = 0;
        this.extractedschemeCards = new LinkedList<>();
        this.scheme = null;
        this.playerState = new PlayerState();
        this.token = 0;

    }





    //metodi setter e getter

    public int getToken() {
        return token;
    }
    public void setToken(int token) {
        this.token = token;
    }
    public void payforToolAction(int cost)throws NotEnoughSegnaliniException {
        if(this.getToken() < cost){
            throw new NotEnoughSegnaliniException();
        }
        else {
            this.setToken(this.getToken()-cost);
        }
    }
    public  void setPrivateGoalCard ( GoalCard privateGoalCard){
        this.privateGoalCard = privateGoalCard;
    }
    public GoalCard getPrivateGoalCard(){
        return this.privateGoalCard;
    }
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
    public SchemeCard getScheme() throws SchemeCardNotExistantException{
        if(this.scheme !=null){
            return this.scheme;
        }
        throw new SchemeCardNotExistantException(this);
    }
    public Gametable getGametable(){
        return this.getMatch().getGametable();
    }
    public void addPoints(int points){
        this.points += points;
    }
    public int getPoints(){
        return this.points;
    }
    public User getAssociatedUser(){ return this.user; }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return this.match;
    }
    public void setMatch(Match match) {
        this.match = match;
    }
    public List<SchemeCard> getExtractedSchemeCards(){
        return this.extractedschemeCards;
    }

    //metodi per gli attributi per le toolcard
    public void setDiceforToolCardUse(Dice dice){
        this.diceforToolCard = dice;
    }
    public Dice getdiceforToolCardUse() throws DiceNotExistantException{
        if (this.diceforToolCard ==null){
            throw new DiceNotExistantException();
        }
        else {
            return this.diceforToolCard;

        }
    }
    public void  removediceforToolCardUse(){
        this.diceforToolCard = null;
    }

    public boolean mustpassTurn() {
        return mustpassTurn;
    }
    public void setMustpassTurn( boolean mustpassTurn) {
        this.mustpassTurn = mustpassTurn;
    }
    public void addExtractedSchemeCard(SchemeCard schemeCard){
        this.extractedschemeCards.add(schemeCard);
    }
    public PlayerState getPlayerState(){
        return this.playerState;
    }
    public void setPlayerState(State state)throws RemoteException{
        this.playerState.updateState(state);
        this.playerState.notifyObservers();
    }
    public Turn getTurn() {
        return turn;
    }
    public void setTurn(Turn turn){
        this.turn = turn;
    }

    /// /utile per ordinare i giocatori in base al punteggio
    @Override
    public int compareTo(Player player) {
        if(this.getPoints() != player.getPoints()){
            return this.getPoints() - player.getPoints();
        }
        else if (this.getToken() != player.getToken()){
            return this.getToken() - this.getToken();
        }
        else return this.getMatch().getallPlayers().indexOf(this) - player.getMatch().getallPlayers().indexOf(player);
    }

    @Override
    public String toString(){
        return this.getAssociatedUser().getName();
    }


}