package it.polimi.ingsw.model;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.Turn.PlayerState;
import it.polimi.ingsw.model.Turn.Turn;

import java.util.LinkedList;

//implementa comparable per ordinare i giocatori in base al punteggio nellav lista di player
public class Player extends Observable implements Comparable<Player>{
    private User user;
    private GoalCard privateGoalCard;
    private int segnalini_favore;
    private LinkedList<SchemeCard> extractedschemeCards;
    private SchemeCard scheme;
    private int points;
    private Match match;
    private PlayerState playerState;
    private Turn turn;






    //per la gestione delle toolCard, potremo pensare ad un'ottimizzazione
    private Dice diceforDiluenteperPastaSalda;
    private boolean mustsetdice;
    private int numberoftimesyouhaveplayedthisround;
    private DiceColor colorConstrainForTaglierinaManuale;
    private boolean mustpassTurn;


    //costruttore
    public Player(){
        this.diceforDiluenteperPastaSalda = null;
        this.mustsetdice = false;
        this.numberoftimesyouhaveplayedthisround=0;
        this.colorConstrainForTaglierinaManuale = null;
        this.observerViewInterfaces = new LinkedList<>();
        this.feedObserverViews = new LinkedList<>();
        this.mustpassTurn = false;
        this.points = 0;
        this.extractedschemeCards = new LinkedList<>();
        this.scheme = null;
    }





    //metodi setter e getter
    public int getSegnalini_favore() {
        return segnalini_favore;
    }
    public void setSegnalini_favore(int segnalinifavore) {
        this.segnalini_favore = segnalinifavore;
    }
    public void payforTool(int cost)throws NotEnoughSegnaliniException {
        if(this.getSegnalini_favore() < cost){
            throw new NotEnoughSegnaliniException();
        }
        else {
            this.setSegnalini_favore(this.getSegnalini_favore()-cost);
        }
    }
    public synchronized void setPrivateGoalCard ( GoalCard privateGoalCard){
        this.privateGoalCard = privateGoalCard;
    }
    public GoalCard getPrivateGoalCard(){
        return this.privateGoalCard;
    }
    public void setScheme ( int cardid) throws CardIdNotAllowedException{
        if (cardid == this.extractedschemeCards.getFirst().getID()){
            this.scheme = this.extractedschemeCards.getFirst();
        }else if (cardid == this.extractedschemeCards.getFirst().getTwinCard().getID()) {
            this.scheme = this.extractedschemeCards.getFirst().getTwinCard();
        }else if (cardid == this.extractedschemeCards.getLast().getID()){
            this.scheme = this.extractedschemeCards.getLast();
        }else if (cardid == this.extractedschemeCards.getLast().getTwinCard().getID()) {
            this.scheme = this.extractedschemeCards.getLast().getTwinCard();
        }else throw new CardIdNotAllowedException();
        this.getMatch().update();
    }
    public SchemeCard getScheme() throws SchemeCardNotExistantException{
        if(this.scheme !=null){
            return this.scheme;
        }
        throw new SchemeCardNotExistantException(this);
    }
    public String getSchemeCardRappresentation  () throws SchemeCardNotExistantException{
        return getScheme().displayScheme();
    }
    public Gametable getGametable(){
        return getMatch().getGametable();
    }
    public void addPoints(int points){
        this.points += points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getPoints(){
        return this.points;
    }
    public User getAssociatedUser(){ return this.user; }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }
    public void setMatch(Match match) {
        this.match = match;
    }
    public String getExtractedSchemeCards(){
        String stringToreturn = "\n";
        int index = 1;
        for (SchemeCard schemeCard: this.extractedschemeCards) {
            stringToreturn += schemeCard.displayScheme();
            stringToreturn += "!";
            stringToreturn += schemeCard.getTwinCard().displayScheme();
            if (index < this.extractedschemeCards.size()){
                stringToreturn += "!";
            }
            index++;
        }
        return stringToreturn;
    }

    //metodi per gli attributi per le toolcard
    public void setDiceforDiluenteperPastaSalda(Dice dice){
        this.diceforDiluenteperPastaSalda = dice;
    }
    public Dice getdiceforDiluenteperPastaSalda() throws DiceNotExistantException{
        if (this.diceforDiluenteperPastaSalda ==null){
            throw new DiceNotExistantException();
        }
        else {
            return this.diceforDiluenteperPastaSalda
            ;
        }
    }
    public void  removediceforDiluenteperPastaSalda(){
        this.diceforDiluenteperPastaSalda = null;
    }
    public void setMustsetdice(boolean settable){
        this.mustsetdice = settable;
    }
    public boolean getMustsetDice(){
        return this.mustsetdice;
    }
    public int getNumberoftimesyouhaveplayedthisround() {
        return numberoftimesyouhaveplayedthisround;
    }
    public void setNumberoftimesyouhaveplayedthisround(int numberoftimesyouhaveplayedthisround) {
        this.numberoftimesyouhaveplayedthisround += 1;
    }
    public DiceColor getColorConstrainForTaglierinaManuale() {
        return colorConstrainForTaglierinaManuale;
    }
    public void setColorConstrainForTaglierinaManuale(DiceColor dicecolor){
        this.colorConstrainForTaglierinaManuale = dicecolor;
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
    public void setPlayerState(PlayerState playerState){
        this.playerState = playerState;
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
        return this.getPoints() - player.getPoints();
    }

    @Override
    public String toString(){
        return this.getAssociatedUser().getName() + " " + this.getPoints() + "\n";
    }


}