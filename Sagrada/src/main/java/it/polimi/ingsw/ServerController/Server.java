package it.polimi.ingsw.ServerController;

//fatta da pon
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.polimi.ingsw.model.*;
public class Server {

    public static void main(String[] args) throws RemoteException{
        int RMI_PORT = 1099;
        //creo i due "database" di cu idevo tenere consistenza nel server
        MatchesList gameslist = MatchesList.singleton();
        UsersList usersList = UsersList.Singleton();


        //RMI part
        //parte di questo codice è stato preso dalle slide di presentazione di RMI
        //anche RMitter ha gentilmente contribuito
        System.out.println("Binding server implementation to registry...\n");
        ClientHandler controller = new ClientHandler();
        TurnController turnController = new TurnController();

        System.setProperty("java.rmi.server.hostname","192.168.1.2");
        //System.setProperty("java.rmi.server.hostname","10.169.214.40");
        Registry registry = LocateRegistry.createRegistry(RMI_PORT);
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






