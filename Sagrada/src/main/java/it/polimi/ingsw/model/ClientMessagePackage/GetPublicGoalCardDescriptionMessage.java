package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.NetworkServer.ServerMessage;

import java.io.IOException;
import java.rmi.RemoteException;

public class GetPublicGoalCardDescriptionMessage extends ClientMessage {

    public GetPublicGoalCardDescriptionMessage() { this.messagecodex = 44; }

    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener)throws RemoteException {
        String answer = clientHandler.getPublicGoalCarddescriptions(getClientName());
        ServerMessage messageClass = new ServerMessage();
        messageClass.setMessagecodex(1);
        messageClass.setMessage(answer);
        try {
            listener.sendMessage(messageClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
