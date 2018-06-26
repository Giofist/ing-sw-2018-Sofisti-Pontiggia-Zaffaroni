package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.NetworkServer.ServerMessage;
import it.polimi.ingsw.model.ClientMessagePackage.ResponseMessage;

import java.io.IOException;
import java.rmi.RemoteException;

public class SocketMessageHandler implements Runnable {
    private Observer observer;
    private ServerMessage message;
    private SocketClientListener listener;

    public SocketMessageHandler( Observer observer, SocketClientListener listener, ServerMessage message){
        this.listener = listener;
        this.observer = observer;
        this.message = message;
    }
    @Override
    public void run() {
        try{
            message.performAction(this.observer, this.listener);
        }catch(RemoteException e){
            ResponseMessage message = new ResponseMessage();
            message.setMessagecodex(0);
            message.setErrorMessage(e.getMessage());
        try {
            listener.sendMessage(message);
        } catch (IOException err) {
            e.printStackTrace();
        }
    }
    }
}
