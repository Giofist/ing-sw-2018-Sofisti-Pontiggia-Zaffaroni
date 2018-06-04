package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.model.ServerMessagePackage.ServerMessage;

public class SocketMessageHandler implements Runnable {
    private SocketController controller;
    private ObserverView observerView;
    private ServerMessage message;
    private SocketClientListener listener;

    public SocketMessageHandler(SocketController controller, ObserverView observerViewView, SocketClientListener listener, ServerMessage message){
        this.controller = controller;
        this.listener = listener;
        this.observerView = observerViewView;
        this.message = message;
    }
    @Override
    public void run() {

        message.performAction(this.observerView, this.listener);
    }
}
