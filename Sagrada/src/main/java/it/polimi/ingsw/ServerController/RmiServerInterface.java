package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientController.FeedObserverView;
import it.polimi.ingsw.ClientController.ObserverView;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RmiServerInterface extends Remote {

    // All the methods exposed from the server to the client go here
    //initial test
    public String rmiTest(String stringa) throws RemoteException;


    public void register(String username, String password) throws RemoteException;
    public void login(String clientname, String password) throws RemoteException;
    public  void createGame(String clientname, ObserverView client, FeedObserverView Client, String gamename ) throws  RemoteException;
    public void setSchemeCard(String clientname, int twin, SchemeCard schemeCard) throws RemoteException;
    public String getPrivateGoalCarddescription(String clientname) throws RemoteException;
    public String getPrivateGoalCardname(String clientname) throws RemoteException;
    public int getPrivateGoalCardid(String clientname) throws RemoteException;
    public List getPublicGoalCarddescriptions(String clientname) throws RemoteException;
    public List getPublicGoalCardids(String clientname) throws RemoteException;
    public List getPublicGoalCardnames(String clientname) throws RemoteException;

        // public getActiveMatchList() throws RemoteException;
        // Per questo metodo va deciso come ritornare il risultato
}
