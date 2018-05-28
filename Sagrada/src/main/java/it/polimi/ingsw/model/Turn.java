package it.polimi.ingsw.model;

import java.rmi.RemoteException;
import java.util.List;

public class Turn {

    private Player currentPlayer;
    private Match match;
    private List<Player> otherPlayers;

    Turn(Player player, Match match) {
        this.currentPlayer = player;
        this.match = match;
        otherPlayers = this.match.getallPlayersbutnotme(currentPlayer);
    }

    public void run() throws RemoteException, InterruptedException {
        // Per prima cosa notifico tutti i giocatori di chi è il turno
        for (Player otherPlayer: otherPlayers) {
            otherPlayer.notifyError("Sei in attesa. E' il turno di \"" + currentPlayer + "\"");
        }

        // Avviso il giocatore che deve giocare
        currentPlayer.notifyIsYourTurn();

        Thread.sleep(7000);
    }
}
