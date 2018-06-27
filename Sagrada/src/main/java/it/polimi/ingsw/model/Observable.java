package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public interface Observable extends Serializable{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    State getState();
    void setState(State state);
    void notifyObservers()throws RemoteException;
    void checkAction(TurnActions action) throws NotAllowedActionException;
    List getActions();
}
