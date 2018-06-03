package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.Client;
import it.polimi.ingsw.Network.SocketMessageClass;

import java.io.IOException;
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
                SocketMessageClass messageClass = new SocketMessageClass();
                messageClass.setMessagecodex(1);
                try {
                    listener.sendMessage(messageClass);
                    System.out.println("ecco fatto");

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
            break;



        }


    }
}
