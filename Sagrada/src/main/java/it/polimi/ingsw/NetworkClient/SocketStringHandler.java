package it.polimi.ingsw.NetworkClient;

import java.rmi.RemoteException;

public class SocketStringHandler implements Runnable{
    private SocketController controller;
    private String message;
    private RemoteException exception;
    boolean errorMessage;

    public SocketStringHandler(SocketController controller, SocketClientListener listener, String message, boolean errorMessage){
        this.controller = controller;
        this.message = message;
        this.errorMessage = errorMessage;
    }
    public  void run(){
        System.out.println(message);
        switch(message){
            case "OK":
                break;
            default:
                if(errorMessage){
                    this.exception = new RemoteException(message);
                }
                break;
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
}

