package it.polimi.ingsw.Controller;

import java.net.Socket;

public class ClientHandler implements Runnable {
    Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        //not implemented yet
    }
}
