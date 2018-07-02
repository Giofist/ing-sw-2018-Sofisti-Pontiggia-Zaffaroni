package it.polimi.ingsw.ServerController;


import it.polimi.ingsw.model.MatchesList;
import it.polimi.ingsw.model.UsersList;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * This class contains a single main method which is responsible for:
 * 1) Initializing the singleton mathes list
 * 2) Initializing the singleton users list by loading past registered accounts
 * 3) Starting the rmi registry on port 1099
 * 4) Starting a new thread which will handle all the socket connections
 */
public class Server {

    public static void main(String[] args) throws RemoteException{
        int RMI_PORT = 1099;
        MatchesList.singleton();
        UsersList.Singleton();


        // RMI part
        System.out.println("Binding server implementation to registry...\n");
        ClientHandler controller = new ClientHandler();

        System.setProperty("java.rmi.server.hostname","192.168.1.2");
        Registry registry = LocateRegistry.createRegistry(RMI_PORT);
        registry.rebind("ClientHandler", controller);

        //Socket part
        new Thread(new StartServer(controller, 1337)).start();
        System.out.println("Waiting for invocations from clients...");
    }

}






