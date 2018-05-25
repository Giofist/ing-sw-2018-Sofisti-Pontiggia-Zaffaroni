package it.polimi.ingsw.model;
import it.polimi.ingsw.ClientView.FeedObserverView;
import it.polimi.ingsw.ClientView.ObserverViewInterface;
import it.polimi.ingsw.model.Exceptions.CardIdNotAllowedException;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.rmi.RemoteException;
import java.util.LinkedList;

//implementa comparable per ordinare i giocatori in base al punteggio nellav lista di player
public class Player implements Comparable<Player>{
    private User user;
    private GoalCard privateGoalCard;
    private int segnalini_favore;
    private LinkedList<SchemeCard> extractedschemeCards;
    private SchemeCard scheme;
    private int points;
    private Match match;




    //per il pattern observer
    private LinkedList<ObserverViewInterface> observerViewInterfaces;
    private LinkedList<FeedObserverView> feedObserverViews;

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
    public void setPrivateGoalCard ( GoalCard privateGoalCard){
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
    }
    public SchemeCard getScheme(){
        return this.scheme;
    }
    public String getSchemeCardRappresentation (){
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
        for (SchemeCard schemeCard: this.extractedschemeCards) {
            stringToreturn += schemeCard.displayScheme();
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
    public boolean MustpassTurn() {
        return mustpassTurn;
    }
    public void setMustpassTurn( boolean mustpassTurn) {
        this.mustpassTurn = mustpassTurn;
    }


    //metodo per l'oberserver design pattern
    public void feedObserverViews(FeedObserverView client) {
        this.feedObserverViews.add(client);
    }
    public void observerViews(ObserverViewInterface client){
        this.observerViewInterfaces.add(client);
    }
    //tutti questi metodi chiamano qualcosa della view tramite gli observer pattern
    public void notifyError(String message)  throws RemoteException{
        for(ObserverViewInterface observerViewInterface : this.observerViewInterfaces){
            observerViewInterface.showErrorMessage(message);
        }
    }
    public void startGame(SchemeCard schemeCard1, SchemeCard schemeCard2)throws RemoteException{
        try{
            this.extractedschemeCards.add(schemeCard1);
            this.extractedschemeCards.add(schemeCard2);
            //all'inizio della partita viene anche aggiunta ad ogni player una carta obiettivo privato
            setPrivateGoalCard(getMatch().getGametable().getPrivateGoalCard());
            for(ObserverViewInterface observerViewInterface : this.observerViewInterfaces){
                observerViewInterface.notifyGameisStarting(this.getMatch().getName());
            }
        }catch(PrivateGoalCardException e){
            notifyError(e.getMessage());
        }
    }
    /// /utile per ordinare i giocatori in base al punteggio
    @Override
    public int compareTo(Player player) {
        return this.getPoints() - player.getPoints();
    }
}