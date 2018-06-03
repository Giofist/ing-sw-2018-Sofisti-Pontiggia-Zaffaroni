package it.polimi.ingsw.model.ServerMessagePackage;

import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.NetworkClient.SocketClientListener;
import it.polimi.ingsw.model.ClientMessagePackage.ResponseMessage;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

public class ServerMessage implements Serializable {
    int messagecodex;
    String errorMessage;
    String answermessage;

    public String getAnswermessage() {
        return answermessage;
    }

    public void setAnswermessage(String answermessage) {
        this.answermessage = answermessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getMessagecodex() {
        return messagecodex;
    }

    public void setMessagecodex(int messagecodex) {
        this.messagecodex = messagecodex;
    }
    public void performAction(ObserverView view, SocketClientListener listener) {
        try {
            view.update(null, null);
            ClientMessage messageClass = new ResponseMessage();
            messageClass.setMessagecodex(1);
            try {
                listener.sendMessage(messageClass);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            ClientMessage messageClass = new ResponseMessage();
            messageClass.setMessagecodex(0);
            messageClass.setErrorMessage(e.getMessage());
            try {
                listener.sendMessage(messageClass);
            } catch (IOException err) {
                e.printStackTrace();
            }
        }
    }

}
