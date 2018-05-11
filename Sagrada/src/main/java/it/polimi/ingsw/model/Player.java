package it.polimi.ingsw.model;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.net.Socket;

//abbiamo il problema del pattern observer
public class Player{
    private User user;
    private GoalCard privateGoalCard;
    private int segnalini_favore;
    private SchemeCard scheme;
    private Gametable gametable;
    private int points;


    //pervla gestione delle toolCard, potremo pensare ad un'ottimizzazione
    private Dice diceforDiluenteperPastaSalda;
    private boolean mustsetdice;
    private int numberoftimesyouhaveplayedthisround;
    private DiceColor colorConstrainForTaglierinaManuale;


    //costruttore
    public Player(User user){
        this.user = user;
        this.diceforDiluenteperPastaSalda = null;
        this.mustsetdice = false;
        this.numberoftimesyouhaveplayedthisround=0;
        this.colorConstrainForTaglierinaManuale = null;
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
        return this.gametable;
    }
    public void setGametable(Gametable gametable) {
        this.gametable = gametable;
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


}