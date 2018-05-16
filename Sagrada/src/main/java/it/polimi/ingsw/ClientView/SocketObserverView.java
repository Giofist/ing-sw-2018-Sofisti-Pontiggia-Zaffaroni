package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

// implemented by pon
public class SocketObserverView implements ClientHandlerInterface{
    private Socket socket;
    Scanner in;
    PrintWriter out;

    private ObserverViewInterface observerView;

    public SocketObserverView(Socket socket, ObserverViewInterface observerView) throws IOException{
        this.socket = socket;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        this.observerView = observerView;
    }

    //all methods to be implemented here
    @Override
    public String rmiTest(String stringa) throws RemoteException {

        return null;
    }

    @Override
    public void register(String username, String password) throws RemoteException {
        out.println(1 + username + password);
        switch(in.nextInt()){
            case 1: return;
        }

    }

    @Override
    public void login(String clientname, String password) throws RemoteException {

    }

    @Override
    public void createGame(String clientname, ObserverViewInterface client, FeedObserverView Client, String gamename) throws RemoteException {

    }

    @Override
    public boolean isMatchInList(String gamename) throws RemoteException {
        return false;
    }

    @Override
    public void joinaGame(String clientname, String gamename) throws RemoteException {

    }

    @Override
    public void setSchemeCard(String clientname, int twin, SchemeCard schemeCard) throws RemoteException {

    }

    @Override
    public String getPrivateGoalCarddescription(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getPrivateGoalCardname(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public int getPrivateGoalCardid(String clientname) throws RemoteException {
        return 0;
    }

    @Override
    public List getPublicGoalCarddescriptions(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public List getPublicGoalCardids(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public List getPublicGoalCardnames(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public List getActiveMatchList() throws RemoteException {
        return null;
    }

    @Override
    public int getmyPoints(String clientname) throws RemoteException {
        return 0;
    }

    @Override
    public List getRanking(String clientname) throws RemoteException {
        return null;
    }
}
