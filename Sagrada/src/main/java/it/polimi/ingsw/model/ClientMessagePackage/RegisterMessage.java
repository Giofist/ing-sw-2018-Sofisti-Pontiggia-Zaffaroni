package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandler;
import it.polimi.ingsw.model.ServerMessagePackage.ServerMessage;

import java.io.IOException;
import java.rmi.RemoteException;

public class RegisterMessage  extends ClientMessage {
    @Override
    public void performAction(ClientHandler clientHandler, SocketServerListener listener) {
        try {
            clientHandler.register(getClientName(), getPassword());
            ServerMessage messageClass = new ServerMessage();
            messageClass.setMessagecodex(1);
            try {
                listener.sendMessage(messageClass);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            ServerMessage messageClass = new ServerMessage();
            messageClass.setMessagecodex(0);
            messageClass.setErrorMessage(e.getMessage());
            try {
                listener.sendMessage(messageClass);
            } catch (IOException err) {
                e.printStackTrace();
            }
        }
    }
}

