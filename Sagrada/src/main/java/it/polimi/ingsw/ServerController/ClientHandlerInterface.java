package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.ObserverView;
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


    void register(String clientname, String password) throws RemoteException;
    void login(String clientname, String password, Observer observer) throws RemoteException;
    void logout(String clientname) throws RemoteException;
    void createGame(String clientname, Observer client, String gamename ) throws  RemoteException;
    void joinaGame(String clientname, Observer  client, String gamename) throws RemoteException;


    String getPrivateGoalCarddescription(String clientname) throws RemoteException;
    String getPrivateGoalCardname(String clientname) throws RemoteException;
    public int getPrivateGoalCardid(String clientname) throws RemoteException;
    public String getPublicGoalCarddescriptions(String clientname) throws RemoteException;
    public String getPublicGoalCardids(String clientname) throws RemoteException;
    public String getPublicGoalCardnames(String clientname) throws RemoteException;
    public String getActiveMatchesList() throws RemoteException;


    //fine partita
    public int getmyPoints(String clientname) throws RemoteException;
    public String getRanking(String clientname) throws RemoteException;
    public List getExtractedSchemeCard(String clientname) throws RemoteException;

    String getSchemeCard(String clientname) throws RemoteException;

    public String getRoundDicepool(String clientname) throws RemoteException;
    public String getRoundTrack(String clientname) throws RemoteException;
    public void setSchemeCard(String clientname, int cardid) throws RemoteException;
    public void setDice(String clientname,int diceindex, int row, int column) throws RemoteException, UserNotExistentException, SchemeCardNotExistantException;

    String getToolCardsCosts(String clientname) throws RemoteException;

    public void useaToolCard(String clientname, ToolRequestClass toolRequestClass) throws RemoteException;

    public String getToolCardsIDs(String clientname) throws RemoteException;
    public String getToolCardsNames(String clientname) throws RemoteException;
    public String getToolCardsDescriptions(String clientname) throws RemoteException;

    public String getPossibleActions(String clientname) throws RemoteException;
    public void passTurn(String clientname) throws RemoteException;
    void leavethematch(String clientname) throws RemoteException;
    public void leavethematchatthend(String clientname) throws RemoteException;
    public void setToolCardDice(String clientname, int row, int column)throws RemoteException;

    public int getToken(String clientname) throws RemoteException;
    public void setToolCardDiceIntensity(String clientname, int intensity) throws RemoteException;
    public  List getPlayersinmymatch (String clientname) throws RemoteException;
    public List getSchemeCardsoftheotherPlayers(String clientname) throws RemoteException;
    public String getToolCardDice(String clientname)throws RemoteException;

    }
