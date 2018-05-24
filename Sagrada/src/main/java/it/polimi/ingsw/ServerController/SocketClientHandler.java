package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientView.FeedObserverView;
import it.polimi.ingsw.ClientView.ObserverViewInterface;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Scanner;

public class SocketClientHandler implements Runnable, ObserverViewInterface, FeedObserverView {
    private Socket socket;
    private ClientHandler controller;
    Scanner in;
    PrintWriter out;


    public SocketClientHandler(Socket socket, ClientHandler controller) throws RemoteException, IOException {
        this.socket = socket;
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        this.controller = controller;
    }
    @Override
    public void run (){
        int i=0;
        while (i==0){
            String command = in.next();
            System.out.println("the command I've received: " +command);
            switch(command){
                case "register": try{
                    String username = in.next();
                    String password = in.next();
                    controller.register(username, password);
                    out.println(1);
                    out.flush();
                }catch(RemoteException e){
                    out.println(0 + " " +e.getMessage());
                    out.flush();
                }
                break;
                case "login": try{
                    String username = in.next();
                    String password = in.next();
                    controller.login(username, password);
                    out.println(1);
                    out.flush();
                }catch(RemoteException e){
                    out.println(0 + " " +e.getMessage());
                    out.flush();
                }
                break;
                case "createGame": try{
                    String username = in.next();
                    String gamename = in.next();
                    controller.createGame(username, this,this, gamename);
                    out.println(1);
                    out.flush();
                }catch(RemoteException e){
                    out.println(0 + " " +e.getMessage());
                    out.flush();
                }
                break;
                case "getActiveMatchList" : try{
                    String list =controller.getActiveMatchList();
                    out.println(1 + " "+ list);
                    out.flush();
                }catch(RemoteException e){
                    out.println(0 + " " +e.getMessage());
                    out.flush();
                }
                break;
                case "setSchemeCard" : try{
                    String username = in.next();
                    int cardid = in.nextInt();
                    controller.setSchemeCard(username,cardid);
                    out.println(1);
                    out.flush();
                }catch(RemoteException e){
                    out.println(0 + " " +e.getMessage());
                    out.flush();
                }
                break;
                case "joinaGame": try{
                    String username = in.next();
                    String gamename = in.next();
                    controller.joinaGame(username,this, this, gamename);
                    out.println(1);
                    out.flush();
                }catch(RemoteException e){
                    out.println(0 + " " +e.getMessage());
                    out.flush();
                }
            }
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showSchemeCards(String schemeCard1, String schemeCard2, String schemeCard3, String schemeCard4) throws RemoteException {

    }


    @Override
    public void notifyaDraw() {

    }

    @Override
    public void notifyaLose() {

    }

    @Override
    public void notifyaWin() {

    }

    @Override
    public void notifyGameisStarting(String gamename) throws RemoteException {

    }

    @Override
    public void testConnection(boolean value) throws RemoteException {
        out.println("testConnection");
        out.flush();
    }
}
