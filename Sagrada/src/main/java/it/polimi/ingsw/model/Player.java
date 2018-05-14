package it.polimi.ingsw.model;
import it.polimi.ingsw.ClientController.FeedObserverView;
import it.polimi.ingsw.ClientController.ObserverViewInterface;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.net.Socket;
import java.rmi.RemoteException;
import java.util.LinkedList;


public class Player implements Comparable<Player>{
    private User user;
    private GoalCard privateGoalCard;
    private int segnalini_favore;
    private SchemeCard scheme;
    private int points;
    private Game game;




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
    public void setScheme ( SchemeCard scheme){
        this.scheme = scheme;
    }
    public SchemeCard getScheme(){
        return this.scheme;
    }
    public Socket getSocket(){
        return this.user.getSocket();
    }
    public Gametable getGametable(){
        return getGame().getGametable();
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

    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
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
    //tutti questi metodi chiamano qualcosa della view tramite gli observer pattern
    public void feedObserverViews(FeedObserverView client) {
        this.feedObserverViews.add(client);
    }

    public void observerViews(ObserverViewInterface client){
        this.observerViewInterfaces.add(client);
    }

    public void notifyError(String message){
        for(ObserverViewInterface observerViewInterface : this.observerViewInterfaces){
            observerViewInterface.showErrorMessage(message);
        }
    }

    public void notifyGameisStarting(SchemeCard scheme1, SchemeCard scheme2)throws RemoteException{
        try{
            for(ObserverViewInterface observerViewInterface : this.observerViewInterfaces){
                observerViewInterface.showSchemeCards(scheme1,scheme2);
            }
            setPrivateGoalCard(getGame().getGametable().getPrivateGoalCard());
        }catch(PrivateGoalCardException e){
            notifyError(e.getMessage());
        }
    }

    public void notifyaDraw(){
        for (ObserverViewInterface observerview: this.observerViewInterfaces) {
            observerview.notifyaDraw();
        }
    }

    public void notifyaLose(){
        for (ObserverViewInterface observerview: this.observerViewInterfaces) {
            observerview.notifyaLose();
        }
    }

    public void notifyaWin(){
        for (ObserverViewInterface observerview: this.observerViewInterfaces) {
            observerview.notifyaWin();
        }
    }

    /// /utile per ordinare i giocatori in base al punteggio
    @Override
    public int compareTo(Player player) {
        return this.getPoints() - player.getPoints();
    }
}