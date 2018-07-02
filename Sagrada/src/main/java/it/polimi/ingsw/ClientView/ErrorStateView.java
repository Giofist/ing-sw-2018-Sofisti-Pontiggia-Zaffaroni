package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Observable;

import java.rmi.RemoteException;

/**
 * This view for the CLI is displayed in the situation in which the user created a new match but nobody joined it during
 * the waiting countdown. This will be a notify to the user that the match cannot start with only one player.
 */
public class ErrorStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;
    private ObserverView view;

    /**
     * @param serverController The reference to the server's controller for invoking its methods
     * @param yourName The username of the account performing the requests
     * @param view The client's main view
     */
    public ErrorStateView(ClientHandlerInterface serverController, String yourName, ObserverView view) {
        this.serverController = serverController;
        this.yourName = yourName;
        this.view = view;
    }

    /**
     * Method displaying the time expired message
     */
    @Override
    public void run() {
        System.out.println("Il timer è scaduto e nessuno si è unito alla tua partita: non può iniziare");
    }
}
