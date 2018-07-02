package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

/**
 * This view for the CLI is displayed when the player has already used a tool card in its turn and he can
 * only pass the turn or place a dice on the scheme card
 */
public class HasUsedAToolcardActionStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;
    private ObserverView view;


    /**
     * @param serverController The reference to the server's controller for invoking its methods
     * @param yourName The username of the account performing the requests
     * @param view The client's main view
     */
    public HasUsedAToolcardActionStateView(ClientHandlerInterface serverController, String yourName, ObserverView view) {
        this.serverController = serverController;
        this.yourName = yourName;
        this.view = view;
    }


    /**
     * Method for retrieving the possible actions in the current state
     */
    @Override
    public void run() {
        Setter.Singleton().selectAction(serverController, yourName, view);
    }
}
