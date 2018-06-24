package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ServerController.ClientHandler;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;

import java.io.IOException;
import java.rmi.RemoteException;

public class SocketMessageHandlerServer implements Runnable {
    ClientMessage message;
    ClientHandler clientHandler;
    SocketServerListener listener;
    public SocketMessageHandlerServer(ClientMessage messageClass, ClientHandler clientHandler, SocketServerListener listener){
        this.message = messageClass;
        this.clientHandler = clientHandler;
        this.listener = listener;
    }
    @Override
    public void run() {
        try{
            this.message.performAction(this.clientHandler, listener);
        }catch (RemoteException e) {
            ServerMessage message = new ServerMessage();
            message.setMessagecodex(0);
            message.setMessage(e.getMessage());

            try {
                listener.sendMessage(message);
            } catch (IOException err) {
                e.printStackTrace();
            }
        }
    }
}
