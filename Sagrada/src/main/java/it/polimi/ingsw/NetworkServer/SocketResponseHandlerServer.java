package it.polimi.ingsw.NetworkServer;


import java.rmi.RemoteException;

public class SocketResponseHandlerServer implements Runnable {
    private SocketClient client;
    private String message;
    private RemoteException exception;
    private int messageCodex;

    public SocketResponseHandlerServer(SocketClient client, String message, int messageCodex ){
        this.client = client;
        this.messageCodex = messageCodex;
        this.message = message;
    }
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



