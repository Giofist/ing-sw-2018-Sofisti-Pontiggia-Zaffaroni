package it.polimi.ingsw.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            Scanner in = new Scanner(socket.getInputStream());
            Scanner out = new PrintWriter(socket.getOutputStream());
            out.println("")
        }  catch (IOException e){
            System.err
        }
        //not implemented yet
    }
}
