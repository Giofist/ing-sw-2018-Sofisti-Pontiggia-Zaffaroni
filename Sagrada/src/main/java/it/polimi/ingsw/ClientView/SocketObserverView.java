package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import it.polimi.ingsw.model.Exceptions.UserNotExistentException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;
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
            //try{
                System.out.println("é partito il socket listening");
                String message = in.next();
                switch(message){
                    case "notifyGameisStarting": try{
                        System.out.println("ho ricevuto una notifica\n");
                        observerView.notifyGameisStarting();
                        out.println(1);
                    }catch (RemoteException e){
                        out.println(0+e.getMessage());
                    }
                        out.flush();
                        break;
                    case "update": try{
                        observerView.update();
                        out.println(1);
                    }catch (RemoteException e){
                        out.println(0+e.getMessage());
                    }
                        out.flush();
                        break;
                    case "showErrorMessage": try{
                        observerView.showErrorMessage(in.next());
                        out.println(1);
                    }catch (RemoteException e){
                        out.println(0+e.getMessage());
                    }
                        out.flush();
                        break;
                    default: System.out.println("niente di nuovo sul fronte occipitale");
                }
            //}catch(NoSuchElementException e){
                //do nothing
            //}

        }
    }
    public void waitforAnswerfromServer()throws RemoteException{
        switch (in.nextInt()) {
            case 1:
                System.out.println("sono stato notificato");
                return;
            case 0:
                throw new RemoteException(in.nextLine());
        }
    }
    //all methods to be implemented here
    @Override
    public synchronized String rmiTest(String stringa) throws RemoteException {

        return null;
    }

    @Override
    public synchronized void register(String username, String password) throws RemoteException {
        out.println("register " + username + " " + password);
        out.flush();
        this.waitforAnswerfromServer();
    }

    @Override
    public synchronized void login(String username, String password) throws RemoteException {
        out.println("login " + username + " " + password);
        out.flush();
        this.waitforAnswerfromServer();
    }

    @Override
    public synchronized void logout(String username) throws RemoteException {
        out.println("logout " + username);
        out.flush();
        this.waitforAnswerfromServer();
    }

    @Override
    public synchronized void createGame(String username, ObserverViewInterface client, FeedObserverView Client, String gamename) throws RemoteException {
        out.println("createGame " + username + " " + gamename);
        out.flush();
        this.waitforAnswerfromServer();
    }



    @Override
    public synchronized void joinaGame(String username, ObserverViewInterface client, FeedObserverView Client,String gamename) throws RemoteException {
        out.println("joinaGame " + username + " " + gamename);
        out.flush();
        this.waitforAnswerfromServer();

    }

    @Override
    public synchronized String getMymapString(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public synchronized void setSchemeCard(String username, int cardid) throws RemoteException {
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
    public void setDice(String clientname, int diceindex, int row, int column) throws RemoteException, UserNotExistentException, SchemeCardNotExistantException {

    }


    @Override
    public void notifyGame(String clientname) throws RemoteException {

    }

    @Override
    public synchronized String getPrivateGoalCarddescription(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public synchronized String getPrivateGoalCardname(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public synchronized int getPrivateGoalCardid(String clientname) throws RemoteException {
        return 0;
    }

    @Override
    public synchronized List getPublicGoalCarddescriptions(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public synchronized List getPublicGoalCardids(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public synchronized List getPublicGoalCardnames(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public synchronized String getActiveMatchList() throws RemoteException {
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
    public synchronized int getmyPoints(String clientname) throws RemoteException {
        out.println("getActiveMatchList ");
        out.flush();
        switch (in.nextInt()) {
            case 1:
                return in.nextInt();
            case 0:
                throw new RemoteException(in.nextLine());
        }
        throw  new RemoteException();
    }

    @Override
    public synchronized String getRanking(String username) throws RemoteException {
        return null;

    }

    @Override
    public String getSchemeCards(String clientname) throws RemoteException {
        return null;
    }

    @Override
    public String getRoundDicepool(String clientname) throws RemoteException {
        return null;
    }
}
