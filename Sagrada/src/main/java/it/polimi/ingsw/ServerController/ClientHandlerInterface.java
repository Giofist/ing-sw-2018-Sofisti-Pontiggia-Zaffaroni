package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.FeedObserverView;
import it.polimi.ingsw.ClientView.ObserverViewInterface;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.UserNotExistentException;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ClientHandlerInterface extends Remote {

    // All the methods exposed from the server to the client go here
    //initial test
    public String rmiTest(String stringa) throws RemoteException;


    public void register(String username, String password) throws RemoteException;
    public void login(String clientname, String password) throws RemoteException;
    public void logout(String clientname) throws RemoteException;
    public  void createGame(String clientname, ObserverViewInterface client, FeedObserverView Client, String gamename ) throws  RemoteException;
    public void joinaGame(String clientname, ObserverViewInterface client, FeedObserverView Client, String gamename) throws RemoteException;

    public String getMymapString(String clientname) throws RemoteException;

    public String getPrivateGoalCarddescription(String clientname) throws RemoteException;
    public String getPrivateGoalCardname(String clientname) throws RemoteException;
    public int getPrivateGoalCardid(String clientname) throws RemoteException;
    public String getPublicGoalCarddescriptions(String clientname) throws RemoteException;
    public String getPublicGoalCardids(String clientname) throws RemoteException;
    public String getPublicGoalCardnames(String clientname) throws RemoteException;
    public String getActiveMatchList() throws RemoteException;


    //fine partita
    public int getmyPoints(String clientname) throws RemoteException;
    public String getRanking(String clientname) throws RemoteException;
    public String getSchemeCards(String clientname) throws RemoteException;

    String getSchemeCard(String clientname);

    public String getRoundDicepool(String clientname) throws RemoteException;
    public String getRoundTrack(String clientname) throws RemoteException;
    public void setSchemeCard(String clientname, int cardid) throws RemoteException;
    public void setDice(String clientname,int diceindex, int row, int column) throws RemoteException, UserNotExistentException, SchemeCardNotExistantException;
    public void useaToolCard(String clientname, ToolRequestClass toolRequestClass) throws RemoteException;

    public String getToolCards(String clientname) throws RemoteException;

    public String getPossibleActions(String clientname) throws RemoteException;
    public void passTurn(String clientname) throws RemoteException;
}
