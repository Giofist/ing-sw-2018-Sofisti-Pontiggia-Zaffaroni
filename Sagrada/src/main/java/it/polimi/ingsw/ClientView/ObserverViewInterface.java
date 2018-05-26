package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObserverViewInterface extends Remote{
    public void update() throws RemoteException;
    public void showErrorMessage(String message)throws RemoteException;
        public void notifyendGame()throws RemoteException;
    public void notifyGameisStarting() throws RemoteException;

}
