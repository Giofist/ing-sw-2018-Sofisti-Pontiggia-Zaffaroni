package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class UseToolCardMessage extends ClientMessage {

    public UseToolCardMessage() { this.messagecodex = 44; }

    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) {


    }
}
