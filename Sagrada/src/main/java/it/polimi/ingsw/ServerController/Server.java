package it.polimi.ingsw.ServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.model.*;
public class Server {
    private int port;
    public Server(int port){
        this.port=port;
    }

    //the method which starts the server
    public void startServer(){
        ExecutorService executor = Executors.newCachedThreadPool(); //crea thread quando necessario
        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(port);
        } catch(IOException e) {
            System.err.println(e.getMessage());
            return;  // porta non disponibile
        }
        System.out.println("Server ready");
        int i=0;
        while(i==0) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new ClientHandler(socket));
            } catch (IOException e) {
                i=1;
                break; //entrerei qui se serverSocket venisse chiuso
            }
        }
        try{
            serverSocket.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        executor.shutdown();
    }
    public static void main (String[] args){
        MultipleUserGameList gameslist = MultipleUserGameList.singleton();
        UsersList usersList = UsersList.Singleton();
        Server server = new Server(1337);
        server.startServer();
    }

}
