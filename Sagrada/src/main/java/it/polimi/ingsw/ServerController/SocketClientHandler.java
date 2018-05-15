package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.ObserverViewInterface;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.net.Socket;
import java.rmi.RemoteException;

public class SocketClientHandler implements Runnable, ObserverViewInterface {
    private Socket socket;
    private ClientHandler controller;
    public SocketClientHandler(Socket socket, ClientHandler controller) throws RemoteException {
        this.socket = socket;
        this.controller = controller;
    }
    @Override
    public void run (){

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
}
