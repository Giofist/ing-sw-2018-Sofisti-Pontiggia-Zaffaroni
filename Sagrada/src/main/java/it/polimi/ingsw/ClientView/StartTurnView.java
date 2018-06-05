package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.rmi.RemoteException;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.WHITE;
import static org.fusesource.jansi.Ansi.ansi;

public class StartTurnView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    public StartTurnView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    @Override
    public synchronized void run() {
        try {
            Scanner in = new Scanner(System.in);
            String schemeCards = null;
            boolean correct = false;
            int index;


            try {
                schemeCards = serverController.getSchemeCards(this.yourName);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
            String[] schemeCardsArray = schemeCards.split("&"); //creo un array con le sngole mappe
            for (index = 0; index < schemeCardsArray.length; index++) {
                Printer.Singleton().printMap(schemeCardsArray[index]);
            }

            while (!correct) {
                System.out.println("Seleziona la carta schema che desideri tra le seguenti indicando il numero relativo:");
                try {
                    serverController.setSchemeCard(yourName, in.nextInt());
                    correct = true;
                } catch (RemoteException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("La carta schema da te scelta è: ");
            try {
                Printer.Singleton().printMap(serverController.getSchemeCard(yourName));
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("\nAttendi che anche gli altri giocatori abbiano scelto la loro mappa.\n");
            wait();
        }
        catch(InterruptedException e){
            System.out.println("Sei passato allo stato successivo!");
        }
    }

}
