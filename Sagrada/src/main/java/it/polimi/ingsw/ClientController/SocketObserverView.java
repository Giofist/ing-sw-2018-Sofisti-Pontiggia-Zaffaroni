package it.polimi.ingsw.ClientController;

import it.polimi.ingsw.ServerController.ClientHandler;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;

// implemented by pon
public class SocketObserverView implements ClientHandlerInterface{
    private Socket socket;
    private ObserverViewInterface observerView;

    public SocketObserverView(Socket socket, ObserverViewInterface observerView){
        this.socket = socket;
        this.observerView = observerView;
    }



    //all methods to be implemented here
    @Override
    public String rmiTest(String stringa) throws RemoteException {
        return null;
    }

    @Override
    public void register(String username, String password) throws RemoteException {

    }

    @Override
    public void login(String clientname, String password) throws RemoteException {

    }

    @Override
    public void createGame(String clientname, ObserverViewInterface client, FeedObserverView Client, String gamename) throws RemoteException {

    }

    @Override
    public void joinaGame(String clientname, String gamename) throws RemoteException {

    }

    @Override
    public void setSchemeCard(String clientname, int twin, SchemeCard schemeCard) throws RemoteException {

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
    public List getPublicGoalCarddescriptions(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public List getPublicGoalCardids(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public List getPublicGoalCardnames(String clientname) throws RemoteException {
        return null;
    }
}
