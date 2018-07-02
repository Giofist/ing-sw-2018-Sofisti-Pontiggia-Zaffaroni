package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

/**
 * This view for the CLI is displayed when the player is starting its turn
 */
public class StartTurnView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;
    private ObserverView view;

    /**
     * @param serverController The reference to the server's controller for invoking its methods
     * @param yourName The username of the account performing the requests
     * @param view The client's main view
     */
    public StartTurnView(ClientHandlerInterface serverController, String yourName, ObserverView view) {
        this.serverController = serverController;
        this.yourName = yourName;
        this.view =view;
    }

    /**
     * Method used for displaying the possible actions in the current state and some useful information such as which
     * dices remained in the round dice pool or which are the goal cards for the current match
     */
    @Override
    public void run() {
            Printer.Singleton().printRoundTrack(serverController, yourName);
            Printer.Singleton().printGoalCards(serverController, yourName);
            Printer.Singleton().printRoundDicePool(serverController, yourName);
            Setter.Singleton().selectAction(serverController, yourName, view);

    }
}
