package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.io.Serializable;
import java.rmi.RemoteException;

public abstract class ClientMessage implements Serializable{
    int messagecodex;
    String errorMessage;
    String clientname;
    String password;
    String gameName;
    int cardId;
    int diceIndex;
    int row;
    int column;
    int intensity;

    public void setErrorMessage(String errorMessage){
        this.errorMessage =errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setMessagecodex(int messagecodex) {
        this.messagecodex = messagecodex;
    }

    public int getMessagecodex() {
        return messagecodex;
    }

    public void setClientName(String clientName) {
        this.clientname = clientName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setDiceIndex(int diceIndex) {
        this.diceIndex = diceIndex;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    // Metodi getter
    public String getClientName(){
        return this.clientname;
    }

    public String getPassword() {
        return this.password;
    }

    public String getGameName() {
        return this.gameName;
    }


    public int getCardId() {
        return this.cardId;
    }

    public int getDiceIndex() {
        return this.diceIndex;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getIntensity() {
        return this.intensity;
    }

    public  abstract void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) throws RemoteException;


}
