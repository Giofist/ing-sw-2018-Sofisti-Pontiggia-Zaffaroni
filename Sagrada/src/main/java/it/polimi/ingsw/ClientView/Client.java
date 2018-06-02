package it.polimi.ingsw.ClientView;


//import it.polimi.ingsw.Network.SocketController;
import it.polimi.ingsw.Network.SocketController;
import it.polimi.ingsw.Network.SocketClientListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) throws Exception {
        // Passing server IP as command line argument
        //String ipAddr = args[1];
        String ipAddr = "127.0.0.1";
        boolean correct = false;
        Scanner in = new Scanner(System.in);


        System.out.println("Benvenuto nel SetUP partita di Sagrada!\n" +
                "Qui puoi selezionare se giocare usando la connessione di tipo RMI (R) oppure Socket (S).\n" +
                "Seleziona ora la tua scelta digitando R per RMI o S per Socket:");
        while (!correct) {
            String input = in.nextLine();
            if (input.equals("R") || input.equals("r")) {
                // Locating rmi register on the server
                // looking for the controller on the registry
                Registry rmiRegistry = LocateRegistry.getRegistry(ipAddr);
                ClientHandlerInterface controller = (ClientHandlerInterface) rmiRegistry.lookup("ClientHandler");
                new ObserverView(controller).run();
                correct = true;
            } else if (input.equals("S") || input.equals("s")) {
                ObserverView observerViewView = new ObserverView();
                SocketClientListener listener = new SocketClientListener(ipAddr);
                SocketController socketController = new SocketController(observerViewView, listener);
                listener.setController(socketController);
                observerViewView.setServercontroller(socketController);
                new Thread(listener).start();
                observerViewView.run();

                correct = true;
            } else {
                System.out.println("Hai sbagliato a digitare. Riprova");
            }
        }
    }

}

