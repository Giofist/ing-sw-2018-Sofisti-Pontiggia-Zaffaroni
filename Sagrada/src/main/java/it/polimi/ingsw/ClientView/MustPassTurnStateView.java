package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

/**
 * This view for the CLI is displayed when the player is in a state where he is obliged to pass the turn
 */
public class MustPassTurnStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    /**
     * @param serverController The reference to the server's controller for invoking its methods
     * @param yourName The username of the account performing the requests
     */
    public MustPassTurnStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    /**
     * Method for displaying the actions available in this state
     */
    @Override
    public void run() {
    Setter.Singleton().passYourTurn(serverController, yourName);
    }
}
