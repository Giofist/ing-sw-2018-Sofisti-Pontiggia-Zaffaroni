package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class GetSchemeCardsMessage extends ClientMessage {

    public GetSchemeCardsMessage() { this.messagecodex = 44; }

    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) {

        /*try {
            //String answer = clientHandler.getExtractedSchemeCard(getClientName());
            ServerMessage messageClass = new ServerMessage();
            messageClass.setMessagecodex(33);
            messageClass.setAnswermessage();

            try {
                listener.sendMessage(messageClass);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            ServerMessage messageClass = new ServerMessage();
            messageClass.setMessagecodex(0);
            messageClass.setErrorMessage(e.getMessage());

            try {
                listener.sendMessage(messageClass);
            } catch (IOException err) {
                e.printStackTrace();
            }
        }*/
    }
}
