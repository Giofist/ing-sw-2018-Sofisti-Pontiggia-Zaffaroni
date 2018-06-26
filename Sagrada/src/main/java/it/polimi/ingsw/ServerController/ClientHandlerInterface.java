package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.NetworkClient.SocketResponseHandler;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.PlayerPackage.TurnActions;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.ToolCard.ToolAction;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;


public interface ClientHandlerInterface extends Remote {
    void setResponseHandler(SocketResponseHandler responseHandler)throws RemoteException;


    void createGame(String clientname, Observer client, String gamename ) throws  RemoteException;

    List<Match> getActiveMatchesList() throws RemoteException;
    List<SchemeCard> getExtractedSchemeCard(String clientname) throws RemoteException;
    int getmyPoints(String clientname) throws RemoteException;
    List getPlayersinmymatch(String clientname) throws RemoteException;
    List<TurnActions> getPossibleActions(String clientname) throws RemoteException;
    List<GoalCard> getPrivateGoalCard(String clientname) throws RemoteException;
    List<GoalCard> getPublicGoalCards(String clientname) throws RemoteException;
    List getRanking(String clientname) throws RemoteException;
    String getRoundDicepool(String clientname) throws RemoteException;
    String getRoundTrack(String clientname) throws RemoteException;
    List<SchemeCard> getSchemeCard(String clientname) throws RemoteException;
    List<SchemeCard> getSchemeCardsoftheotherPlayers(String clientname) throws RemoteException;
    int getToken(String clientname) throws RemoteException;
    String getToolCardDice(String clientname)throws RemoteException;
    List<ToolAction> getToolCards(String clientname) throws RemoteException;
    void joinaMatch(String clientname, Observer  client, String gamename) throws RemoteException;
    void leavethematch(String clientname) throws RemoteException;
    void login(String clientname, String password, Observer observer) throws RemoteException;
    void logout(String clientname) throws RemoteException;
    void passTurn(String clientname) throws RemoteException;
    void register(String clientname, String password) throws RemoteException;
    void setDice(String clientname, int diceindex, int row, int column) throws RemoteException;
    void setSchemeCard(String clientname, int cardid) throws RemoteException;
    void setToolCardDice(String clientname, int row, int column)throws RemoteException;
    void setToolCardDiceIntensity(String clientname, int intensity) throws RemoteException;
    void useaToolCard(String clientname, ToolRequestClass toolRequestClass) throws RemoteException;
    }
