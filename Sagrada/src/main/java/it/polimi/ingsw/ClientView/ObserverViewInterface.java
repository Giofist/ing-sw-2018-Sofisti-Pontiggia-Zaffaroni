package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObserverViewInterface extends Remote{
    public void update() throws RemoteException;
    public void showErrorMessage(String message)throws RemoteException;
    public void showSchemeCards(String schemeCard1, String schemeCard2, String schemeCard3, String schemeCard4)throws RemoteException;
    public void notifyaDraw()throws RemoteException;
    public void notifyaLose()throws RemoteException;
    public void notifyaWin()throws RemoteException;
    public void notifyGameisStarting(String gamename) throws RemoteException;
    public void testConnection(boolean value) throws RemoteException;

}
