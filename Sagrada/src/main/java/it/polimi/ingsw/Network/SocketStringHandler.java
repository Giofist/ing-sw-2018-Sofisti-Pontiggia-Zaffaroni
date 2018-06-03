package it.polimi.ingsw.Network;

import it.polimi.ingsw.ClientView.ObserverView;

import java.rmi.RemoteException;

public class SocketStringHandler implements Runnable{
    private SocketController controller;
    private ObserverView observerViewView;
    private String message;
    private RemoteException exception;
    boolean errorMessage;

    public SocketStringHandler(SocketController controller, ObserverView observerViewView, SocketClientListener listener, String message, boolean errorMessage){
        this.controller = controller;
        this.observerViewView = observerViewView;
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
            System.out.println("Sono arrivato qui\n");
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

