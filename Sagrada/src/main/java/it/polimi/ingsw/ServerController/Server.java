package it.polimi.ingsw.ServerController;

//fatta da pon
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.model.*;
public class Server {

    public static void main(String[] args) throws RemoteException{
        //creo i due "database" di cu idevo tenere consistenza nel server
        GamesList gameslist = GamesList.singleton();
        UsersList usersList = UsersList.Singleton();


        //RMI part
        //parte di questo codice è stato preso dalle slide di presentazione di RMI
        //anche RMitter ha gentilmente contribuito
        System.out.println("Binding server implementation to registry...\n");
        ClientHandler controller = new ClientHandler();
        TurnController turnController = new TurnController();

        System.setProperty("java.rmi.server.hostname","192.168.1.2");
        //System.setProperty("java.rmi.server.hostname","10.169.214.40");
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("ClientHandler", controller);
        System.out.println("Waiting for invocations from clients...\n");


        //socket part
        new Thread(new StartServer(controller, 1337)).start();


        //carica un turnController
        //ho pensato di gestire separatamente il turn controller perchè se no
        //avremmo messo troppi metodi in clienthandler
        // così il client, dovendo cercare un turncontroller sul registro, avremo
        //magari modo di usare un'altra calsse per il turno e cambiare grafica magari
        registry.rebind("TurnController",turnController );
        System.out.println("Waiting for invocations from clients...\n");


    }

}






