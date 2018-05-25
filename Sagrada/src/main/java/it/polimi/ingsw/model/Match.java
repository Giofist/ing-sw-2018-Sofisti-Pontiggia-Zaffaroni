package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.DrawException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;


import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

//manca solo il timer
public class Match implements Runnable{
    private String game_name;
    private LinkedList<Player> players;
    private Gametable gametable;
    private boolean started;
    


    //public constructor
    public Match(Player player, String game_name)  {
        this.game_name = game_name;
        this.players = new LinkedList<>();
        this.players.addFirst(player);
        player.setMatch(this);
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
        try {
            gametable = new Gametable(this.players.size());
            for (Player player : this.players) {
                boolean success = false;
                while (!success) {
                    try {
                        player.startGame(this.getGametable().getSchemeCard(), getGametable().getSchemeCard());
                        success = true;
                    } catch (MapConstrainReadingException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }catch(IOException e){
            try{
                for(Player player: this.players){
                    player.notifyError("Impossibile leggere il file delle mappe, la partita non può iniziare!\n");
                }
            }catch(RemoteException err){
                //do nothing, we are so unlucky here
            }
        }
        while(!everyonehassettheschemecard()){
            try {
                wait();
            }catch(InterruptedException e) {
                //do nothing
            }
        }
        //adesso la partita può avere inizio
        new Round(1, this.players, this).run();
        this.players.addLast(this.players.removeFirst());
        new Round(2, this.players, this).run();
        this.players.addLast(this.players.removeFirst());
        new Round(3, this.players, this).run();
        this.players.addLast(this.players.removeFirst());
        new Round(4, this.players, this).run();
        this.players.addLast(this.players.removeFirst());
        new Round(5, this.players, this).run();
        this.players.addLast(this.players.removeFirst());
        new Round(6, this.players, this).run();
        this.players.addLast(this.players.removeFirst());
        new Round(7, this.players, this).run();
        this.players.addLast(this.players.removeFirst());
        new Round(8, this.players, this).run();
        this.players.addLast(this.players.removeFirst());
        new Round(9, this.players, this).run();
        this.players.addLast(this.players.removeFirst());
        new Round(10, this.players, this).run();

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
            player.notifyEndMatch();
        }
        //qui bisognerà chiudere la partita in qualche modo



    }

    private synchronized boolean checkIsreadyToStart() {
        // qua bisognerà inserire un controllo dovuto al timer
        // ho messo due per semplicità nel testing
        if (this.players.size() == 2) {
            return true;
        } else return false;
    }
    private synchronized boolean everyonehassettheschemecard(){
        boolean returnValue = true;
        try{
            for (Player player: this.players){
                returnValue = returnValue && player.getScheme() != null;
            }
        }catch (SchemeCardNotExistantException e){
            return false;
        }
        return returnValue;
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
    public void leavethegameattheend(Player player){
        this.players.remove(player);
        if(getNumberOfPlayers()==0){
            MatchesList.singleton().remove(player.getMatch());
        }
    }

}
