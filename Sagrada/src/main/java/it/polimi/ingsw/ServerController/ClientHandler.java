package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.*;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;
import it.polimi.ingsw.model.PlayerPackage.TurnActions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// this is the controller
public class ClientHandler extends UnicastRemoteObject implements ClientHandlerInterface {

    //constructor
    public ClientHandler ()throws RemoteException {};

    @Override
    public String rmiTest(String stringa) throws RemoteException {
        return "Test " + stringa;
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
    public synchronized void login(String username, String password) throws RemoteException{
        try{
            UsersList.Singleton().check(username, password);
        }catch(LoginException e){
            throw new RemoteException(e.getMessage());
        }catch (IsAlreadyActiveException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized void logout(String username) throws RemoteException{
            UsersList.Singleton().logOut(username);
    }


    @Override
    public void createGame(String clientname, Observer client, String gamename ) throws  RemoteException {
        try {
            //creo un player, lo associo ad uno user e viceversa;
            Player player = new Player();
            User user = UsersList.Singleton().getUser(clientname);
            System.out.println("fin qui tutto bene");

            user.setPlayer(player);
            player.setUser(user);

            //creo effettivamente la partita
            //NB: questa chiamata già aggiunge in player un riferimento alla partita a cui è iscritto
            MatchesList.singleton().createGame(player, gamename);
            //observer pattern, mi registro per seguire gli aggiornamenti relativi a me
            player.addObserver(client);
            //gestione delle eccezioni
        } catch (HomonymyException e) {
            throw new RemoteException(e.getMsg());
        } catch (UserNotExistentException err) {
            throw new RemoteException(err.getMessage());
        }
    }



    @Override
    public void joinaGame(String clientname, Observer observerView, String gamename) throws RemoteException{
        try{
            Player player = new Player();
            User user = UsersList.Singleton().getUser(clientname);
            user.setPlayer(player);
            player.setUser(user);

            //observerView pattern, mi registro per seguire gli aggiornamenti relativi a me
            player.addObserver(observerView);
            player.addObserver(observerView);

            MatchesList.singleton().join(player,gamename);
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (GameNotExistantException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public String getPrivateGoalCarddescription(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getPrivateGoalCard().getDescription();
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }

    }

    @Override
    public String getPrivateGoalCardname(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getPrivateGoalCard().getName();
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }

    }


    @Override
    public int getPrivateGoalCardid(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getPrivateGoalCard().getID();
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }

    }
    @Override
    public String getPublicGoalCarddescriptions(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getMatch().getGametable().getPublicGoalDescriptions();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }



    @Override
    public String getPublicGoalCardids(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getMatch().getGametable().getPublicGoalIDs();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public String getPublicGoalCardnames(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getMatch().getGametable().getPublicGoalNames();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }


    @Override
    public String getActiveMatchesList() throws RemoteException{
        String list = new String();
        int i;
        for (Match match: MatchesList.singleton().getActiveMatches()) {
            i=4;
            list+= "-";
            list += (match.getName());
            list += "       I giocatori iscritti a questa partita sono: ";
            for(Player player: match.getallPlayers()){
                list+= player.getAssociatedUser().getName();
                list += " ";
                i--;
            }
            list += "e ne mancano ancora ";
            list += i;
            list += ".\n";
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
    public String getRanking(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getMatch().getfinalRanking();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized String getSchemeCards(String clientname) throws RemoteException {
        try {
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            String stringToreturn = "\n";
            int index = 1;
            for (SchemeCard schemeCard: player.getExtractedSchemeCards()) {
                stringToreturn += schemeCard.toString();
                stringToreturn += "'";
                stringToreturn += schemeCard.getTwinCard().toString();
                if (index < player.getExtractedSchemeCards().size()){
                  stringToreturn += "'";
                }
                index++;
            }
            return stringToreturn;
        } catch (UserNotExistentException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized  String getSchemeCard(String clientname)throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getScheme().toString();
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
    public synchronized void setSchemeCard(String clientusername, int cardid) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientusername).getPlayer();
            player.setScheme(cardid);
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (CardIdNotAllowedException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized void setDice(String clientusername, int diceindex, int row, int column) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientusername).getPlayer();
            player.getPlayerState().checkAction(TurnActions.SETDICE);
            Dice dice = player.getGametable().getRoundDicepool().getDice(diceindex);
            player.getGametable().getRoundDicepool().removeDice(diceindex);
            player.getScheme().setDice(dice ,row ,column, false, false, false);
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (SchemeCardNotExistantException e){
            throw new RemoteException(e.getMessage());
        }catch(OutOfMatrixException e){
            throw new RemoteException(e.getMessage());
        }catch(TileConstrainException e){
            throw new RemoteException(e.getMessage());
        }catch (EmpyDicepoolException e){
            throw new RemoteException(e.getMessage());
        }catch (NotAllowedActionException e){
            throw new RemoteException(e.getMessage());
        }

    }

    @Override
    public synchronized String getToolCardsIDs(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getGametable().getToolCardsIDs();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized String getToolCardsDescriptions(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getGametable().getToolCardsDescriptions();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized String getToolCardsNames(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getGametable().getToolCardsTitles();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized String getToolCardsCosts(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getGametable().getToolCardsCosts();
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
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (Exception e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized String getPossibleActions(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getPlayerState().getActions();

        }catch(UserNotExistentException e){
            throw  new RemoteException(e.getMessage());
        }catch (NoActionAllowedException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public synchronized void passTurn(String clientname) throws RemoteException {
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.PASSTURN);
            synchronized (player.getTurn()){
                player.getTurn().notify();
            }
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (NotAllowedActionException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void leavethematch(String clientname) throws RemoteException {

    }

    @Override
    public void setToolCardDice(String clientname, int row, int column) throws RemoteException {
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            player.getPlayerState().checkAction(TurnActions.SETTOOLCARDDICE);
            player.getScheme().setDice(player.getdiceforToolCardUse(), row,column, false, false, false);
            player.removediceforToolCardUse();

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
}
