package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.ServerResponseMessage;
import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.NetworkServer.ServerMessage;

import java.io.IOException;
import java.rmi.RemoteException;

public class GetRoundTrackMessage extends ClientMessage {

    public GetRoundTrackMessage() { this.messagecodex = 44; }

    /**
     * @param clientHandler The interface with all the methods exposed by the server
     * @param listener      The socket server listener waiting for messages from the client
     * @throws RemoteException
     */
    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener)throws RemoteException {
        String answer = clientHandler.getRoundTrack(getClientName());
        ServerMessage messageClass = new ServerResponseMessage();
        messageClass.setMessagecodex(1);
        messageClass.setMessage(answer);
        try {
            listener.sendMessage(messageClass);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
