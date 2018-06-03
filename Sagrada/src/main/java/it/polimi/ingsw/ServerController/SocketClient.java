package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.Network.SocketClientListener;
import it.polimi.ingsw.Network.SocketStringHandler;
import it.polimi.ingsw.model.Observable;

import java.io.IOException;
import java.rmi.RemoteException;

public class SocketClient implements Observer {

    private SocketStringHandlerServer stringHandler;
    private SocketServerListener listener;

    public SocketClient( SocketServerListener socketServerListener)  throws IOException {
        this.listener = socketServerListener;

    }

    public void setStringHandler(SocketStringHandlerServer stringHandler) {
        this.stringHandler = stringHandler;
    }

    @Override
    public void update(Observable o, Object arg) throws RemoteException {

    }
}
