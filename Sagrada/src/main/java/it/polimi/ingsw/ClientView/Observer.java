package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.model.Observable;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {
    void update(Observable o, Object arg) throws RemoteException;
    void ping () throws RemoteException;
}
