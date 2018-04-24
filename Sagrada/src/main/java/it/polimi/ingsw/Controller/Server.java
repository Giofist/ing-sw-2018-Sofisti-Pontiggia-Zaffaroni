package it.polimi.ingsw.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int port;

    public Server(int port){
        this.port=port;
    }

    public void startServer(){
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(port);
        } catch(IOException e){
            System.err.println(e.getMessage());
            return;  // porta non disponibile
        }
        System.out.println("Server ready");
        /*
        while(true){
            try{
                Socket socket = serverSocket.accept();
                executor.submit(new ClientHandler(socket));

            }catch(IOException e){
                break; //entrerei qui se serverSocket venisse chiuso
            }
        }
        */
        executor.shutdown();
    }
}
