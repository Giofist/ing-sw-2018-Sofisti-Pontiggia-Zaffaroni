package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandler;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class ResponseMessage extends ClientMessage {
    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) {
        //do null
    }
}