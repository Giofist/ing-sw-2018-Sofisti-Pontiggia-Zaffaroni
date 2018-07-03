package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.NetworkClient.SocketClientListener;

import java.rmi.RemoteException;

/**
 * Response message sent from the server after receiving a request from the client
 */
public class ServerResponseMessage extends ServerMessage {
    /**
     * @param view The client that performed the request
     * @param listener A reference to the SocketServerListener
     * @throws RemoteException
     */
    @Override
    public void performAction(Observer view, SocketClientListener listener) throws RemoteException {
        //do nothing
    }
}
