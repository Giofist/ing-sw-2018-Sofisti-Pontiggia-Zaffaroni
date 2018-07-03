package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

/**
 * This view for the CLI is displayed when the player used the "Pennello per Pasta Salda" tool card and he has to place
 * the dice.
 */
public class MustSetPennelloPerPastaSaldanteStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;


    /**
     * @param serverController The reference to the server's controller for invoking its methods
     * @param yourName The username of the account performing the requests
     */
    public MustSetPennelloPerPastaSaldanteStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    /**
     * Method handling the second part of Pennello per Pasta Salda
     */
    @Override
    public void run() {
        Setter.Singleton().placeSingleDice(serverController, yourName);
    }
}
