package it.polimi.ingsw.NetworkClient;

import java.rmi.RemoteException;
import java.util.List;

public class SocketResponseHandler implements Runnable{
    private SocketController controller;
    private String message;
    private RemoteException exception;
    private int messageCodex;
    private List list;

    public SocketResponseHandler(SocketController controller, String message, int messageCodex, List list){
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
            controller.setStringHandler(this);
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

