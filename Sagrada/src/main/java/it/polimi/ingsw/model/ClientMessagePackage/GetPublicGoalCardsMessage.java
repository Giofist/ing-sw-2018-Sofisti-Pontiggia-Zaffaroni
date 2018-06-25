package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.NetworkServer.ServerMessage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class GetPublicGoalCardsMessage extends ClientMessage {

    public GetPublicGoalCardsMessage() { this.messagecodex = 44; }

    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener)throws RemoteException {
        List list = clientHandler.getPublicGoalCards(getClientName());
        ServerMessage messageClass = new ServerMessage();
        messageClass.setMessagecodex(1);
        messageClass.setList(list);
        try {
            listener.sendMessage(messageClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}