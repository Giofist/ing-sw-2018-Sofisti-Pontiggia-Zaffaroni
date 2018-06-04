package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ClientView.Client;
import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.ClientMessagePackage.*;
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
        ClientMessage logoutMessage = new LogoutMessage();
        logoutMessage.setClientName(username);

        try {
            listener.sendMessage(logoutMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public synchronized void createGame(String username, Observer client, String gamename) throws RemoteException {
        ClientMessage createGameMessage = new CreateGameMessage();
        createGameMessage.setClientName(username);
        createGameMessage.setGameName(gamename);

        try {
            listener.sendMessage(createGameMessage);
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
    public synchronized void joinaGame(String username, Observer client, String gamename) throws RemoteException {
        ClientMessage joinaGameMessage = new JoinaGameMessage();
        joinaGameMessage.setClientName(username);
        joinaGameMessage.setGameName(gamename);

        try {
            listener.sendMessage(joinaGameMessage);
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
    public synchronized void setSchemeCard(String username, int cardid) {
        ClientMessage setSchemeCardMessage = new SetSchemeCardMessage();
        setSchemeCardMessage.setClientName(username);
        setSchemeCardMessage.setCardId(cardid);

        try {
            listener.sendMessage(setSchemeCardMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void setDice(String clientname, int diceindex, int row, int column) {
        ClientMessage setDiceMessage = new SetDiceMessage();
        setDiceMessage.setClientName(clientname);
        setDiceMessage.setDiceIndex(diceindex);
        setDiceMessage.setRow(row);
        setDiceMessage.setColumn(column);

        try {
            listener.sendMessage(setDiceMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getToolCardsCosts(String clientname) throws RemoteException {
        ClientMessage getToolCardsCostMessage = new GetToolCardCostMessage();
        getToolCardsCostMessage.setClientName(clientname);

        try {
            listener.sendMessage(getToolCardsCostMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void useaToolCard(String clientname, ToolRequestClass requestClass) throws RemoteException {
        ClientMessage useToolCardMessage = new UseToolCardMessage();
        useToolCardMessage.setClientName(clientname);
        useToolCardMessage.setRequestClass(requestClass);

        try {
            listener.sendMessage(useToolCardMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getToolCardsIDs(String clientname) throws RemoteException {
        ClientMessage getToolCardsIDsMessage = new GetToolCardsIDsMessage();
        getToolCardsIDsMessage.setClientName(clientname);

        try {
            listener.sendMessage(getToolCardsIDsMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getToolCardsNames(String clientname) throws RemoteException {
        ClientMessage getToolCardsNamesMessage = new GetToolCardsNamesMessage();
        getToolCardsNamesMessage.setClientName(clientname);

        try {
            listener.sendMessage(getToolCardsNamesMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getToolCardsDescriptions(String clientname) throws RemoteException {
        ClientMessage getToolCardsDescriptionsMessage = new GetToolCardsDescriptionsMessage();
        getToolCardsDescriptionsMessage.setClientName(clientname);

        try {
            listener.sendMessage(getToolCardsDescriptionsMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getPossibleActions(String clientname) throws RemoteException {
        ClientMessage getPossibleActionsMessage = new GetPossibleActionsMessage();
        getPossibleActionsMessage.setClientName(clientname);

        try {
            listener.sendMessage(getPossibleActionsMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void passTurn(String clientname) throws RemoteException {
        ClientMessage passTurnMessage = new PassTurnMessage();
        passTurnMessage.setClientName(clientname);

        try {
            listener.sendMessage(passTurnMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void leavethematch(String clientname) throws RemoteException {
        ClientMessage leaveTheMatchMessage = new LeaveTheMatchMessage();
        leaveTheMatchMessage.setClientName(clientname);

        try {
            listener.sendMessage(leaveTheMatchMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setToolCardDice(String clientname, int row, int column) throws RemoteException {
        ClientMessage setToolCardDiceMessage = new SetToolCardDiceMessage();
        setToolCardDiceMessage.setClientName(clientname);
        setToolCardDiceMessage.setRow(row);
        setToolCardDiceMessage.setColumn(column);

        try {
            listener.sendMessage(setToolCardDiceMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setToolCardDiceIntensity(String clientname, int intensity) throws RemoteException {
        ClientMessage setToolCardDiceIntesityMessage = new SetToolCardDiceIntensityMessage();
        setToolCardDiceIntesityMessage.setClientName(clientname);
        setToolCardDiceIntesityMessage.setIntensity(intensity);

        try {
            listener.sendMessage(setToolCardDiceIntesityMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getPlayersinmymatch(String clientname) throws RemoteException {
        ClientMessage getPlayerIsInMatchMessage = new GetPlayerIsInMatchMessage();
        getPlayerIsInMatchMessage.setClientName(clientname);

        try {
            listener.sendMessage(getPlayerIsInMatchMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getmapofthePlayer(String clientname, String player) throws RemoteException {
        ClientMessage getMapOfThePlayerMessage = new GetMapOfThePlayerMessage();
        getMapOfThePlayerMessage.setClientName(clientname);
        getMapOfThePlayerMessage.setPlayername(player);

        try {
            listener.sendMessage(getMapOfThePlayerMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getToolCardDice(String clientname) throws RemoteException {
        ClientMessage getToolCardDiceMessage = new GetToolCardDiceMessage();
        getToolCardDiceMessage.setClientName(clientname);

        try {
            listener.sendMessage(getToolCardDiceMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getPrivateGoalCarddescription(String clientname) throws RemoteException {
        ClientMessage getPrivateGoalCardDescriptionMessage = new GetPrivateGoalCardDescriptionMessage();
        getPrivateGoalCardDescriptionMessage.setClientName(clientname);

        try {
            listener.sendMessage(getPrivateGoalCardDescriptionMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getPrivateGoalCardname(String clientname) throws RemoteException {
        ClientMessage getPrivateGoalCardNameMessage = new GetPrivateGoalCardNameMessage();
        getPrivateGoalCardNameMessage.setClientName(clientname);

        try {
            listener.sendMessage(getPrivateGoalCardNameMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int getPrivateGoalCardid(String clientname) throws RemoteException {
        ClientMessage getPrivateGoalCardIdMessage = new GetPrivateGoalCardIdMessage();
        getPrivateGoalCardIdMessage.setClientName(clientname);

        try {
            listener.sendMessage(getPrivateGoalCardIdMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public String getPublicGoalCarddescriptions(String clientname) throws RemoteException {
        ClientMessage getPublicGoalCardDescriptionMessage = new GetPublicGoalCardDescriptionMessage();
        getPublicGoalCardDescriptionMessage.setClientName(clientname);

        try {
            listener.sendMessage(getPublicGoalCardDescriptionMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getPublicGoalCardids(String clientname) throws RemoteException {
        ClientMessage getPublicGoalCardIdsMessage = new GetPublicGoalCardIdsMessage();
        getPublicGoalCardIdsMessage.setClientName(clientname);

        try {
            listener.sendMessage(getPublicGoalCardIdsMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getPublicGoalCardnames(String clientname) throws RemoteException {
        ClientMessage getPublicGoalCardNamesMessage = new GetPublicGoalCardNamesMessage();
        getPublicGoalCardNamesMessage.setClientName(clientname);

        try {
            listener.sendMessage(getPublicGoalCardNamesMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public synchronized String getActiveMatchesList(String clientname) throws RemoteException {
        ClientMessage getActiveMatchesListMessage = new GetActiveMatchesListMessage();
        getActiveMatchesListMessage.setClientName(clientname);

        try {
            listener.sendMessage(getActiveMatchesListMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int getmyPoints(String clientname) throws RemoteException {
        ClientMessage getMyPointsMessage = new GetMyPointsMessage();
        getMyPointsMessage.setClientName(clientname);

        try {
            listener.sendMessage(getMyPointsMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public String getRanking(String username) throws RemoteException {
        ClientMessage getRankingMessage = new GetRankingMessage();
        getRankingMessage.setClientName(username);

        try {
            listener.sendMessage(getRankingMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public String getSchemeCards(String clientname) throws RemoteException {
        ClientMessage getSchemeCardsMessage = new GetSchemeCardsMessage();
        getSchemeCardsMessage.setClientName(clientname);

        try {
            listener.sendMessage(getSchemeCardsMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getSchemeCard(String clientname) throws RemoteException {
        ClientMessage getSchemeCardMessage = new GetSchemeCardMessage();
        getSchemeCardMessage.setClientName(clientname);

        try {
            listener.sendMessage(getSchemeCardMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getRoundDicepool(String clientname) throws RemoteException {
        ClientMessage getRoundDicePoolMessage = new GetRoundDicePoolMessage();
        getRoundDicePoolMessage.setClientName(clientname);

        try {
            listener.sendMessage(getRoundDicePoolMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getRoundTrack(String clientname) throws RemoteException {
        ClientMessage getRoundTrackMessage = new GetRoundTrackMessage();
        getRoundTrackMessage.setClientName(clientname);

        try {
            listener.sendMessage(getRoundTrackMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}