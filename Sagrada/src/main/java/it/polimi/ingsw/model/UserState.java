package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class UserState implements Observable {
    private transient LinkedList<Observer> observers;
    private State state;

    public UserState(){
        this.state = State.INACTIVE;
        this.observers = new LinkedList<>();
    }
    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public void setState(State state){
        this.state = state;
    }

    public void notifyObservers() throws RemoteException{
        for (Observer observer: this.observers){
            observer.ping();
        }
    }

    @Override
    public void checkAction(TurnActions action) throws NotAllowedActionException {
        //never used
    }

    @Override
    public List getActions() {
        // never used
        return null;
    }

    @Override
    public Observer getObserver() {
        return this.observers.getFirst();
    }
}
