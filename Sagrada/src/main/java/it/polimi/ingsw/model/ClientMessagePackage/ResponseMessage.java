package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandler;

public class ResponseMessage extends ClientMessage {
    @Override
    public void performAction(ClientHandler clientHandler, SocketServerListener listener) {
        //do null
    }
}
