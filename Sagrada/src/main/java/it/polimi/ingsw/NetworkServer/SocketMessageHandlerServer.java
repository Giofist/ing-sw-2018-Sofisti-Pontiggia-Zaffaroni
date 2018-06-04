package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;
import it.polimi.ingsw.ServerController.ClientHandler;
import it.polimi.ingsw.model.ServerMessagePackage.ServerMessage;

import java.io.IOException;
import java.rmi.RemoteException;

public class SocketMessageHandlerServer implements Runnable {
    ClientMessage messageClass;
    ClientHandler clientHandler;
    SocketServerListener listener;
    public SocketMessageHandlerServer(ClientMessage messageClass, ClientHandler clientHandler, SocketServerListener listener){
        this.messageClass = messageClass;
        this.clientHandler = clientHandler;
        this.listener = listener;
    }
    @Override
    public void run() {
        this.messageClass.performAction(this.clientHandler, this.listener);
    }
}
