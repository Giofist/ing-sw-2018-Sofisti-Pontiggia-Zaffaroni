package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Observable;

import java.io.IOException;
import java.rmi.RemoteException;

public class SocketClient implements Observer {

    private SocketStringHandlerServer stringHandler;
    private SocketServerListener listener;

    public SocketClient( SocketServerListener socketServerListener) {
        this.listener = socketServerListener;

    }

    public void setStringHandler(SocketStringHandlerServer stringHandler) {
        this.stringHandler = stringHandler;
    }

    @Override
    public synchronized void update(Observable o, Object arg)throws RemoteException {
        ServerMessage message = new ServerMessage();
        message.setMessagecodex(44);
        message.setObservable(o);
        System.out.println(o.getState().toString());
        try{
            listener.sendMessage(message);
        }catch(IOException e){
            throw new RemoteException("problemi di connessione");
        }
        try{
            wait();
        }catch(InterruptedException e){
            //do something?
        }
        this.stringHandler.check();
        this.stringHandler = null;
    }
}
