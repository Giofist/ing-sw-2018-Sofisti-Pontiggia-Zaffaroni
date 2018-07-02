package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.NetworkServer.ServerMessage;
import it.polimi.ingsw.model.ClientMessagePackage.ResponseMessage;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * This class is responsible for managing the situation in which the server needs to contact the client either for pinging
 * it or for updating it about changes in states
 */
public class SocketMessageHandler implements Runnable {
    private Observer observer;
    private ServerMessage message;
    private SocketClientListener listener;

    /**
     * @param observer
     * @param listener The socketClientListener waiting for messages from the server
     * @param message The message we received from the server that we want to process
     */
    public SocketMessageHandler( Observer observer, SocketClientListener listener, ServerMessage message){
        this.listener = listener;
        this.observer = observer;
        this.message = message;
    }


    /**
     * This method handles the request and replies back to the server
     */
    @Override
    public void run() {
        try{
            message.performAction(this.observer, this.listener);
        }catch(RemoteException e){
            ResponseMessage message = new ResponseMessage();
            message.setMessagecodex(0);
            message.setErrorMessage(e.getMessage());
        try {
            listener.sendMessage(message);
        } catch (IOException err) {
            e.printStackTrace();
        }
    }
    }
}
