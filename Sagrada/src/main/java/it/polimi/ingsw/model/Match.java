package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.CardIdNotAllowedException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;
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

    @Override
    public void run(){
        synchronized (this){
            while (!isreadyTostart){
                try {
                    this.players.getLast().setPlayerState(State.MATCHNOTSTARTEDYETSTATE);
                    wait();
                }catch(InterruptedException e) {
                    return;
                }
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
                    }
                }
            }
        }catch(IOException e){
            //do something?
        }

        doneSignal = new CountDownLatch(this.getNumberOfPlayers());
        Timer timer = new Timer(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(90000);
                    if(doneSignal.getCount()==0){
                        return;
                    }
                    else for (Player player: players){
                        try{
                            player.getScheme();
                        }catch (SchemeCardNotExistantException e){
                            try{
                                player.setScheme(player.getExtractedSchemeCards().get(0).getID());
                            }catch(CardIdNotAllowedException er){
                                //do nothing, impossibile to get here
                            }
                        }
                    }
                    while(doneSignal.getCount() !=0){
                        doneSignal.countDown();
                    }
                    return;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        },0);
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
            player.setPlayerState(State.ENDMATCHSTATE);
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
            notifyAll();
        }

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



    public void leavethematch(Player player){
        this.players.remove(player);
        if(getNumberOfPlayers()==0){
            MatchesList.singleton().getmatches().remove(this);
        }
    }

    public void countDown() {
        this.doneSignal.countDown();
    }
    public void forceendmatch() {
        for(Player player: this.players){
            player.setPlayerState(State.FORCEENDMATCH);
        }
    }


    // For testing
    protected boolean getIsReadyToStart(){
        return isreadyTostart;
    }

    protected int getDoneSignal() {
        return (int) this.doneSignal.getCount();
    }
}
