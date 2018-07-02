package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

/**
 * This view for the CLI is displayed when the the player created a new match and he has to wait for other players in order
 * to be able to start the match
 */
public class MatchNotStartedYetStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;


    /**
     * @param serverController The reference to the server's controller for invoking its methods
     * @param yourName The username of the account performing the requests
     */
    public MatchNotStartedYetStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    /**
     * Method displaying a waiting message immediately after a match is created
     */
    @Override
    public void run() {
        System.out.println("Attendi che gli altri giocatori entrino in partita...");
    }
}
