package it.polimi.ingsw.ServerController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartServer implements Runnable{
    private int port;
    private ClientHandler controller;
    ArrayList<ObjectOutputStream> clientstreams = new ArrayList<ObjectOutputStream>();
    //ArrayList<ObjectInputStream> clientstreamsinput = new ArrayList<ObjectInputStream>();



    public StartServer (ClientHandler controller, int port){
        this.controller = controller;
        this.port = port;
    }


    public void run(){
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
                clientstreams.add(new ObjectOutputStream(socket.getOutputStream()));
                //clientstreamsinput.add(new ObjectInputStream (socket.getInputStream()));
                System.out.println("Ho ricevuto una nuova richiesta di connessione\n");
                executor.submit(new SocketServerListener(socket, this.controller));
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
}

