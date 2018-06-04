package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandler;
import it.polimi.ingsw.model.ServerMessagePackage.ServerMessage;

import java.io.IOException;
import java.rmi.RemoteException;

public class RegisterMessage  extends ClientMessage {
    @Override
    public void performAction(ClientHandler clientHandler, SocketServerListener listener) throws RemoteException {
        clientHandler.register(getClientName(), getPassword());
    }
}

