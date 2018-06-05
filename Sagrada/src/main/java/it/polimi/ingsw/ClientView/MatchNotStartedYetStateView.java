package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class MatchNotStartedYetStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    public MatchNotStartedYetStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    @Override
    public void run() {
        System.out.println("Attendi che gli altri giocatori entrino in partita...");
    }
}
