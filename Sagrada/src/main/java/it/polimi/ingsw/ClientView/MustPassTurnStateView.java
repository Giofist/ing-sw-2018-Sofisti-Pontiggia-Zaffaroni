package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class MustPassTurnStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    public MustPassTurnStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    @Override
    public void run() {
    Setter.Singleton().passYourTurn(serverController, yourName);
    }
}
