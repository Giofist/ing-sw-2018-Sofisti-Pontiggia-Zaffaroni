package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Observable;

import java.rmi.RemoteException;

public class ErrorStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;
    private ObserverView view;

    public ErrorStateView(ClientHandlerInterface serverController, String yourName, ObserverView view) {
        this.serverController = serverController;
        this.yourName = yourName;
        this.view = view;
    }

    @Override
    public void run() {
        System.out.println("Il timer è scaduto e nessuno si è unito alla tua partita: non può iniziare");
    }
}
