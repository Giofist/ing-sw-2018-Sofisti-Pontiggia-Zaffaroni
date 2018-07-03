package it.polimi.ingsw.NetworkServer;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.NetworkClient.SocketClientListener;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;
import it.polimi.ingsw.model.ClientMessagePackage.ResponseMessage;
import it.polimi.ingsw.model.Observable;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

/**
 * This class represents the message send by the server in the socket communication
 */
public abstract class ServerMessage implements Serializable {
    int messagecodex;
    String message;
    Observable observable;
    List list;


    /**
     * @param observable
     */
    public void setObservable(Observable observable){
        this.observable = observable;
    }


    /**
     * @param list List with information we want to send to the client
     */
    public void setList(List list) {
        this.list = list;
    }

    /**
     * @return List with information received by the server
     */
    public List getList() {
        return list;
    }


    /**
     * @return The message send by the client
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message we want to send to the client
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * @return The message id for understanding the type of message: error, ok, request
     */
    public int getMessagecodex() {
        return messagecodex;
    }

    /**
     * @param messagecodex The id of the massage based on the type of response the server want to send
     */
    public void setMessagecodex(int messagecodex) {
        this.messagecodex = messagecodex;
    }


    /**
     * @param view The client that performed the request
     * @param listener A reference to the SocketServerListener
     * @throws RemoteException
     */
    public  abstract void performAction(Observer view, SocketClientListener listener) throws RemoteException;

}
