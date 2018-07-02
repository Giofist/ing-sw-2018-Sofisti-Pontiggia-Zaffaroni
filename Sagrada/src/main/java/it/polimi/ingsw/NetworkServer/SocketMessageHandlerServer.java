package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ServerController.ClientHandler;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;

import java.io.IOException;
import java.rmi.RemoteException;


/**
 * This class is for handling requests from the client
 */
public class SocketMessageHandlerServer implements Runnable {
    ClientMessage message;
    ClientHandler clientHandler;
    SocketServerListener listener;


    /**
     * @param messageClass The class containing the request message
     * @param clientHandler The server controller
     * @param listener The server message listener
     */
    public SocketMessageHandlerServer(ClientMessage messageClass, ClientHandler clientHandler, SocketServerListener listener){
        this.message = messageClass;
        this.clientHandler = clientHandler;
        this.listener = listener;
    }


    /**
     * Method responsible for performing the request received from the client and for notifying it about the result of the operation
     */
    @Override
    public void run() {
        try{
            this.message.performAction(this.clientHandler, listener);
        }catch (RemoteException e) {
            ServerMessage message = new ServerResponseMessage();
            message.setMessagecodex(0);
            message.setMessage(e.getMessage());

            try {
                listener.sendMessage(message);
            } catch (IOException err) {
                e.printStackTrace();
            }
        }
    }
}
