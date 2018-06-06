package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;


import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

//manca solo il timer
public class Match implements Runnable{
    private String game_name;
    private LinkedList<Player> players;
    private Gametable gametable;
    private boolean started;
    private CountDownLatch doneSignal;
    private Timer timer;
    public boolean isreadyTostart;




    //public constructor
    public Match(Player player, String game_name, Timer timer)  {
        this.game_name = game_name;
        this.players = new LinkedList<>();
        this.players.addFirst(player);
        player.setMatch(this);
        this.timer = timer;
        this.isreadyTostart = false;


    }

    public synchronized void run(){
        while (!checkIsreadyToStart()){
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
                        player.getAssociatedUser().setActive(false);
                        leavethematch(player);
                        success = true;
                    }
                }
            }
        }catch(IOException e){
            for(Player player: this.players){
                try{
                    player.setPlayerState(State.ERRORSTATE);//"Impossibile leggere il file delle mappe, la partita non può iniziare!\n");
                }catch(Exception err){
                    //do nothing, we are so unlucky here
                }
            }
        }
        doneSignal = new CountDownLatch(this.getNumberOfPlayers());
        try {
            doneSignal.await();

        }catch(InterruptedException e) {
            //do nothing
        }
        //adesso la partita può avere inizio
        for (int i = 1; i<=10; i++){
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
        //qualche altro conto da fare? Non ho letto tutto il regolamento

        //notifico ai vari giocatori la fine della partita dopo aver ordinato la lista in base al punteggio di ciascuno
        Collections.sort(this.players);
        for (Player player:this.players) {
            try{
                player.setPlayerState(State.ENDMATCHSTATE);
            }catch (RemoteException e){
                leavethematchatthend(player);
            }
        //qui bisognerà chiudere la partita in qualche modo
        }
    }


    private synchronized boolean checkIsreadyToStart() {
        // qua bisognerà inserire un controllo dovuto al timer
        // ho messo due per semplicità nel testing
        if (this.players.size() == 1) {
            return true;
        } else return false;
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
        notifyAll();
    }

    //assolutamente da testare
    //come esegue la remove? Correttamente?
    //molto utile per il pattern observer, per non mettere dei riferimenti agli altri player
    //all'interno della classe player
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
            MatchesList.singleton().remove(player.getMatch());
        }
    }

    public void leavethematch(Player player){
        this.players.remove(player);
    }

    public String getfinalRanking() {
        String string = " ";
        for(Player player: this.players){
            string += player.toString() + player.getPoints() + "\n";
        }
        return string;
    }

    public void countDown() {
        this.doneSignal.countDown();
    }

    public void forceendmatch() {

    }
    @Override
    public  String toString(){
        String string = "";
        string += this.game_name + " " + "Giocatori in questa partita:";
        for (Player player: this.players){
            string += player.toString();
        }
        return string;
    }
}
