package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.ServerMessage;
import it.polimi.ingsw.NetworkServer.ServerResponseMessage;
import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.io.IOException;
import java.rmi.RemoteException;

public class UseToolCardMessage extends ClientMessage {
    ToolRequestClass requestClass;

    public UseToolCardMessage() { this.messagecodex = 44;
    }

    public void setRequestClass(ToolRequestClass requestClass){
        this.requestClass = requestClass;
    }
    public ToolRequestClass getRequestClass() {
        return this.requestClass;
    }

    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) throws RemoteException{
        clientHandler.useaToolCard(getClientName(), getRequestClass());
        ServerMessage messageClass = new ServerResponseMessage();
        messageClass.setMessagecodex(1);
        try {
            listener.sendMessage(messageClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
