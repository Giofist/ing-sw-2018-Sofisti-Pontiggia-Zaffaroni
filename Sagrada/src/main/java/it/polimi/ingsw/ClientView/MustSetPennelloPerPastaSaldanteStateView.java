package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class MustSetPennelloPerPastaSaldanteStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    public MustSetPennelloPerPastaSaldanteStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    @Override
    public void run() {
        Setter.Singleton().placeSingleDice(serverController, yourName);
    }
}
