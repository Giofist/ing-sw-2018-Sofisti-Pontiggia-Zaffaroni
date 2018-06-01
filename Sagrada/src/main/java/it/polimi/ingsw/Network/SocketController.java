package it.polimi.ingsw.Network;

import it.polimi.ingsw.ClientView.FeedObserverView;
import it.polimi.ingsw.ClientView.ObserverViewInterface;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.UserNotExistentException;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.io.IOException;
import java.rmi.RemoteException;

// implemented by pon
public class SocketController implements ClientHandlerInterface {

    private ObserverViewInterface observerView;
    private SocketStringHandler stringHandler;
    private SocketListener listener;

    public SocketController( ObserverViewInterface observerView, SocketListener socketListener)  throws IOException {
        this.listener = socketListener;
        this.observerView = observerView;
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
        listener.sendString("register " + username + " " + password);
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
        listener.sendString("login " + username + " " + password);
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        this.stringHandler = null;
    }

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
    public synchronized void createGame(String username, ObserverViewInterface client, FeedObserverView Client, String gamename) throws RemoteException {
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
    public synchronized void joinaGame(String username, ObserverViewInterface client, FeedObserverView Client,String gamename) throws RemoteException {
        listener.sendString("joinaGame " + username + " " + gamename);
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        this.stringHandler = null;

    }

    @Override
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

    @Override
    public synchronized void setSchemeCard(String username, int cardid) throws RemoteException {
        listener.sendString("joinaGame " + username + " " + cardid);
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
        listener.sendString("joinaGame " + clientname  + diceindex + row + column);
        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.stringHandler.check();
        this.stringHandler = null;
    }

    @Override
    public void useaToolCard(String clientname, ToolRequestClass requestClass) throws RemoteException {
        SocketMessageClass message = new SocketMessageClass(clientname);
        message.setRequestClass(requestClass);
        try{
            listener.sendMessage(message);
        }catch (IOException e){
            throw new RemoteException(e.getMessage());
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
