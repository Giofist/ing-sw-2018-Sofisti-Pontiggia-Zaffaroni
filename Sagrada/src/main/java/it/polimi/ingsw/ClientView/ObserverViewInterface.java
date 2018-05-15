package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObserverViewInterface extends Remote {
    public void update() throws RemoteException;
    public void showErrorMessage(String message)throws RemoteException;
    public void showSchemeCards(SchemeCard scheme, SchemeCard card)throws RemoteException;
    public void notifyaDraw()throws RemoteException;
    public void notifyaLose()throws RemoteException;
    public void notifyaWin()throws RemoteException;

}
