package it.polimi.ingsw.ServerController;

import java.rmi.Remote;
import java.rmi.RemoteException;

//l'interfaccia che sarà condivisa con il client
//viene caricata sul RMI register ecc...
//guardate le slide
public interface RmiServerInterface extends Remote {

        // All the methods exposed from the server to the client go here
        public String rmiTest(String stringa) throws RemoteException;

        public void register(String username, String password) throws RemoteException;

        public void login(String username, String password) throws RemoteException;

        public boolean checkIsReady();

        // public getActiveMatchList() throws RemoteException;
        // Per questo metodo va deciso come ritornare il risultato
}
