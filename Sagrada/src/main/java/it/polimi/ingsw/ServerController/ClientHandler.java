package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.NetworkClient.SocketResponseHandler;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.PlayerPackage.TurnActions;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

// this is the controller
public class ClientHandler extends UnicastRemoteObject implements ClientHandlerInterface {

    //constructor
    public ClientHandler ()throws RemoteException {}


    @Override
    public void setResponseHandler(SocketResponseHandler responseHandler) throws RemoteException {
        //do nothing, questo metodo si usa solo per i socket
    }

    @Override
    public synchronized void register(String username, String password) throws RemoteException{
        // When the User wants to register a new account we first verify that there isn't another User with the same username
        try {
            UsersList.Singleton().checkHomonymy(username);
        } catch (HomonymyException e) {
            throw new RemoteException(e.getMessage());
        }

        // Then we proceed to register and notify the new User
        UsersList.Singleton().register(username, password);
    }


    // Implementing the login method
    @Override
    public synchronized void login(String username, String password, Observer observer) throws RemoteException{
        try{
            UsersList.Singleton().check(username, password, observer);
        }catch(LoginException e){
            throw new RemoteException(e.getMessage());
        }catch (IsAlreadyActiveException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized void logout(String username) {
            UsersList.Singleton().logOut(username);
    }




    @Override
    public void createGame(String clientname, Observer client, String gamename ) throws  RemoteException {
        try {
            //creo un player, lo associo ad uno user e viceversa;
            Player player = new Player();
            User user = UsersList.Singleton().getUser(clientname);
            user.setPlayer(player);
            player.setName(clientname);
            player.getPlayerState().addObserver(client);
            //creo effettivamente la partita
            //NB: questa chiamata già aggiunge in player un riferimento alla partita a cui è iscritto
            MatchesList.singleton().createMatch(player, gamename);
            //gestione delle eccezioni
        } catch (HomonymyException e) {
            throw new RemoteException(e.getMsg());
        } catch (UserNotExistentException err) {
            throw new RemoteException(err.getMessage());
        }
    }



    @Override
    public void joinaMatch(String clientname, Observer observerView, String gamename) throws RemoteException{
        try{
            Player player = new Player();
            User user = UsersList.Singleton().getUser(clientname);
            user.setPlayer(player);
            player.setName(clientname);
            player.getPlayerState().addObserver(observerView);

            MatchesList.singleton().join(player,gamename);
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (GameNotExistantException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List getPrivateGoalCard(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            List list = new LinkedList();
            list.add(player.getPrivateGoalCard());
            return list;
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }

    }

    @Override
    public List getPublicGoalCards(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getMatch().getGametable().getPublicGoalCards();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List getActiveMatchesList() {
        LinkedList list = new LinkedList();
        for (Match match: MatchesList.singleton().getActiveMatches()) {
           list.add(match);
        }
        return list;
    }

    @Override
    public int getmyPoints(String clientname)throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getPoints();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List getRanking(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getMatch().getallPlayers();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized List getExtractedSchemeCard(String clientname) throws RemoteException {
        try {
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
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

    @Override
    public synchronized  List getSchemeCard(String clientname)throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            List list = new LinkedList();
            list.add(player.getScheme());
            return list;
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch(SchemeCardNotExistantException e){
            throw new RemoteException(e.getMessage());
        }
    }

        @Override
    public synchronized String getRoundDicepool(String clientname) throws RemoteException {
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getGametable().getRoundDicepool().toString();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public String getRoundTrack(String clientname) throws RemoteException {
        try {
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getGametable().getRoundTrack().toString();
        } catch (UserNotExistentException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized void setSchemeCard(String clientusername, int cardid) throws RemoteException {
        try {
            Player player = UsersList.Singleton().getUser(clientusername).getPlayer();
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

    @Override
    public synchronized void setDice(String clientusername, int diceindex, int row, int column) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientusername).getPlayer();
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


    @Override
    public synchronized List getToolCards(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getGametable().getToolCards();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }


    @Override
    public synchronized void useaToolCard(String clientname, ToolRequestClass toolRequestClass) throws RemoteException {
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.USEALLTOOLCARD);
            player.getGametable().useaToolCard(toolRequestClass,player);
            player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized List getPossibleActions(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getPlayerState().getActions();

        }catch(UserNotExistentException e){
            throw  new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized void passTurn(String clientname) throws RemoteException {
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.PASSTURN);
            player.getTurn().countDown();
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (NotAllowedActionException e){
            throw new RemoteException(e.getMessage());
        }
    }


    @Override
    public void leavethematch(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.LEAVEMATCHBEFORESTARTING);
            player.getMatch().leavethematch(player);
            UsersList.Singleton().getUser(clientname).removePlayer();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (NotAllowedActionException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void setToolCardDice(String clientname, int row, int column) throws RemoteException {
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
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

    @Override
    public int getToken(String clientname) throws RemoteException {
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getToken();
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void setToolCardDiceIntensity(String clientname, int intensity) throws RemoteException {
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
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

    @Override
    public List getPlayersinmymatch(String clientname) throws RemoteException {
        List list = new LinkedList();
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            for (Player other_player : player.getMatch().getallPlayersbutnotme(player)){
                list.add(other_player);
            }
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
        return list;
    }

    @Override
    public List getSchemeCardsoftheotherPlayers(String clientname) throws RemoteException {
        try{
            List<SchemeCard> listtoreturn = new LinkedList();
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.GETMAPS);
            for (Player other_player : player.getMatch().getallPlayersbutnotme(player)){
               listtoreturn.add(other_player.getScheme());
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

    @Override
    public String getToolCardDice(String clientname)throws RemoteException {
        try {
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getdiceforToolCardUse().toString();
        } catch (UserNotExistentException e) {
            throw new RemoteException(e.getMessage());
        } catch (DiceNotExistantException e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
