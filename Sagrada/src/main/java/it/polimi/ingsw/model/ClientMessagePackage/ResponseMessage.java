package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class ResponseMessage extends ClientMessage {
    /**
     * @param clientHandler The interface with all the methods exposed by the server
     * @param listener      The socket server listener waiting for messages from the client
     */
    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) {
        //do null
    }
}
