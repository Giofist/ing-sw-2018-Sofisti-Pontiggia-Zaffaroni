package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class MustSetDiluentePerPastaSaldanteStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    public MustSetDiluentePerPastaSaldanteStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    @Override
    public void run() {
        Setter.Singleton().selectExtractedDiceIntensity(serverController, yourName);
        Setter.Singleton().placeSingleDice(serverController, yourName);
        }
}
