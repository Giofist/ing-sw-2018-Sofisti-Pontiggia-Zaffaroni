package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class ErrorStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    public ErrorStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    @Override
    public void run() {
        System.out.println("Impossibile caricare le mappe!\nLa partita non può iniziare. :(");
    }
}
