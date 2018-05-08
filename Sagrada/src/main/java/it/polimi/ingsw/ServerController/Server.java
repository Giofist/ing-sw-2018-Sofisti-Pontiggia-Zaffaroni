package it.polimi.ingsw.ServerController;

//fatta da pon
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.model.*;
public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    /*
    //the method which starts the server for the socket part
    //the method creates a pool of thread and waits for connection from the client
    public void startServer() {
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;  // porta non disponibile
        }
        System.out.println("Server ready for the socket connection\n");
        int i = 0;
        while (i == 0) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new ClientHandler(socket));
            } catch (IOException e) {
                i = 1;
                break; //entrerei qui se serverSocket venisse chiuso
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        executor.shutdown();
    }
}*/
    public static void main(String[] args) throws RemoteException{
        //creo i due "database" di cu idevo tenere consistenza nel server
        MultipleUserGameList gameslist = MultipleUserGameList.singleton();
        UsersList usersList = UsersList.Singleton();

/*
        //socket part
        Server server = new Server(1337);
        server.startServer();
*/
        //RMI part
        //parte di questo codice è stato preso dalle slide di presentazione di RMI
        //anche RMitter ha gentilmente contribuito
        System.out.println("Binding server implementation to registry...\n");
        ClientHandler controller = new ClientHandler();

        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("RMIServerClientHandler", controller);
        System.out.println("Waiting for invocations from clients...");


    }
}






