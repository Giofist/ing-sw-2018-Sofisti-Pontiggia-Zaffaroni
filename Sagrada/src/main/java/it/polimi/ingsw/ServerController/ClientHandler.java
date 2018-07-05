package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.NetworkClient.SocketResponseHandler;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;
import it.polimi.ingsw.model.TurnActions;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * This class is the controller of our MVC application
 */
public class ClientHandler extends UnicastRemoteObject implements ClientHandlerInterface {


    /**
     * Empty constructor
     * @throws RemoteException
     */
    public ClientHandler ()throws RemoteException {};


    /**
     * This method calls the register one in UsersList when a new user wants to register a new account
     * @param username The new username for the account
     * @param password The new password associated to the new account
     * @throws RemoteException Exception thrown in case there is another account already registered with the same username
     */
    @Override
    public void register(String username, String password) throws RemoteException{
        // When the User wants to register a new account we first verify that there isn't another User with the same username
        // then we proceed to register and notify the new User
        try {
            UsersList.Singleton().register(username, password);
        } catch (HomonymyException e) {
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * This method calls the method responsible for the login in UsersList when a user wants to log into its account
     * @param username The username of the account
     * @param password The password associated to the account
     * @param observer The ObserverView object that represents the client
     * @throws RemoteException Exception thrown in 2 different scenarios: the user il already logged in from another computer, the username or password provided are incorrect
     */
    @Override
    public void login(String username, String password, Observer observer) throws RemoteException{
        try{
            UsersList.Singleton().check(username, password, observer);
        }catch(LoginException e){
            throw new RemoteException(e.getMessage());
        }catch (IsAlreadyActiveException e){
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * This method calls the logout one in UsersList to let the acount free and permitting other pcs to connect to it
     * with the correct credentials
     * @param username The username of the account from where we want to log out
     * @param observer The ObserverView object to be removed from the observers on the server (basically the client that wants to log out)
     */
    @Override
    public void logout(String username, Observer observer) {
        UsersList.Singleton().logOut(username, observer );
    }


    /**
     * With this method any user can create a new match
     * @param clientname The username of the account that wants to create a new game
     * @param client The ObserverView object that represents the client
     * @param gamename The name for the new match
     * @throws RemoteException Exception thrown in 2 possible situations: the name for the new match is already taken, we are not able to find the user that performed the request in the UsersList
     */
    @Override
    public void createGame(String clientname, Observer client, String gamename ) throws  RemoteException {
        try {
            // Here we create a new player and we associate it to the corresponding user.
            // The player is a type of object that is created in the context of a match
            Player player = new Player();
            User user = UsersList.Singleton().findUser(clientname);
            user.setPlayer(player);
            player.setName(clientname);
            player.getPlayerState().addObserver(client);
            // Here we create the match
            // This method will also set the new created match to the player creator of the match
            MatchesList.singleton().createMatch(player, gamename);
        } catch (HomonymyException e) {
            throw new RemoteException(e.getMsg());
        } catch (UserNotExistentException err) {
            throw new RemoteException(err.getMessage());
        }
    }


    /**
     * This method allows a user to join a match available in the list of matches waiting to start
     * @param clientname The username of the account
     * @param observerView The ObserverView that is performing the request
     * @param gamename The name of the match we want to join
     * @throws RemoteException Exception thrown in 2 possible situations: the match's name where we want to join is invalid, the username provided for the request is invalid
     */
    @Override
    public void joinaMatch(String clientname, Observer observerView, String gamename) throws RemoteException{
        try{
            Player player = new Player();
            User user = UsersList.Singleton().findUser(clientname);
            user.setPlayer(player);
            player.setName(clientname);
            player.getPlayerState().addObserver(observerView);
            MatchesList.singleton().join(player,gamename);
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (GameNotExistantException e){
            throw new RemoteException(e.getMessage());
        }catch (MatchStartedYetException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can receive a private goal card for the match
     * @param clientname The username of the account performing the request
     * @return A list containing the private goal card
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public List getPrivateGoalCard(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            List list = new LinkedList();
            list.add(player.getPrivateGoalCard());
            return list;
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }

    }


    /**
     * With this method the client can receive the public goal cards for the match
     * @param clientname The username of the account performing the request
     * @return A list containing the public goal cards
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public List getPublicGoalCards(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            return player.getMatch().getGametable().getPublicGoalCards();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can receive the list of available matches where it's possible to join
     * @return A list with the active matches
     */
    @Override
    public List getActiveMatchesList() {
        LinkedList list = new LinkedList();
        for (Match match: MatchesList.singleton().getActiveMatches()) {
           list.add(match);
        }
        return list;
    }


    /**
     * With this method the client can receive how many points it has
     * @param clientname The username of the account performing the request
     * @return An integer value with how many points it has
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public int getmyPoints(String clientname)throws RemoteException{
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            return player.getPoints();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can receive all the players in the match ordered by their ranking.
     * @param clientname The username of the account performing the request
     * @return A list with all the players in the match
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public List<Player> getRanking(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            List list = player.getMatch().getallPlayers();
            Collections.sort(list);
            Collections.reverse(list);
            return list;
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can receive the scheme cards to choose before the match starts
     * @param clientname The username of the account performing the request
     * @return A list containing the scheme cards randomly extracted for the match
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public  List getExtractedSchemeCard(String clientname) throws RemoteException {
        try {
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            List toreturn = new LinkedList<>();
            for (SchemeCard schemeCard: player.getExtractedSchemeCards()) {
                toreturn.add(schemeCard);
                toreturn.add(schemeCard.getTwinCard());
            }
            return toreturn;
        } catch (UserNotExistentException e) {
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can receive the scheme card which has chosen
     * @param clientname The username of the account performing the request
     * @return  A list containing my scheme card
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public   List getSchemeCard(String clientname)throws RemoteException{
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            List list = new LinkedList();
            list.add(player.getScheme());
            return list;
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch(SchemeCardNotExistantException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can receive the dices contained in the round dice pool
     * @param clientname The username of the account performing the request
     * @return A string with the values of the dices in the Dicepool of the current round
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
        @Override
    public  String getRoundDicepool(String clientname) throws RemoteException {
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            return player.getGametable().getRoundDicepool().toString();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can receive the current status of the round track in the match
     * @param clientname The username of the account performing the request
     * @return A string with the situation on the round track
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public String getRoundTrack(String clientname) throws RemoteException {
        try {
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            return player.getGametable().getRoundTrack().toString();
        } catch (UserNotExistentException e) {
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can set which scheme card he wants to use for the match
     * @param clientusername The username of the account performing the request
     * @param cardid The id associated to the chosen scheme card
     * @throws RemoteException Exception thrown in 3 different situations: the provided username is not in the UsersList, the provided id is invalid, the player is not in a state where he can set a tool card
     */
    @Override
    public  void setSchemeCard(String clientusername, int cardid) throws RemoteException {
        try {
            Player player = UsersList.Singleton().findUser(clientusername).getPlayer();
            player.getPlayerState().checkAction(TurnActions.SETSCHEMECARD);
            player.setScheme(cardid);
        } catch (UserNotExistentException e) {
            throw new RemoteException(e.getMessage());
        } catch (CardIdNotAllowedException e) {
            throw new RemoteException(e.getMessage());
        } catch (NotAllowedActionException e) {
           throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can specify where he wants to place the dice on the scheme card
     * @param clientusername The username of the account performing the request
     * @param diceindex The index of the dice in the dicepool he wants to put on the scheme card
     * @param row The row where we want to place the dice
     * @param column The column where we want to place the dice
     * @throws RemoteException Exception thrown for several reasons: the username is not in the UsersList, we are not able to retrieve the scheme card
     * from the player, the diceIndex is an invalid one, we are not allowed to perform the action in the current state,
     * we are not respecting a constrain of the specified position, the specified position is out from the scheme card
     */
    @Override
    public void setDice(String clientusername, int diceindex, int row, int column) throws RemoteException{
        try{
            Player player = UsersList.Singleton().findUser(clientusername).getPlayer();
            player.getPlayerState().checkAction(TurnActions.SETDICE);
            Dice dice = player.getGametable().getRoundDicepool().getDice(diceindex);
            player.getScheme().setDice(dice ,row ,column, false, false, false);
            player.getGametable().getRoundDicepool().removeDice(diceindex);
            if (player.getPlayerState().getState().equals(State.HASUSEDATOOLCARDACTIONSTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASSETADICESTATE);

        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (SchemeCardNotExistantException e){
            throw new RemoteException(e.getMessage());
        }catch(OutOfMatrixException e){

            throw new RemoteException(e.getMessage());
        }catch(TileConstrainException e){
            throw new RemoteException(e.getMessage());
        }catch (DicepoolIndexException e){
            throw new RemoteException(e.getMessage());
        }catch (NotAllowedActionException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can receive all the tool cards in the current match
     * @param clientname The username of the account performing the request
     * @return A list of the available tool cards
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public  List getToolCards(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            return player.getGametable().getToolCards();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can specify which tool card he wants to use
     * @param clientname The username of the account performing the request
     * @param toolRequestClass A class with all the necessary parameters for performing the action (see the documentation for each tool card to see which parameters are required)
     * @throws RemoteException Exception thrown in different situations: something was wrong in performing the tool card action, wrong state in which to perform the action, the username is not in the UsersList
     */
    @Override
    public  void useaToolCard(String clientname, ToolRequestClass toolRequestClass) throws RemoteException {
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.USEALLTOOLCARD);
            player.getGametable().useaToolCard(toolRequestClass,player);
            player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can see which are the available actions in its current state
     * @param clientname The username of the account performing the request
     * @return List of the possible actions
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public List getPossibleActions(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            return player.getPlayerState().getActions();

        }catch(UserNotExistentException e){
            throw  new RemoteException(e.getMessage());
        }
    }

    /**
     * With this method the client can notify the server that it's ready to pass its turn
     * @param clientname The username of the account performing the request
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server or when the action is not allowed in the current state
     */
    @Override
    public  void passTurn(String clientname) throws RemoteException {
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.PASSTURN);
            player.getTurn().countDown();
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (NotAllowedActionException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can leave the match he is in
     * @param clientname The username of the account performing the request
     * @param observer The ObserverView object that represents the client
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server or when the action is not allowed in the current state
     */
    @Override
    public void leavethematch(String clientname, Observer observer) throws RemoteException{
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.LEAVEMATCH);
            player.getMatch().leavethematch(player);
            UsersList.Singleton().findUser(clientname).removePlayer(observer);
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (NotAllowedActionException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can specify where to put the Dice after an extraction allowed with a particular tool card
     * @param clientname The username of the account performing the request
     * @param row The row where we want to put the dice
     * @param column The column where we want to put the dice
     * @throws RemoteException Exception thrown in different situations: the specified username is not in the UsersList,  the action performed is not allowed in the current state,
     * the new position specified is out of the scheme card, there was some problem in getting the scheme card, the dice to be placed doesn't exist
     */
    @Override
    public void setToolCardDice(String clientname, int row, int column) throws RemoteException {
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.SETTOOLCARDDICE);
            player.getScheme().setDice(player.getdiceforToolCardUse(), row,column, false, false, false);
            player.removediceforToolCardUse();
            player.setPlayerState(State.MUSTPASSTURNSTATE);

        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch(DiceNotExistantException e){
            throw new RemoteException(e.getMessage());
        }catch (SchemeCardNotExistantException e){
            throw new RemoteException(e.getMessage());
        }catch (NotAllowedActionException e){
            throw new RemoteException(e.getMessage());
        }catch (OutOfMatrixException e){
            throw new RemoteException(e.getMessage());
        }catch (TileConstrainException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can be notified about how many Segnalini Favore it has
     * @param clientname The username of the account performing the request
     * @return The number of Segnalini Favore
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public int getToken(String clientname) throws RemoteException {
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            return player.getToken();
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can set the intensity he wants to a particular dice in a specific tool card
     * @param clientname The username of the account performing the request
     * @param intensity The intensity we want to set to the dice in a particular tool card
     * @throws RemoteException Exception thrown in different situations: the provided username doesn't exist in the UsersList,
     * the action is not allowed in the current state, there is no dice set to which we can change the intensity
     */
    @Override
    public void setToolCardDiceIntensity(String clientname, int intensity) throws RemoteException {
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.SETTOOLCARDDICEINTENSITY);
            player.getdiceforToolCardUse().setIntensity(intensity);
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch(NotAllowedActionException e){
            throw new RemoteException(e.getMessage());
        }catch(DiceNotExistantException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can receive a list with all the opponents in the current match
     * @param clientname The username of the account performing the request
     * @return A list with al the other players in the match
     * @throws RemoteException Exception thrown when the provided username doesn't exist in the UsersList on the server
     */
    @Override
    public List getPlayersinmymatch(String clientname) throws RemoteException {
        List<Player> list;
        try{
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            list = player.getMatch().getallPlayersbutnotme(player);

        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
        return list;
    }

    /**
     * With this method the client can see the maps of the other players in the same match
     * @param clientname The username of the account performing the request
     * @return A string with all the information about the maps of the other players
     * @throws RemoteException Exception thrown in different situations: the provided username doesn't exist in the UsersList,
     * the scheme card associated to the player was not found, the action is not allowed in the current state
     */
    @Override
    public List<SchemeCard> getSchemeCardsoftheotherPlayers(String clientname) throws RemoteException {
        try{
            LinkedList<SchemeCard> listtoreturn = new LinkedList();
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.GETMAPS);
            for (Player other_player : player.getMatch().getallPlayersbutnotme(player)){
               listtoreturn.addLast(other_player.getScheme());
            }
            return listtoreturn;
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (SchemeCardNotExistantException e){
            throw new RemoteException(e.getMessage());
        }catch (NotAllowedActionException e){
            throw new RemoteException(e.getMessage());
        }
    }


    /**
     * With this method the client can retrieve information about the special dice
     * @param clientname The username of the account performing the request
     * @return A string with the information of the special dice for the particular tool card
     * @throws RemoteException Exception thrown in different situations: the provided username doesn't exist in the UsersList,
     * there is no special dice set at the moment
     */
    @Override
    public String getToolCardDice(String clientname)throws RemoteException {
        try {
            Player player = UsersList.Singleton().findUser(clientname).getPlayer();
            return player.getdiceforToolCardUse().toString();
        } catch (UserNotExistentException e) {
            throw new RemoteException(e.getMessage());
        } catch (DiceNotExistantException e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
