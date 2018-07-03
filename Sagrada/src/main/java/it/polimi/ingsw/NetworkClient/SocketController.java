package it.polimi.ingsw.NetworkClient;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.ClientMessagePackage.*;
import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;


/**
 * Methods offered by the server, here implemented client side when performing a request
 */
public class SocketController implements ClientHandlerInterface {

    private SocketResponseHandler responseHandler;
    private SocketClientListener listener;

    public SocketController(SocketClientListener socketClientListener) {
        this.listener = socketClientListener;
    }


    /**
     * @param responseHandler
     * @throws RemoteException
     */
    public void setResponseHandler(SocketResponseHandler responseHandler)throws RemoteException{
        this.responseHandler = responseHandler;
   }


    /**
     * This method is how the client invoke a request for registering
     * @param username The new username for the account
     * @param password The new password associated to the new account
     * @throws RemoteException Exception thrown in case there is another account already registered with the same username
     */
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
        this.responseHandler.check();
        this.responseHandler = null;

    }


    /**
     * This method is how the client invoke a request for logging in
     * @param clientname The username of the account
     * @param password The password associated to the account
     * @param observer The ObserverView object that represents the client
     * @throws RemoteException Exception thrown in 2 different scenarios: the user il already logged in from another computer, the username or password provided are incorrect
     */
    @Override
    public synchronized void login(String clientname, String password, Observer observer)throws RemoteException{
        ClientMessage loginMessage = new LoginMessage();
        loginMessage.setClientName(clientname);
        loginMessage.setPassword(password);
        try {
            listener.sendMessage(loginMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        this.responseHandler = null;
    }


    /**
     * This method is how the client invoke a request for logging out
     * @param username The username of the account from where we want to log out
     * @param observer The ObserverView object to be removed from the observers on the server (basically the client that wants to log out)
     * @throws RemoteException
     */
    @Override
    public synchronized void logout(String username, Observer observer) throws RemoteException {
        ClientMessage logoutMessage = new LogoutMessage();
        logoutMessage.setClientName(username);

        try {
            listener.sendMessage(logoutMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        this.responseHandler = null;

    }


    /**
     * This method is how the client invoke a request for creating a new game
     * @param username The username of the account that wants to create a new game
     * @param client The Observer object that represents the client
     * @param gamename The name for the new match
     * @throws RemoteException Exception thrown in 2 possible situations: the name for the new match is already taken, we are not able to find the user that performed the request in the UsersList on the server
     */
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
        this.responseHandler.check();
        this.responseHandler = null;
    }


    /**
     * This method is how the client invoke a request for joining a match
     * @param username The username of the account
     * @param client The Observer performing the request
     * @param gamename The name of the match we want to join
     * @throws RemoteException Exception thrown in 2 possible situations: the match's name where we want to join is invalid, the username provided for the request is invalid
     */
    @Override
    public synchronized void joinaMatch(String username, Observer client, String gamename) throws RemoteException {
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
        this.responseHandler.check();
        this.responseHandler = null;

    }


    /**
     * This method is how the client invoke a request for setting the scheme card he had chosen
     * @param username The username of the account performing the request
     * @param cardid The id associated to the chosen scheme card
     * @throws RemoteException Exception thrown in 3 different situations: the provided username is not in the UsersList, the provided id is invalid, the player is not in a state where he can set a tool card
     */
    @Override
    public synchronized void setSchemeCard(String username, int cardid) throws RemoteException {
        ClientMessage setSchemeCardMessage = new SetSchemeCardMessage();
        setSchemeCardMessage.setClientName(username);
        setSchemeCardMessage.setCardId(cardid);
        try {
            listener.sendMessage(setSchemeCardMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        this.responseHandler = null;
    }


    /**
     * This method is how the client invoke a request for positioning a dice on a scheme card
     * @param clientname The username of the account performing the request
     * @param diceindex The index of the dice in the dicepool we want to put on the scheme card
     * @param row The row where we want to place the dice
     * @param column The column where we want to place the dice
     * @throws RemoteException Exception thrown for several reasons: the username is not in the UsersList, we are not able to retrieve the scheme card
     * from the player, the diceIndex is an invalid one, we are not allowed to perform the action in the current state,
     * we are not respecting a constrain of the specified position, the specified position is out from the scheme card
     */
    @Override
    public synchronized void setDice(String clientname, int diceindex, int row, int column) throws RemoteException {
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

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        this.responseHandler = null;
    }


    /**
     * This method is how the client invoke a request for using a tool card
     * @param clientname The username of the account performing the request
     * @param requestClass A class with all the necessary parameters for performing the action (see the documentation for each tool card to see which parameters are required)
     * @throws RemoteException Exception thrown in different situations: something was wrong in performing the tool card action, wrong state in which to perform the action, the username is not in the UsersList
     */
    @Override
    public synchronized void useaToolCard(String clientname, ToolRequestClass requestClass) throws RemoteException {
        UseToolCardMessage useToolCardMessage = new UseToolCardMessage();
        useToolCardMessage.setClientName(clientname);
        useToolCardMessage.setRequestClass(requestClass);

        try {
            listener.sendMessage(useToolCardMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        this.responseHandler = null;
    }


    /**
     * This method is how the client invoke a request for receiving the tool cards extracted for the match
     * @param clientname The username of the account performing the request
     * @return A list of the available tool cards
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized List getToolCards(String clientname) throws RemoteException {
        ClientMessage getToolCards = new GetToolCardsMessage();
        getToolCards.setClientName(clientname);

        try {
            listener.sendMessage(getToolCards);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        List list = this.responseHandler.getList();
        this.responseHandler = null;

        return list;
    }


    /**
     * This method is how the client invoke a request for getting the list of possible actions in the current state
     * @param clientname The username of the account performing the request
     * @return List of the possible actions
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized List getPossibleActions(String clientname) throws RemoteException {
        ClientMessage getPossibleActionsMessage = new GetPossibleActionsMessage();
        getPossibleActionsMessage.setClientName(clientname);

        try {
            listener.sendMessage(getPossibleActionsMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        this.responseHandler.check();
        List list  = this.responseHandler.getList();
        this.responseHandler = null;

        return list;
    }

    /**
     * This method is how the client invoke a request for passing the turn
     * @param clientname The username of the account performing the request
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server or when the action is not allowed in the current state
     */
    @Override
    public synchronized void passTurn(String clientname) throws RemoteException {
        ClientMessage passTurnMessage = new PassTurnMessage();
        passTurnMessage.setClientName(clientname);

        try {
            listener.sendMessage(passTurnMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        this.responseHandler = null;
    }


    /**
     * This method is how the client invoke a request for leaving the match he is in
     * @param clientname The username of the account performing the request
     * @param observer The Observer object that represents the client
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server or when the action is not allowed in the current state
     */
    @Override
    public synchronized void leavethematch(String clientname, Observer observer) throws RemoteException{
        ClientMessage message = new LeavethematchMessage();
        message.setClientName(clientname);

        try {
            listener.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        this.responseHandler = null;
    }


    /**
     * This method is how the client invoke a request for setting the special dice connected to the particular tool card
     * @param clientname The username of the account performing the request
     * @param row The row where we want to put the dice
     * @param column The column where we want to put the dice
     * @throws RemoteException Exception thrown in different situations: the specified username is not in the UsersList,  the action performed is not allowed in the current state,
     * the new position specified is out of the scheme card, there was some problem in getting the scheme card, the dice to be placed doesn't exist
     */
    @Override
    public synchronized void setToolCardDice(String clientname, int row, int column) throws RemoteException {
        ClientMessage setToolCardDiceMessage = new SetToolCardDiceMessage();
        setToolCardDiceMessage.setClientName(clientname);
        setToolCardDiceMessage.setRow(row);
        setToolCardDiceMessage.setColumn(column);
        try {
            listener.sendMessage(setToolCardDiceMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        this.responseHandler = null;
    }


    /**
     * This method is how the client invoke a request for receiving how many segnalini does the player have
     * @param clientname The username of the account performing the request
     * @return The number of Segnalini Favore
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized int getToken(String clientname)throws RemoteException {
        ClientMessage message = new GetTokenMessage();
        message.setClientName(clientname);
        try {
            listener.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        int token = Integer.parseInt(this.responseHandler.getMessage());
        this.responseHandler = null;
        return  token;
    }


    /**
     * This method is how the client invoke a request for setting the intensity of the special dice in a particular tool card
     * @param clientname The username of the account performing the request
     * @param intensity The intensity we want to set to the dice in a particular tool card
     * @throws RemoteException Exception thrown in different situations: the provided username doesn't exist in the UsersList,
     * the action is not allowed in the current state, there is no dice set to which we can change the intensity
     */
    @Override
    public synchronized void setToolCardDiceIntensity(String clientname, int intensity) throws RemoteException {
        ClientMessage setToolCardDiceIntesityMessage = new SetToolCardDiceIntensityMessage();
        setToolCardDiceIntesityMessage.setClientName(clientname);
        setToolCardDiceIntesityMessage.setIntensity(intensity);

        try {
            listener.sendMessage(setToolCardDiceIntesityMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        this.responseHandler = null;
    }


    /**
     * This method is how the client invoke a request for receiving a list with all my opponents in my match
     * @param clientname The username of the account performing the request
     * @return A list with al the other players in the match
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized List getPlayersinmymatch(String clientname) throws RemoteException {
        ClientMessage getPlayerIsInMatchMessage = new GetPlayerIsInMatchMessage();
        getPlayerIsInMatchMessage.setClientName(clientname);

        try {
            listener.sendMessage(getPlayerIsInMatchMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        List list = this.responseHandler.getList();
        this.responseHandler = null;

        return list;
    }


    /**
     * This method is how the client invoke a request for receiving the maps of the other players
     * @param clientname The username of the account performing the request
     * @return A string with all the information about the maps of the other players
     * @throws RemoteException Exception thrown in different situations: the provided username doesn't exist in the UsersList,
     * the scheme card associated to the player was not found, the action is not allowed in the current state
     */
    @Override
    public synchronized List getSchemeCardsoftheotherPlayers(String clientname) throws RemoteException{
        ClientMessage message = new GetSchemeCardsoftheotherPlayersMessage();
        message.setClientName(clientname);

        try {
            listener.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        List list = this.responseHandler.getList();
        this.responseHandler = null;

        return list;
    }


    /**
     * This method is how the client invoke a request for getting the special dice extracted with the particular tool card
     * @param clientname The username of the account performing the request
     * @return A string with the information of the special dice for the particular tool card
     * @throws RemoteException Exception thrown in different situations: the provided username doesn't exist in the UsersList,
     * there is no special dice set at the moment
     */
    @Override
    public synchronized String getToolCardDice(String clientname) throws RemoteException {
        ClientMessage getToolCardDiceMessage = new GetToolCardDiceMessage();
        getToolCardDiceMessage.setClientName(clientname);

        try {
            listener.sendMessage(getToolCardDiceMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        String value = this.responseHandler.getMessage();
        this.responseHandler = null;

        return value;
    }


    /**
     * This method is how the client invoke a request for get a list containing my private goal card
     * @param clientname The username of the account performing the request
     * @return A list containing the private goal card
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized List getPrivateGoalCard(String clientname) throws RemoteException {
        ClientMessage getPrivateGoalCard = new GetPrivateGoalCardMessage();
        getPrivateGoalCard.setClientName(clientname);

        try {
            listener.sendMessage(getPrivateGoalCard);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        List list = this.responseHandler.getList();
        this.responseHandler = null;

        return list;
    }


    /**
     * This method is how the client invoke a request for receiving a list of all the public goals of the current match
     * @param clientname The username of the account performing the request
     * @return A list containing the public goal cards
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized List getPublicGoalCards(String clientname) throws RemoteException {
        ClientMessage getPublicGoalCards = new GetPublicGoalCardsMessage();
        getPublicGoalCards.setClientName(clientname);

        try {
            listener.sendMessage(getPublicGoalCards);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        List list = this.responseHandler.getList();
        this.responseHandler = null;

        return list;
    }


    /** This method is how the client invoke a request for getting a list of all the available matches waiting to start
     * @return A list with the active matches
     * @throws RemoteException
     */
    @Override
    public synchronized List getActiveMatchesList() throws RemoteException {
        ClientMessage getActiveMatchesListMessage = new GetActiveMatchesListMessage();
        try {
            listener.sendMessage(getActiveMatchesListMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        List<Match> list = this.responseHandler.getList();
        this.responseHandler = null;
        return list;
    }


    /**
     * This method is how the client invoke a request for getting how many points I have
     * @param clientname The username of the account performing the request
     * @return An integer value with how many points it has
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized int getmyPoints(String clientname) throws RemoteException {
        ClientMessage getMyPointsMessage = new GetMyPointsMessage();
        getMyPointsMessage.setClientName(clientname);

        try {
            listener.sendMessage(getMyPointsMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        String value = this.responseHandler.getMessage();
        this.responseHandler = null;

        return Integer.parseInt(value);
    }


    /**
     * This method is how the client invoke a request for getting the players in the match ordered by ranking
     * @param username The username of the account performing the request
     * @return A list with all the players in the match
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized List getRanking(String username) throws RemoteException {
        ClientMessage getRankingMessage = new GetRankingMessage();
        getRankingMessage.setClientName(username);

        try {
            listener.sendMessage(getRankingMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        List list = this.responseHandler.getList();
        this.responseHandler = null;

        return list;

    }


    /**
     * This method is how the client invoke a request for getting a list of the extracted scheme cards to choose
     * @param clientname The username of the account performing the request
     * @return A list containing the scheme cards randomly extracted for the match
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized List getExtractedSchemeCard(String clientname) throws RemoteException {
        ClientMessage message = new GetExtractedSchemeCardMessage();
        message.setClientName(clientname);

        try {
            listener.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        List list = this.responseHandler.getList();
        this.responseHandler = null;

        return list;
    }


    /**
     * This method is how the client invoke a request for getting my scheme card
     * @param clientname The username of the account performing the request
     * @return A list containing my scheme card
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized List getSchemeCard(String clientname) throws RemoteException {
        ClientMessage getSchemeCardMessage = new GetSchemeCardMessage();
        getSchemeCardMessage.setClientName(clientname);

        try {
            listener.sendMessage(getSchemeCardMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        List list = this.responseHandler.getList();
        this.responseHandler = null;

        return list;
    }


    /**
     * This method is how the client invoke a request for getting the dices contained in the round dice pool
     * @param clientname The username of the account performing the request
     * @return A string with the values of the dices in the Dicepool of the current round
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized String getRoundDicepool(String clientname) throws RemoteException {
        ClientMessage getRoundDicePoolMessage = new GetRoundDicePoolMessage();
        getRoundDicePoolMessage.setClientName(clientname);

        try {
            listener.sendMessage(getRoundDicePoolMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.responseHandler.check();
        String value = this.responseHandler.getMessage();
        this.responseHandler = null;

        return value;
    }


    /**
     * This method is how the client invoke a request for getting a representation of the round track in the match
     * @param clientname The username of the account performing the request
     * @return A string with the situation on the round track
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public synchronized String getRoundTrack(String clientname) throws RemoteException {
        ClientMessage getRoundTrackMessage = new GetRoundTrackMessage();
        getRoundTrackMessage.setClientName(clientname);

        try {
            listener.sendMessage(getRoundTrackMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            wait();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        this.responseHandler.check();
        String value = this.responseHandler.getMessage();
        this.responseHandler = null;
        return value;
    }
}
