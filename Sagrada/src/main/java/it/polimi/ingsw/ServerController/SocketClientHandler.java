package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.ObserverViewInterface;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;

public class SocketClientHandler implements Runnable, ObserverViewInterface {
    private Socket socket;
    private ClientHandler controller;
    Scanner in;
    PrintWriter out;


    public SocketClientHandler(Socket socket, ClientHandler controller) throws RemoteException, IOException {
        this.socket = socket;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        this.controller = controller;
    }
    @Override
    public void run (){
        switch(in.nextInt()){
            case 1: out.println(1);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showSchemeCards(SchemeCard scheme, SchemeCard card) {

    }

    @Override
    public void notifyaDraw() {

    }

    @Override
    public void notifyaLose() {

    }

    @Override
    public void notifyaWin() {

    }

    @Override
    public void notifyGameisStarting(String gamename) throws RemoteException {

    }
}
