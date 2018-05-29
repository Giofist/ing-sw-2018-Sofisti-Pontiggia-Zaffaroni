package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.UserNotExistentException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

// implemented by pon
public class SocketObserverView implements ClientHandlerInterface, Runnable {
    private Socket socket;
    Scanner in;
    PrintWriter out;
    private ObserverViewInterface observerView;

    public SocketObserverView (Socket socket, ObserverViewInterface observerView)  throws IOException {
        this.socket = socket;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        this.observerView = observerView;
    }

    @Override
    public void run(){
        int i=0;
        while(i==0){
            switch(in.next()){
                case "testConnection": //do nothing

            }
        }

    }
    public void waitforAnswerfromServer()throws RemoteException{
        switch (in.nextInt()) {
            case 1:
                return;
            case 0:
                throw new RemoteException(in.nextLine());
        }
    }
    //all methods to be implemented here
    @Override
    public String rmiTest(String stringa) throws RemoteException {

        return null;
    }

    @Override
    public void register(String username, String password) throws RemoteException {
        out.println("register " + username + " " + password);
        out.flush();
        this.waitforAnswerfromServer();

    }

    @Override
    public void login(String username, String password) throws RemoteException {
        out.println("login " + username + " " + password);
        out.flush();
        this.waitforAnswerfromServer();
    }

    @Override
    public void logout(String username) throws RemoteException {
        out.println("logout " + username);
        out.flush();
        this.waitforAnswerfromServer();
    }

    @Override
    public void createGame(String username, ObserverViewInterface client, FeedObserverView Client, String gamename) throws RemoteException {
        out.println("createGame " + username + " " + gamename);
        out.flush();
        this.waitforAnswerfromServer();
    }



    @Override
    public void joinaGame(String username, ObserverViewInterface client, FeedObserverView Client,String gamename) throws RemoteException {
        out.println("joinaGame " + username + " " + gamename);
        out.flush();
        this.waitforAnswerfromServer();

    }

    @Override
    public String getMymapString(String clientname) throws RemoteException, SchemeCardNotExistantException {
        return null;
    }

    @Override
    public void setSchemeCard(String username, int cardid) throws RemoteException {
        out.println("setSchemeCard " + username + " " + cardid);
        out.flush();
        switch (in.nextInt()) {
            case 1:
                return;
            case 0:
                throw new RemoteException(in.nextLine());
        }
    }

    @Override
    public void setDice(String clientname, int row, int column) throws RemoteException, UserNotExistentException, SchemeCardNotExistantException {

    }

    @Override
    public void notifyGame(String clientname) throws RemoteException {

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
    public String getActiveMatchList() throws RemoteException {
        out.println("getActiveMatchList ");
        out.flush();
        switch (in.nextInt()) {
            case 1:
                return in.nextLine();
            case 0:
                throw new RemoteException(in.nextLine());
        }
        throw  new RemoteException();
    }

    @Override
    public int getmyPoints(String clientname) throws RemoteException {
        return 0;
    }

    @Override
    public List getRanking(String username) throws RemoteException {
        return null;

    }

    @Override
    public String getSchemeCards(String clientname) throws RemoteException {
        return null;
    }
}
