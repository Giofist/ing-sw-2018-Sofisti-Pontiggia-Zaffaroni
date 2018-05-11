package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.UsersList;

import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//all'inizio avevo pensato di mettere il client server per rmi in una classe a parte
//poi ho pensato: ma devo proprio? beh per ora mi sembra di no
//l'unica cosa è l'esposizione del metodo run, che beh nel caso dirmi non deve essere invocato, perchè serve invece per gestire le connessioni socket
// il fatto che implementi runnable e quindi sia codice per thread non mi pare crei problemi
//se avete notizie avvisatemi
//client handler
public class ClientHandler extends UnicastRemoteObject implements Runnable, RmiServerInterface {

    private Socket socket;
    private UsersList usersList = UsersList.Singleton();

    public ClientHandler ()throws RemoteException {};
    public ClientHandler(Socket socket) throws RemoteException{
        this.socket = socket;
    }

    @Override
    public void run(){

        //questo per gestire i socket
        //qui metteremo lo switchone
    }


    @Override
    public String rmiTest(String stringa) {
        return "Test " + stringa;
    }

    // Here we'll put the implementation of all the methods in the interface RmiServerInterface

    // Implementing the register method
    @Override
    synchronized public  void register(String username, String password) throws RemoteException{
        // When the User wants to register a new account we first verify that there isn't another User with the same username
        try {
            usersList.checkHomonymy(username);
        } catch (HomonymyException e) {
            throw new RemoteException("Username already in use");
        }

        // Then we proceed to register and notify the new User
        usersList.register(username, password);
    }

    @Override
    synchronized public boolean checkIsReady(){

    }

    // Implementing the login method
    @Override
    synchronized public void login(String username, String password) throws RemoteException{
         if(usersList.check(username, password) == false){
             throw new RemoteException("Invalid username or password");
         }
    }


    // Implementing the getActiveMatchList
}
