package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Observable;

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
    public void update(Observable o, Object arg) {

    }
}
