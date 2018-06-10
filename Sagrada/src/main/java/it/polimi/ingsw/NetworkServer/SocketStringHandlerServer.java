package it.polimi.ingsw.NetworkServer;


import java.rmi.RemoteException;

public class SocketStringHandlerServer implements Runnable {
    private SocketClient client;
    private String message;
    private RemoteException exception;
    private int messageCodex;

    public SocketStringHandlerServer(SocketClient client, String message, int messageCodex ){
        this.client = client;
        this.messageCodex = messageCodex;
        this.message = message;
    }
    public  void run(){
        if(this.messageCodex == 0){
            this.exception = new RemoteException(message);
        }
        synchronized (client){
            client.setStringHandler(this);
            client.notifyAll();
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



