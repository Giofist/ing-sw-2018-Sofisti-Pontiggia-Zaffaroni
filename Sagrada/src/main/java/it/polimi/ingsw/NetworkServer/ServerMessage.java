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

public abstract class ServerMessage implements Serializable {
    int messagecodex;
    String message;
    Observable observable;
    List list;



    public void setObservable(Observable observable){
        this.observable = observable;
    }
    public void setList(List list) {
        this.list = list;
    }
    public List getList() {
        return list;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getMessagecodex() {
        return messagecodex;
    }
    public void setMessagecodex(int messagecodex) {
        this.messagecodex = messagecodex;
    }


    public  abstract void performAction(Observer view, SocketClientListener listener) throws RemoteException;

}
