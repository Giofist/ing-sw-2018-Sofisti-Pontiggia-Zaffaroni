package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.rmi.RemoteException;
import java.util.List;

public class SocketResponseHandler implements Runnable{
    private ClientHandlerInterface controller;
    private String message;
    private RemoteException exception;
    private int messageCodex;
    private List list;

    public SocketResponseHandler(ClientHandlerInterface controller, String message, int messageCodex, List list){
        this.controller = controller;
        this.message = message;
        this.messageCodex = messageCodex;
        this.list = list;
    }
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
    public void check() throws RemoteException{
        if(this.exception == null){
            return;
        }
        throw this.exception;

    }
    public String getMessage(){
        return message;
    }
    public List getList() {
        return list;
    }
}

