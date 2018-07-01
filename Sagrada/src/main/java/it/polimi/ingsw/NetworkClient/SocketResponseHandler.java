package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.rmi.RemoteException;
import java.util.List;

/**
 * This class is responsible for managing the situation in which we as a client have contacted the server for a request
 * and he replied back to us with a message that can be an error in our request, or the content of our request
 */
public class SocketResponseHandler implements Runnable{
    private ClientHandlerInterface controller;
    private String message;
    private RemoteException exception;
    private int messageCodex;
    private List list;

    /**
     * @param controller The interface with all the methods exposed by the server
     * @param message The message received from the server
     * @param messageCodex The type of message we received: 0 for an error to handle or 1 for a good response
     * @param list The content of our response
     */
    public SocketResponseHandler(ClientHandlerInterface controller, String message, int messageCodex, List list){
        this.controller = controller;
        this.message = message;
        this.messageCodex = messageCodex;
        this.list = list;
    }


    /**
     * This method understands if the response was good or bad
     */
    public  void run(){
        if(this.messageCodex == 0){
            this.exception = new RemoteException(message);
        }
        synchronized (controller){
            try{
                controller.setResponseHandler(this);
            }catch(RemoteException e){
                //do nothing
            }
            controller.notifyAll();
        }
    }


    /**
     * This method throws a remote exception if something went wrong with our request to the server and he replied with
     * an error message
     * @throws RemoteException Exception thrown in case we received a bad response from the server
     */
    public void check() throws RemoteException{
        if(this.exception == null){
            return;
        }
        throw this.exception;

    }


    /**
     * @return The content of the message
     */
    public String getMessage(){
        return message;
    }


    /**
     * @return The content of the response
     */
    public List getList() {
        return list;
    }
}

