package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface Observable extends Serializable{
    void addObserver(Observer observer);
    void notifyObservers()throws RemoteException;
    State getState();
}
