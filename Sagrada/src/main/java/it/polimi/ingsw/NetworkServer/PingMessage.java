package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.NetworkClient.SocketClientListener;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;
import it.polimi.ingsw.model.ClientMessagePackage.ResponseMessage;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * This class is a ping message, and it's used to verify that the client is reachable
 */
public class PingMessage extends ServerMessage {


    public PingMessage(){
        this.messagecodex =44;
    }


    /**
     * This method will send a ping message to the client
     * @param view The client to contact
     * @param listener Client side listener that waits for server's messages
     * @throws RemoteException Exception thrown if the client doesn't respond back or there is a problem during the request from the server
     */
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

