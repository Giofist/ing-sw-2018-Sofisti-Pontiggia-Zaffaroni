package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.rmi.RemoteException;

public class EndMatchStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    public EndMatchStateView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }


    @Override
    public void run() {
        try {
            System.out.println("La partita è finita: hai totalizzato" + serverController.getmyPoints(yourName) + "punti");
            System.out.println("Ecco la classifica finale: " + serverController.getRanking(yourName));
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        //to implement svegliare il thread
    }
}
