package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class NotYourTurnStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    public NotYourTurnStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    @Override
    public void run() {
        Setter.Singleton().selectAction(serverController,yourName);
    }
}
