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
    private Gametable gametable;


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
    public void payforTool(int cost)throws NotEnoughSegnaliniException{
        if(this.getSegnalini_favore() < cost){
            throw new NotEnoughSegnaliniException();
        }
        else {
            this.setSegnalini_favore(this.getSegnalini_favore()-cost);
        }
    }
    public void setPublicGoalCards(LinkedList<GoalCard> publicGoalCards) {
        this.publicGoalCards = publicGoalCards;
    }
    public LinkedList<GoalCard> getPublicGoalCards(){
        return this.publicGoalCards;
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
    public Socket getSOcket(){
        return this.user.getSocket();
    }
    public Gametable getGametable(){
        return this.gametable;
    }
    public void setGametable(Gametable gametable) {
        this.gametable = gametable;
    }
    public List<Player> getState(){
        return this.state;
    }
    public void addPlayer(Player player){
        this.state.add(player);
    }
    public Player getSelectedPlayer (String name) throws PlayerNotFoundException {
        for (Player other_player : this.state) {
            if (other_player.user.getName().equals( name))
                return other_player;
        }
        throw new PlayerNotFoundException();
    }



    //implementation of the observer design pattern
    public void update(){
        //not implemented yet
    }




}