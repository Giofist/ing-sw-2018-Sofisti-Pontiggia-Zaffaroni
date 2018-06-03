package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.NetworkClient.SocketMessageClass;
import it.polimi.ingsw.ServerController.ClientHandler;

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
            case "login": try{
                clientHandler.login(messageClass.getClientName(), messageClass.getPassword());
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
