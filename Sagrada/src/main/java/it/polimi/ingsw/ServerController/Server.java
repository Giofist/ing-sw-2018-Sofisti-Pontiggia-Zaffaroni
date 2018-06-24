package it.polimi.ingsw.ServerController;

//fatta da pon

import it.polimi.ingsw.model.MatchesList;
import it.polimi.ingsw.model.UsersList;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Server {

    public static void main(String[] args) throws RemoteException{
        int RMI_PORT = 1099;
        MatchesList gameslist = MatchesList.singleton();
        UsersList usersList = UsersList.Singleton();


        //RMI part
        System.out.println("Binding server implementation to registry...\n");
        ClientHandler controller = new ClientHandler();

        System.setProperty("java.rmi.server.hostname","192.168.1.2");
        Registry registry = LocateRegistry.createRegistry(RMI_PORT);
        registry.rebind("ClientHandler", controller);

        //socket part
        new Thread(new StartServer(controller, 1337)).start();


        //carica un turnController
        //ho pensato di gestire separatamente il turn controller perchè se no
        //avremmo messo troppi metodi in clienthandler
        // così il client, dovendo cercare un turncontroller sul registro, avremo
        //magari modo di usare un'altra calsse per il turno e cambiare grafica magari
        System.out.println("Waiting for invocations from clients...");

    }

}






