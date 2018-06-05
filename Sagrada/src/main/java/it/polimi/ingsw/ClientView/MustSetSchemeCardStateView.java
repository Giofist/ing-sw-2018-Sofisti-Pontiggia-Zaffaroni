package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class MustSetSchemeCardStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    public MustSetSchemeCardStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    @Override
    public void run() {

        Setter.Singleton().selectSchemeCard(serverController, yourName);
    }
}
