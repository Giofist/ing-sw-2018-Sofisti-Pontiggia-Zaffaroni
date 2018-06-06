package it.polimi.ingsw.model.ClientMessagePackage;

import it.polimi.ingsw.NetworkServer.SocketServerListener;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class LoginMessage extends ClientMessage {

    public LoginMessage(){
        this.messagecodex = 44;
    }

    @Override
    public void performAction(ClientHandlerInterface clientHandler, SocketServerListener listener) {

        /*try{
            clientHandler.login(this.getClientName(), this.getPassword(),);
            ServerMessage messageClass = new ServerMessage();
            messageClass.setMessagecodex(1);

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
