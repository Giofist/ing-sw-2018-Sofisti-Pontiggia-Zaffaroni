package it.polimi.ingsw.model;
import java.net.Socket;
import java.util.*;

public class Player{
    private User user;
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
    public int getSegnalini_favore() {
        return segnalini_favore;
    }
    public void setSegnalini_favore(int segnalinifavore) {
        this.segnalini_favore = segnalinifavore;
    }
    public void payforTool(int cost)throws NotEnoughSegnaliniException{
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