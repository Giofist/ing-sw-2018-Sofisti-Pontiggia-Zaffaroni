package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.NetworkClient.SocketClientListener;

import java.rmi.RemoteException;

public class ServerResponseMessage extends ServerMessage {
    @Override
    public void performAction(Observer view, SocketClientListener listener) throws RemoteException {
        //do nothing
    }
}
