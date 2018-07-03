package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.ServerMessage;
import it.polimi.ingsw.NetworkServer.ServerResponseMessage;
import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class GetExtractedSchemeCardMessage extends ClientMessage {

    public GetExtractedSchemeCardMessage(){
        this.messagecodex = 44;
    }

    /**
     * @param clientHandler The interface with all the methods exposed by the server
     * @param listener      The socket server listener waiting for messages from the client
     * @throws RemoteException
     */
    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) throws RemoteException{
        List list = clientHandler.getExtractedSchemeCard(getClientName());
        ServerMessage message = new ServerResponseMessage();
        message.setMessagecodex(1);
        message.setList(list);
        try {
            listener.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
