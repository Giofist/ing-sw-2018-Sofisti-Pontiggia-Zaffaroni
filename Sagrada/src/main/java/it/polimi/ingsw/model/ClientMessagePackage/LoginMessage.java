package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.ServerMessage;
import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.io.IOException;
import java.rmi.RemoteException;

public class LoginMessage extends ClientMessage {

    public LoginMessage(){
        this.messagecodex = 44;
    }

    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) throws RemoteException{
        clientHandler.login(getClientName(),getPassword(),listener.getClient());
        ServerMessage messageClass = new ServerMessage();
        messageClass.setMessagecodex(1);
        try {
            listener.sendMessage(messageClass);
        } catch (IOException e) {
            e.printStackTrace();

        }
        }
}
