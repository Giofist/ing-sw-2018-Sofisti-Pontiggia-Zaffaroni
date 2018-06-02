package it.polimi.ingsw.model.Turn;

import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.*;
import it.polimi.ingsw.model.Round;

public class Turn {

    private Player currentPlayer;
    private Round round;
    private int turnID;

    public Turn(Player player, Round round, int turnID) {
        this.currentPlayer = player;
        this.round = round;
        this.turnID = turnID;
    }

    public synchronized void run(){
        if (currentPlayer.mustpassTurn()){
            currentPlayer.setPlayerState(State.NOTYOURTURNSTATE);
        }else{
            currentPlayer.setPlayerState(State.STARTTURNSTATE);
        }
        currentPlayer.setTurn(this);
        currentPlayer.notifyObservers();
        try{
            wait();
        }catch(InterruptedException e){
            // do nothing
        }

        currentPlayer.setPlayerState(State.NOTYOURTURNSTATE);
        return;
    }

    public int getTurnID() {
        return turnID;
    }
}
