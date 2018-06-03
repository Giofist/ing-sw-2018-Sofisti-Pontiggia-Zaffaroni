package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ClientView.ObserverView;

import java.io.IOException;
import java.rmi.RemoteException;

public class SocketMessageHandler implements Runnable {
    private SocketController controller;
    private ObserverView observerView;
    private SocketMessageClass message;
    private SocketClientListener listener;

    public SocketMessageHandler(SocketController controller, ObserverView observerViewView, SocketClientListener listener, SocketMessageClass message){
        this.controller = controller;
        this.listener = listener;
        this.observerView = observerViewView;
        this.message = message;
    }
    @Override
    public void run() {
        switch (message.getMethodtoinvoke()){
            case "update": try{
                observerView.update(null, null);
                SocketMessageClass messageClass = new SocketMessageClass();
                messageClass.setMessagecodex(1);
                try {
                    listener.sendMessage(messageClass);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch(RemoteException e){
                SocketMessageClass messageClass = new SocketMessageClass();
                messageClass.setMessagecodex(0);
                messageClass.setErrorMessage(e.getMessage());
                try {
                    listener.sendMessage(messageClass);
                }catch (IOException err){
                    e.printStackTrace();
                }

            }
        }
    }
}
