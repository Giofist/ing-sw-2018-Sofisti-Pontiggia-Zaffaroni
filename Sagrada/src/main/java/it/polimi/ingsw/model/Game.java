package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.PrivateGoalCards.PrivateGoalCardDeck;
import it.polimi.ingsw.model.PublicGoalCards.PublicGoalCardDeck;
import sun.management.snmp.jvminstr.NotificationTargetImpl;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


//non terminata
//notate bene che da specifica il timer deve essere caricato da file
//l'idea è che ogni partita sia gestita da un thread, per questo impleemnta runnable e ha un metodo run
public class Game {
    private String game_name;
    private LinkedList<Player> players;
    private Gametable gametable;
    private PublicGoalCardDeck publicGoalCardDeck;


    //public constructor
    public Game(Player player, String game_name) throws RemoteException {
        this.game_name = game_name;
        this.players = new LinkedList<Player>();
        this.players.addFirst(player);
        player.setGame(this);
    }


    public synchronized Game join(Player player) {
        try {
            this.players.add(player);
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
        this.publicGoalCardDeck = gametable.getPublicGoalCardDeck();
        for (Player player : this.players) {
            boolean success = false;
            while (!success) {
                try {
                    player.notifyGameisStarting(getGametable().getSchemeCard(), getGametable().getSchemeCard());
                    success = true;
                } catch (MapConstrainReadingException e) {
                    System.out.println(e.getMessage());
                }
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

}
