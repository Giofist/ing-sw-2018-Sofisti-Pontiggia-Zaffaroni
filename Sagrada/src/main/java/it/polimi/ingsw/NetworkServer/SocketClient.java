package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Observable;
import it.polimi.ingsw.model.PlayerState;
import it.polimi.ingsw.model.State;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 */
public class SocketClient implements Observer {
    private SocketResponseHandlerServer stringHandler;
    private SocketServerListener listener;

    public SocketClient( SocketServerListener socketServerListener) {
        this.listener = socketServerListener;
    }


    /**
     * @param stringHandler
     */
    public void setStringHandler(SocketResponseHandlerServer stringHandler) {
        this.stringHandler = stringHandler;
    }


    /**
     * Method for pinging the client and waiting for the response
     * @param o
     * @param arg
     * @throws RemoteException Exception thrown if the client throws an error
     */
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
            wait(10000);
        }catch(InterruptedException e){
            //do something?
        }
        if(this.stringHandler == null){
            throw new RemoteException();
        }
        this.stringHandler.check();
        this.stringHandler = null;
    }

    /**
     * Method for pinging the client and waiting for the response
     * @throws RemoteException Exception thrown if the client throws an error
     */
    @Override
    public synchronized void ping() throws RemoteException {
        ServerMessage message = new PingMessage();
        try{
            listener.sendMessage(message);
        }catch(IOException e){
            throw new RemoteException();
        }
        try{

            wait(10000);
        }catch(InterruptedException e){
            //do something?
        }
        if(this.stringHandler == null){
            throw new RemoteException();
        }
        this.stringHandler.check();
        this.stringHandler = null;
    }
}

