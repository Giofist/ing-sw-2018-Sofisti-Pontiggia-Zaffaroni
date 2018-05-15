package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.DrawException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.PrivateGoalCards.PrivateGoalCardDeck;
import it.polimi.ingsw.model.PublicGoalCards.PublicGoalCardDeck;


import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;


//non terminata
//notate bene che da specifica il timer deve essere caricato da file
//l'idea è che ogni partita sia gestita da un thread, per questo impleemnta runnable e ha un metodo run
public class Game {
    private String game_name;
    private LinkedList<Player> players;
    private Gametable gametable;
    


    //public constructor
    public Game(Player player, String game_name) throws RemoteException {
        this.game_name = game_name;
        this.players = new LinkedList<>();
        this.players.addFirst(player);
        player.setGame(this);
    }


    public synchronized Game join(Player player)  throws RemoteException{
        try {
            this.players.addLast(player);
            player.setGame(this);
            if (checkIsready()) {
                this.start();
            }
        } catch (IOException e) {
            for (Player plaier : this.players) {
                plaier.notifyError("Errore nel caricamento delle mappe\n");
            }
        }
        return this;
    }

    private boolean checkIsready() {
        // qua bisognerà inserire un controllo dovuto al timer
        if (this.players.size() == 4) {
            return true;
        } else return false;
    }




    public void start() throws IOException {
        gametable = new Gametable(this.players.size());
        
        for (Player player : this.players) {
            boolean success = false;
            while (!success) {
                try {
                    // questo è per notificare che la partita sta per iniziare
                    //nella notifica viene chiesto a ciascun utente di settare una schemecard scegliendola tra due
                    // in realtà la scelta è tra quattro, perchè ogni scheme card ha due facce
                    //notifyGameISStarting fa aggiungere al playerv anche una schemecard
                    player.notifyGameisStarting(getGametable().getSchemeCard(), getGametable().getSchemeCard());
                    success = true;
                } catch (MapConstrainReadingException e) {
                    //qui ho pensato che sul server fosse utile permettere all'amministratore di sistema poter gestire
                    //i casi in cui le mappe non vengono lette correttamente, per poterle correggere manualmente
                    System.out.println(e.getMessage());
                }
            }

        }
        //la partita può avere inizio.
        this.run();

    }
    
    //this is THE GAME: 10 ROUNDS
    private void run()throws RemoteException{
        //ad ogni round modifico l'ordine nella lista
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
        endGame();
    }

    
    //when the match ends, we need to:
    // calculate points for the privategoalcard and for the public goal card
    //notify all players that the match is ended
    //choose a winner and notify he has won
    //notify the others that theey have lost
    // what else?
    private void endGame()throws RemoteException{
        //to calculate points for the public goals
        this.getGametable().calculatePointsforAllPlayers(this.players);

        // to calculate points for the private
        for (Player player:this.players) {
            player.getPrivateGoalCard().calculatepoint(player);
        }
        //qualche altro conto da fare? Non ho letto tutto il regolamento

        //notifico ai vari giocatori la vittoria, il pareggio eventuale e la sconfitta
        try{
            Player winnerPlayer = whohaswon();
            winnerPlayer.notifyaWin();
            for (Player loser:this.getallPlayersbutnotme(winnerPlayer)) {
                loser.notifyaLose();
            }


        }catch (DrawException e){
            for (Player player: this.players) {
                if(player.getPoints() == e.getPointsofthedraw()){
                    player.notifyaDraw();
                }else{
                    player.notifyaLose();
                }

            }
        }

        //qui bisognerà chiudere la partita in qualche modo



    }


    //per decidere chi ha vinto
    //anche questa classe è assolutamente da testatr
    private Player whohaswon() throws DrawException {
        //sort è un metodo che ordina in base all'override che ho fatto di compareTo nella classe player, cioè in base
        //al PUNTEGGIO
       Collections.sort(this.players);
       if (this.players.get(0).getPoints()== this.players.get(1).getPoints()){
           throw new DrawException(this.players.get(0).getPoints(),"C'è stato un pareggio a" + this.players.get(0).getPoints() + "\n");
       }else{
           return this.players.getFirst();
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


    //assolutamente da testare
    //come esegue la remove? Correttamente?
    //molto utile per il pattern observer, per non mettere dei riferimenti agli altri player
    //all'interno della classe player
    public LinkedList<Player> getallPlayersbutnotme(Player player){
        LinkedList<Player> list = new LinkedList<>();
        list.addAll(this.players);
        list.remove(player);
        return list;
    }

    //da chiamare alla fine, quando un client vuole loasciare una partita e per esempio aggiungersi ad un'altra
    public void leavethegameattheend(Player player){
        this.players.remove(player);
        if(getNumberOfPlayers()==0){
            //not implemented yet
            //andrà a cancellare la partita da gameslist
        }
    }

}
