package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.NetworkClient.SocketClientListener;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;
import it.polimi.ingsw.model.ClientMessagePackage.ResponseMessage;

import java.io.IOException;
import java.rmi.RemoteException;

public class PingServerMessage extends ServerMessage {


    public PingServerMessage(){
        this.messagecodex =44;
    }
    @Override
    public void performAction(Observer view, SocketClientListener listener) throws RemoteException {
        view.ping();
        ClientMessage messageClass = new ResponseMessage();
        messageClass.setMessagecodex(1);
        try {
            listener.sendMessage(messageClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

