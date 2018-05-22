package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.FeedObserverView;
import it.polimi.ingsw.ClientView.ObserverViewInterface;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

// this is the controller
public class ClientHandler extends UnicastRemoteObject implements ClientHandlerInterface {

    //constructor
    public ClientHandler ()throws RemoteException {};

   //RMI part
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
    public void createGame(String clientname, ObserverViewInterface client, FeedObserverView Client, String gamename ) throws  RemoteException {
        try {
            //creo un player, lo associo ad uno user e viceversa;
            Player player = new Player();
            User user = UsersList.Singleton().getUser(clientname);
            user.setPlayer(player);
            player.setUser(user);

            //creo effettivamente la partita
            //NB: questa chiamata già aggiunge in player un riferimento alla partita a cui è iscritto
            MatchesList.singleton().createGame(player, gamename);

            //observer pattern, mi registro per seguire gli aggiornamenti relativi a me
            player.feedObserverViews(Client);
            player.observerViews(client);
            //gestione delle eccezioni
        } catch (HomonymyException e) {
            throw new RemoteException(e.getMsg());
        } catch (UserNotExistentException err) {
            throw new RemoteException(err.getMessage());
        }
    }

    @Override
    public boolean isMatchInList(String gamename) throws RemoteException {
       /* try {                                                             //to be implemented
            for (Match game : MatchesList.singleton().getgames()) {
                if (game.getName().equals(gamename)) {
                    return true;
                }
            }
        }catch (GameNotExistantException e){
            throw new RemoteException(e.getMessage());
        }
        return false;*/
       return true;
    }

    @Override
    public void joinaGame(String clientname, String gamename) throws RemoteException{
        try{
            Player player = new Player();
            User user = UsersList.Singleton().getUser(clientname);
            user.setPlayer(player);
            player.setUser(user);
            Match match = MatchesList.singleton().getGame(gamename);
            //NB questa chiamata già aggiunge un riferimento a questo match in player
            match.join(player);
        }catch (UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }catch (GameNotExistantException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void setSchemeCard(String clientusername, int cardid) throws RemoteException{
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
    public List getPublicGoalCarddescriptions(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getMatch().getGametable().getPublicGoalDescriptions();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }



    @Override
    public List getPublicGoalCardids(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getMatch().getGametable().getPublicGoalIDs();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List getPublicGoalCardnames(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getMatch().getGametable().getPublicGoalNames();
        }catch(UserNotExistentException e){
            throw new RemoteException(e.getMessage());
        }
    };


    @Override
    public String getActiveMatchList() throws RemoteException{
        String list = new String();
        for (Match match: MatchesList.singleton().getgames()) {
            if (!match.isStarted()){
                list += (match.getName());
                list += " ";
                for(Player player: match.getallPlayers()){
                    list+= player.getAssociatedUser().getName();
                }
                list += "\n";
            }
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
}
