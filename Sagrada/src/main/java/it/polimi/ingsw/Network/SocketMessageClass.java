package it.polimi.ingsw.Network;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.io.Serializable;

public class SocketMessageClass implements Serializable{
    int messagecodex;
    String errorMessage;
    String answermessage;
    ToolRequestClass requestClass;
    // questa classe dovrà essere popolata a seconda delle richieste
    String methodtoinvoke;
    // Parametri delle richieste
    String clientname;
    String password;
    String gameName;
    Observer client;
    int cardId;
    int diceIndex;
    int row;
    int column;
    int intensity;

    //constructor
    public SocketMessageClass() {
        // Do nothing
    }

    public void setAnswermessage(String answermessage) {
        this.answermessage = answermessage;
    }

    public String getAnswermessage() {
        return answermessage;
    }

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

    public String getMethodtoinvoke() {
        if (this.methodtoinvoke == null){
            return "catania";
        }
        return methodtoinvoke;
    }

    public void setMethodtoinvoke(String methodtoinvoke) {
        this.methodtoinvoke = methodtoinvoke;
    }


    public void setRequestClass(ToolRequestClass requestClass){
        this.requestClass = requestClass;
    }


    // Metodi setter
    public void setClientName(String clientName) {
        this.clientname = clientName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setClient(Observer client){
        this.client = client;
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

    public Observer getClient(){
        return this.client;
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

}
