package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface Observable extends Serializable{
    public void addObserver(Observer observer);
    public void notifyObservers()throws RemoteException;
    public State getState();
}
