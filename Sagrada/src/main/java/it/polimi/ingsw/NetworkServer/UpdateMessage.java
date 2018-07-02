package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.NetworkClient.SocketClientListener;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;
import it.polimi.ingsw.model.ClientMessagePackage.ResponseMessage;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * This class is an update message that is used to send an update to the client (usually changes in state)
 */
public class UpdateMessage extends ServerMessage {

    public UpdateMessage(){
        this.messagecodex=44;
    }

    /**
     * @param view The client to contact
     * @param listener Server side listener that waits for server's messages
     * @throws RemoteException Exception thrown if the client doesn't respond back or there is a problem during the request from the server
     */
    @Override
    public void performAction(Observer view, SocketClientListener listener) throws RemoteException {
        view.update(observable, null);
        ClientMessage messageClass = new ResponseMessage();
        messageClass.setMessagecodex(1);
        try {
            listener.sendMessage(messageClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
