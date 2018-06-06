package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class GetMapOfThePlayerMessage extends ClientMessage {

    public GetMapOfThePlayerMessage() { this.messagecodex = 44; }

    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) {

        /*try {
            String answer = clientHandler.getmapofthePlayer(getClientName(), getPlayername());
            ServerMessage messageClass = new ServerMessage();
            messageClass.setMessagecodex(33);
            messageClass.setAnswermessage(answer);

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
        }
        */
    }
}
