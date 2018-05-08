package it.polimi.ingsw.ServerController;

import java.rmi.Remote;
import java.rmi.RemoteException;

//l'interfaccia che sarà condivisa con il client
//viene caricata sul RMI register ecc...
//guardate le slide
public interface RMIRemoteServerClientHandler extends Remote {

    public String forzaChievo(String stringa) throws RemoteException;
    //here all the methods I want to invoke from the client Controller


}
