package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

/**
 * This view for the CLI is displayed when the match is starting and each player has to choose its scheme card
 */
public class MustSetSchemeCardStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;


    /**
     * @param serverController The reference to the server's controller for invoking its methods
     * @param yourName The username of the account performing the requests
     */
    public MustSetSchemeCardStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    /**
     * Method for displaying and letting the user choose its scheme card
     */
    @Override
    public void run() {
        Setter.Singleton().selectSchemeCard(serverController, yourName);
    }
}
