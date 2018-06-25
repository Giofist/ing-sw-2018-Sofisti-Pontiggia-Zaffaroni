package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.Exceptions.UserNotExistentException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

//manca solo il timer
public class Match implements Runnable,Serializable{
    private String game_name;
    private LinkedList<Player> players;
    private transient Gametable gametable;
    private boolean started;
    private transient CountDownLatch doneSignal;
    public boolean isreadyTostart;


    //public constructor
    public Match(Player player, String game_name)  {
        this.game_name = game_name;
        this.players = new LinkedList<>();
        this.players.addFirst(player);
        player.setMatch(this);
        this.isreadyTostart = false;
    }

    public synchronized void run(){
        while (!isreadyTostart){
            try {
                wait();
            }catch(InterruptedException e) {
                //do nothing
            }
        }
        //the match can start
        this.setStarted(true);
        try {
            gametable = new Gametable(this.players.size());
            for (Player player : this.players) {
                boolean success = false;
                while (!success) {
                    try {
                        player.setPrivateGoalCard(getGametable().getPrivateGoalCard());
                        player.addExtractedSchemeCard(getGametable().getSchemeCard());
                        player.addExtractedSchemeCard(getGametable().getSchemeCard());
                        player.setPlayerState(State.MUSTSETSCHEMECARD);
                        success = true;
                    } catch (MapConstrainReadingException e) {
                        System.out.println(e.getMessage());
                    }catch(PrivateGoalCardException e){
                        // do nothing
                    }catch (RemoteException e){
                        try{
                            UsersList.Singleton().getUser(player.getName()).setActive(false);
                        }catch(Exception err){
                            //do nothing
                        }
                        //se uno non setta la carta schema non può giocare
                        this.players.remove(player);
                        try{
                            UsersList.Singleton().getUser(player.getName()).removePlayer();
                        }catch(UserNotExistentException err){
                            //do nothing
                        }
                        success = true;
                    }
                }
            }
        }catch(IOException e){
            //do something?
        }
        doneSignal = new CountDownLatch(this.getNumberOfPlayers());
        try {
            doneSignal.await();

        }catch(InterruptedException e) {
            //do nothing
        }
        //adesso la partita può avere inizio
        for (int i = 1; i<=1; i++){
            new Round(i, this.players, this).run();
            //ad ogni ciclo for devo cambiare l'ordine di inizio round
            this.players.addLast(this.players.removeFirst());
        }

        //to calculate points for the public goals
        this.getGametable().calculatePointsforAllPlayers(this.players);

        // to calculate points for the private goals
        for (Player player:this.players) {
            player.getPrivateGoalCard().calculatepoint(player);
        }


        //notifico ai vari giocatori la fine della partita dopo aver ordinato la lista in base al punteggio di ciascuno
        Collections.sort(this.players);
        for (Player player:this.players) {
            try{
                player.setPlayerState(State.ENDMATCHSTATE);
            }catch (RemoteException e){
                try{
                    UsersList.Singleton().getUser(player.getName()).setActive(false);
                }catch(Exception err){
                    //do nothing
                }
                leavethematchatthend(player);
            }
        }
    }





    //metodi getter e setter
    public String getName() {
        return this.game_name;
    }
    public int getNumberOfPlayers() {
        return this.players.size();
    }
    public Gametable getGametable() {
        return gametable;
    }
    public boolean isStarted() {
        return started;
    }
    public void setStarted(boolean started){
        this.started= started;
    }
    public synchronized void join(Player player){
        this.players.addLast(player);
        if (this.players.size() == 2){
            isreadyTostart = true;
        }
        notifyAll();
    }

    public List<Player> getallPlayersbutnotme(Player player){
        LinkedList<Player> list = new LinkedList<>();
        list.addAll(this.players);
        list.remove(player);
        return list;
    }

    public List<Player> getallPlayers(){
        LinkedList<Player> list = new LinkedList<>();
        list.addAll(this.players);
        return list;
    }


    //da chiamare alla fine, quando un client vuole loasciare una partita e per esempio aggiungersi ad un'altra
    public  void leavethematchatthend(Player player){
        this.players.remove(player);
        if(getNumberOfPlayers()==0){
            MatchesList.singleton().remove(this);
        }
    }

    public void leavethematch(Player player){
        this.players.remove(player);
        if(getNumberOfPlayers()==0){
            MatchesList.singleton().remove(this);
        }
    }


    public void countDown() {
        this.doneSignal.countDown();
    }

    public void forceendmatch() {
        for(Player player: this.players){
            try{
                player.setPlayerState(State.FORCEENDMATCH);
            }catch(RemoteException e){
                try{
                    UsersList.Singleton().getUser(player.getName()).setActive(false);
                }catch(Exception err){
                    //do nothing
                }
            }
        }
        MatchesList.singleton().remove(this);
    }


}
