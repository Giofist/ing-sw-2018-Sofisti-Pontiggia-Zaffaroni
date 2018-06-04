package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.ClientMessagePackage.LoginMessage;
import it.polimi.ingsw.model.ClientMessagePackage.RegisterMessage;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.UserNotExistentException;
import it.polimi.ingsw.model.ClientMessagePackage.ClientMessage;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.io.IOException;
import java.rmi.RemoteException;

// implemented by pon
public class SocketController implements ClientHandlerInterface {

    private ObserverView observerViewView;
    private SocketStringHandler stringHandler;
    private SocketClientListener listener;

    public SocketController(ObserverView observerViewView, SocketClientListener socketClientListener)  throws IOException {
        this.listener = socketClientListener;
        this.observerViewView = observerViewView;
    }

   public void setStringHandler(SocketStringHandler stringHandler){
        this.stringHandler = stringHandler;
   }


    //all methods to be implemented here
    @Override
    public String rmiTest(String stringa) throws RemoteException {
        return null;
    }

    @Override
    public synchronized void register(String username, String password) throws RemoteException {

        ClientMessage registerMessage = new RegisterMessage();
        registerMessage.setMessagecodex(44);
        registerMessage.setClientName(username);
        registerMessage.setPassword(password);
        try {
            listener.sendMessage(registerMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        this.stringHandler = null;

    }

    @Override
    public synchronized void   login(String username, String password) throws RemoteException {
        ClientMessage loginMessage = new LoginMessage();
        loginMessage.setClientName(username);
        loginMessage.setPassword(password);
        try {
            listener.sendMessage(loginMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //da qui in poi è tutto da rifare, ma una volta che hai la struttura, non cè niente da pensare
    @Override
    public synchronized void logout(String username) throws RemoteException {
        listener.sendString("logout " + username);
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        this.stringHandler = null;
    }

    @Override
    public synchronized void createGame(String username, Observer client, String gamename) throws RemoteException {
        listener.sendString("createGame " + username + " " + gamename);
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        this.stringHandler = null;
    }



    @Override
    public synchronized void joinaGame(String username, Observer client, String gamename) throws RemoteException {
        listener.sendString("joinaGameMessage " + username + " " + gamename);
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        this.stringHandler = null;

    }

    /*@Override
    public synchronized String getMymapString(String clientname) throws RemoteException {
        listener.sendString("getMymapString " + clientname);
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        String message = this.stringHandler.getMessage();
        this.stringHandler = null;
        return message;
    }
    */

    @Override
    public synchronized void setSchemeCard(String username, int cardid) throws RemoteException {
        listener.sendString("joinaGameMessage " + username + " " + cardid);
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        this.stringHandler = null;

    }

    @Override
    public void setDice(String clientname, int diceindex, int row, int column) throws RemoteException, UserNotExistentException, SchemeCardNotExistantException {
        listener.sendString("joinaGameMessage " + clientname  + diceindex + row + column);
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        this.stringHandler = null;
    }

    @Override
    public String getToolCardsCosts(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public void useaToolCard(String clientname, ToolRequestClass requestClass) throws RemoteException {
        //ClientMessage message = new ClientMessage(clientname);
        //message.setRequestClass(requestClass);

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        this.stringHandler = null;
    }

    @Override
    public String getToolCardsIDs(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getToolCardsNames(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getToolCardsDescriptions(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getPossibleActions(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public void passTurn(String clientname) throws RemoteException {

    }

    @Override
    public void leavethematch(String clientname) throws RemoteException {

    }

    @Override
    public void setToolCardDice(String clientname, int row, int column) throws RemoteException {

    }

    @Override
    public void setToolCardDiceIntensity(String clientname, int intensity) throws RemoteException {

    }

    @Override
    public String getPlayersinmymatch(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getmapofthePlayer(String clientname, String player) throws RemoteException {
        return null;
    }

    @Override
    public String getToolCardDice(String clientname) throws RemoteException {
        return null;
    }


    @Override
    public String getPrivateGoalCarddescription(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getPrivateGoalCardname(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public int getPrivateGoalCardid(String clientname) throws RemoteException {
        return 0;
    }

    @Override
    public String getPublicGoalCarddescriptions(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getPublicGoalCardids(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getPublicGoalCardnames(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public synchronized String getActiveMatchesList() throws RemoteException {
        listener.sendString("getActiveMatchList ");
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        String message = this.stringHandler.getMessage();
        this.stringHandler = null;
        return message;
    }

    @Override
    public int getmyPoints(String clientname) throws RemoteException {
        return 0;
    }

    @Override
    public String getRanking(String username) throws RemoteException {
        return null;

    }

    @Override
    public String getSchemeCards(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getSchemeCard(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getRoundDicepool(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getRoundTrack(String clientname) throws RemoteException {
        return null;
    }
}
