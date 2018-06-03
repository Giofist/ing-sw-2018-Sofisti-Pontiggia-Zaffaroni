package it.polimi.ingsw.NetworkServer;


import java.rmi.RemoteException;

public class SocketStringHandlerServer implements Runnable {
    private SocketClient client;
    SocketServerListener listener;
    private String message;
    private RemoteException exception;

    public SocketStringHandlerServer(SocketClient client,  SocketServerListener listener, String message){
        this.client = client;
        this.listener = listener;
        this.message = message;
    }
    public  void run(){
        System.out.println(message);
        switch(message){
            case "OK":
                break;
            default:
                this.exception = new RemoteException(message);
                break;
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



