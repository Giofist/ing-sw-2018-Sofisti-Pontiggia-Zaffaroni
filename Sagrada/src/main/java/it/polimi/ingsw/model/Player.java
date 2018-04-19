package it.polimi.ingsw.model;
import java.net.Socket;
import java.util.*;

public class Player{
    private LinkedList<Dice> dices = new LinkedList<Dice>();
    private User user;
    private LinkedList<GoalCard> publicGoalCards;
    private GoalCard privateGoalCard;
    private int segnalini_favore;
    private SchemeCard scheme;
    private List<Player> state;


    //costruttore
    public Player(User user){
        this.user = user;
    }

    //metodi setter e getter
    private void setDices(Dice dice){
        this.dices.add(dice);
    }
    public Dice getDice() {
        return dices.removeFirst();
    }
    public int getSegnalini_favore() {
        return segnalini_favore;
    }
    public void setSegnalini_favore(int segnalini_favore) {
        this.segnalini_favore = segnalini_favore;
    }
    private void setPublicGoalCards(LinkedList<GoalCard> publicGoalCards) {
        this.publicGoalCards = publicGoalCards;
    }
    private LinkedList<GoalCard> getPublicGoalCards(){
        return this.publicGoalCards;
    }
    private void setPrivateGoalCard ( GoalCard privateGoalCard){
        this.privateGoalCard = privateGoalCard;
    }
    private GoalCard getPrivateGoalCard(){
        return this.privateGoalCard;
    }
    private void setScheme ( SchemeCard scheme){
        this.scheme = scheme;
    }
    private SchemeCard getScheme(){
        return this.scheme;
    }
    private Socket getSOcket(){
        return this.user.getSocket();
    }
    private void update(){

    };




}