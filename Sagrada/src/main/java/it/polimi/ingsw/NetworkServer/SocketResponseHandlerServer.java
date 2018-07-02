package it.polimi.ingsw.NetworkServer;


import java.rmi.RemoteException;

/**
 * This class is for handling responses from the client
 */
public class SocketResponseHandlerServer implements Runnable {

    private SocketClient client;
    private String message;
    private RemoteException exception;
    private int messageCodex;


    /**
     * @param client The client that performed the operation
     * @param message The message contained in the response
     * @param messageCodex The type of response: ok, error
     */
    public SocketResponseHandlerServer(SocketClient client, String message, int messageCodex ){
        this.client = client;
        this.messageCodex = messageCodex;
        this.message = message;
    }


    /**
     *
     */
    @Override
    public  void run(){
        if(this.messageCodex == 0){
            this.exception = new RemoteException(message);
        }
        synchronized (client){
            client.setStringHandler(this);
            client.notifyAll();
        }
    }


    /**
     * This method checks if the response from the client contains an error and throws a remote exception if it's the case
     * @throws RemoteException Exception thrown if the client notified an error
     */
    public void check() throws RemoteException{
        if(this.exception == null){
            return;
        }
        throw this.exception;

    }


    /**
     * @return The message received from the client
     */
    public String getMessage(){
        return message;
    }
}



