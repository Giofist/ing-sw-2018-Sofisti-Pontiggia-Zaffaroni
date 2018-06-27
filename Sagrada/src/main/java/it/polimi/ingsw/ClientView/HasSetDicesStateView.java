package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class HasSetDicesStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;
    private ObserverView view;

    public HasSetDicesStateView(ClientHandlerInterface serverController, String yourName, ObserverView view) {
        this.serverController = serverController;
        this.yourName = yourName;
        this.view = view;
    }

    @Override
    public void run() {
        Setter.Singleton().selectAction(serverController, yourName, view);
    }
}
