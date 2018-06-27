package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Observable;
import it.polimi.ingsw.model.PlayerState;
import it.polimi.ingsw.model.State;

import java.io.IOException;
import java.rmi.RemoteException;

public class SocketClient implements Observer {
    private SocketResponseHandlerServer stringHandler;
    private SocketServerListener listener;

    public SocketClient( SocketServerListener socketServerListener) {
        this.listener = socketServerListener;
    }
    public void setStringHandler(SocketResponseHandlerServer stringHandler) {
        this.stringHandler = stringHandler;
    }

    @Override
    public synchronized void update(Observable o, Object arg)throws RemoteException {
        ServerMessage message = new UpdateMessage();
        State state = o.getState();
        PlayerState playerState = new PlayerState();
        playerState.setState(state);
        message.setObservable(playerState);
        try{
            listener.sendMessage(message);
        }catch(IOException e){
            throw new RemoteException();
        }
        try{
            wait();
        }catch(InterruptedException e){
            //do something?
        }
        this.stringHandler.check();
        this.stringHandler = null;
    }

    @Override
    public void ping() throws RemoteException {
        ServerMessage message = new PingServerMessage();
        try{
            listener.sendMessage(message);
        }catch(IOException e){
            throw new RemoteException();
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

