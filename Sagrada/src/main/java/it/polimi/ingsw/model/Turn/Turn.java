package it.polimi.ingsw.model.Turn;

import it.polimi.ingsw.model.Player;
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
            currentPlayer.setPlayerState(new MustPassTurnState());
        }else{
            currentPlayer.setPlayerState(new StartTurnState());
        }
        currentPlayer.setTurn(this);
        currentPlayer.notifyIsYourTurn();
        try{
            wait();
        }catch(InterruptedException e){
            // do nothing
        }

        currentPlayer.setPlayerState(new NotYourTurnState());


    }

    public int getTurnID() {
        return turnID;
    }
}
