package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.Client;
import it.polimi.ingsw.Network.SocketMessageClass;

import java.rmi.RemoteException;

public class SocketMessageHandlerServer implements Runnable {
    SocketMessageClass messageClass;
    ClientHandler clientHandler;
    SocketServerListener listener;
    public SocketMessageHandlerServer(SocketMessageClass messageClass, ClientHandler clientHandler, SocketServerListener listener){
        this.messageClass = messageClass;
        this.clientHandler = clientHandler;
        this.listener = listener;
    }
    @Override
    public void run() {
        switch (messageClass.getMethodtoinvoke()){
            case "register" : try{
                clientHandler.register(messageClass.getClientName(), messageClass.getPassword());
                listener.sendString(1,null);
            }catch(RemoteException e){
                listener.sendString(0, e.getMessage());
            }


        }

    }
}
