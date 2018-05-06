package it.polimi.ingsw.ServerController;

import java.io.Serializable;
import java.net.Socket;


//raga ho fatto una prima implementazione del client handler
public class ClientHandler implements Runnable,Serializable {
    Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

    }
}
