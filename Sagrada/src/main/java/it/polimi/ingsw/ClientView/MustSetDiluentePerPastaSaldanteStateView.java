package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

/**
 * This view for the CLI is displayed when the player used the "Diluente per Pasta Salda" tool card and he has to choose
 * the intensity of the dice.
 */
public class MustSetDiluentePerPastaSaldanteStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    /**
     * @param serverController The reference to the server's controller for invoking its methods
     * @param yourName The username of the account performing the requests
     */
    public MustSetDiluentePerPastaSaldanteStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    /**
     * Method that handles the second part of Diluente per Pasta Salda
     */
    @Override
    public void run() {
        Setter.Singleton().selectExtractedDiceIntensity(serverController, yourName);
        Setter.Singleton().placeSingleDice(serverController, yourName);
        }
}
