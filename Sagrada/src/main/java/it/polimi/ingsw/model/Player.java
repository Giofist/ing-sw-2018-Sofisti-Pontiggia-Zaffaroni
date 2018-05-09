package it.polimi.ingsw.model;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.NotEnoughSegnaliniException;
import it.polimi.ingsw.model.Exceptions.PlayerNotFoundException;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.net.Socket;
import java.util.*;

public class Player{
    private User user;
    private GoalCard privateGoalCard;
    private int segnalini_favore;
    private SchemeCard scheme;
    private LinkedList<Player> state;
    private Gametable gametable;
    private int points;
    private Dice diceforDiluenteperpastaSalda;
    private boolean mustsetdice;


    //costruttore
    public Player(User user){
        this.user = user;
        this.state = new LinkedList<Player>();
        this.diceforDiluenteperpastaSalda = null;
        this.mustsetdice = false;
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
    public List<Player> getState(){
        return this.state;
    }
    public void addPlayerToState(Player player){
        this.state.add(player);
    }
    public User getAssociatedUser(){ return this.user; }
    public void setDiceforDiluenteperpastaSalda(Dice dice){
        this.diceforDiluenteperpastaSalda = dice;
    }
    public Dice getdiceforDiluenteperPastaSalda() throws DiceNotExistantException{
        if (this.diceforDiluenteperpastaSalda ==null){
            throw new DiceNotExistantException();
        }
        else {
            return this.diceforDiluenteperpastaSalda
            ;
        }

    }
    public void  removediceforDiluenteperPastaSalda(){
        this.diceforDiluenteperpastaSalda = null;
    }


    public Player getSelectedPlayer (String name) throws PlayerNotFoundException {
        for (Player other_player : this.state) {
            if (other_player.getAssociatedUser().getName().equals(name))
                return other_player;
        }
        throw new PlayerNotFoundException();
    }

    public void setMustsetdice(boolean settable){
        this.mustsetdice = settable;
    }
    public boolean getMustsetDice(){
        return this.mustsetdice;
    }



    //implementation of the observer design pattern
    public void update(){
        //not implemented yet
    }


}