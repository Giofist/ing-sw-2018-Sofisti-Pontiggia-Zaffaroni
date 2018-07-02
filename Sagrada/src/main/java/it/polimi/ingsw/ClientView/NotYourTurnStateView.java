package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

/**
 * This view for the CLI is displayed when the player have to wait for its turn
 */
public class NotYourTurnStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;
    private ObserverView view;

    /**
     * @param serverController The reference to the server's controller for invoking its methods
     * @param yourName The username of the account performing the requests
     * @param view The client's main view
     */
    public NotYourTurnStateView(ClientHandlerInterface serverController, String yourName, ObserverView view) {
        this.serverController = serverController;
        this.yourName = yourName;
        this.view = view;
    }


    /**
     * Method for displaying the actions available in this state (pass the turn or display other users' maps)
     */
    @Override
    public void run() {
        Setter.Singleton().selectAction(serverController,yourName, view);
    }
}
