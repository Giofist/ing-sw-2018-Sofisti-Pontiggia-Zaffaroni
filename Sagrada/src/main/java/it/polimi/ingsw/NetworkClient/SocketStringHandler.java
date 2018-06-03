package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ClientView.ObserverView;

import java.rmi.RemoteException;

public class SocketStringHandler implements Runnable{
    private SocketController controller;
    private ObserverView observerView;
    private String message;
    private RemoteException exception;
    boolean errorMessage;

    public SocketStringHandler(SocketController controller, ObserverView observerViewView, SocketClientListener listener, String message, boolean errorMessage){
        this.controller = controller;
        this.observerView = observerViewView;
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

