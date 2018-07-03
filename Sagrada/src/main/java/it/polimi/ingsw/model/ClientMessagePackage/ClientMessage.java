package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Class used for messages sent by the client with actions to be performed by the server
 */
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
<<<<<<< HEAD

=======
>>>>>>> ed51e217d4a95bcf4859fc9a2219241cb3fe8475

    /**
     * @param errorMessage Set the error message that you want to return
     */
    public void setErrorMessage(String errorMessage){
        this.errorMessage =errorMessage;
    }

    /**
     * @return The detail of the error associated to the message
     */
    public String getErrorMessage() {
        return errorMessage;
    }


    /**
     * @param messagecodex The id corresponding to the type of message I want to return
     */
    public void setMessagecodex(int messagecodex) {
        this.messagecodex = messagecodex;
    }

    /**
     * @return The id corresponding to the type of message
     */
    public int getMessagecodex() {
        return messagecodex;
    }
    


    // Metodi setter


    /**
     * @param clientName The username I want to pass to be set
     */
    public void setClientName(String clientName) {
        this.clientname = clientName;
    }

    /**
     * @param password The password I want to pass to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param gameName The name I want to assign to the match
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * @param cardId The scheme card id I want to set or retrieve
     */
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    /**
     * @param diceIndex The index of the dice I want to interact with
     */
    public void setDiceIndex(int diceIndex) {
        this.diceIndex = diceIndex;
    }

    /**
     * @param row The row I want to access
     */
    public void setRow(int row){
        this.row = row;
    }

    /**
     * @param column The column I want to access
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * @param intensity The intensity specified for the special dice for the particular tool card
     */
    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    // Metodi getter

    /**
     * @return The username set in the massage
     */
    public String getClientName(){
        return this.clientname;
    }

    /**
     * @return The the password passed in the massage
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @return The name for the match set in the message
     */
    public String getGameName() {
        return this.gameName;
    }

    /**
     * @return The id of the scheme card the user wants to interact with
     */
    public int getCardId() {
        return this.cardId;
    }

    /**
     * @return The index of the dice the user specified in the message
     */
    public int getDiceIndex() {
        return this.diceIndex;
    }

    /**
     * @return The row of the scheme card specified for the interaction
     */
    public int getRow(){
        return this.row;
    }

    /**
     * @return The column of the scheme card specified for the interaction
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * @return The intensity specified for the special dice for the particular tool card
     */
    public int getIntensity() {
        return this.intensity;
    }

    /**
     * @param clientHandler The interface with all the methods exposed by the server
     * @param listener The socket server listener waiting for messages from the client
     * @throws RemoteException
     */
    public  abstract void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) throws RemoteException;


}
