package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class Observable implements Serializable{
    protected LinkedList<Observer> observers;
    public void addObserver(Observer observer){
        this.observers.addLast(observer);
    }
    public void notifyObservers(){
        for (Observer observer: this.observers){
            try {
                observer.update(null, null);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
