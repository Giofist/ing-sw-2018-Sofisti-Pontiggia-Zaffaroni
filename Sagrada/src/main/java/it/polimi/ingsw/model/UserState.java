package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class UserState implements Observable {
    private transient LinkedList<Observer> observers;
    private State state;

    /**
     * By default the constructor sets the UserState to inactive
     */
    public UserState(){
        this.state = State.INACTIVE;
        this.observers = new LinkedList<>();
    }

    /**
     * @param observer The observer of the user that wants to subscribe to its updates
     */
    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }


    /**
     * @param observer The observer of the user that no longer wants to receive its updates
     */
    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }


    /**
     * @return The state in which the user is currently set
     */
    @Override
    public State getState() {
        return this.state;
    }


    /**
     * @param state The new state to which we want to set the user
     */
    @Override
    public void setState(State state){
        this.state = state;
    }


    /**
     * Each Observer subscribed for receving updates gets notified
     * @throws RemoteException Exception in case we fail to notify one of the observers
     */
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
