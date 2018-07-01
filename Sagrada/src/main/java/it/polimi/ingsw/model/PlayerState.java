package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.ClientView.ObserverView;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class PlayerState implements Observable, Serializable{
    private LinkedList<TurnActions> actions;
    private transient LinkedList<Observer> observers;
    private State state;


    /**
     * Class that implements the Observable interface to keep track of the player states, its observers and its actions available in each state
     */
    public PlayerState(){
        this.actions = new LinkedList<>();
        this.observers = new LinkedList<>();
    }


    /**
     * Method to verify if an action is allowed in the current state
     * @param action Action to check
     * @throws NotAllowedActionException Exception thrown when the action is not allowed in the current state
     */
    public void checkAction(TurnActions action) throws NotAllowedActionException {
        for (TurnActions allowedactions: this.actions){
            if (allowedactions.equals(action))
                return;
        }
        throw new NotAllowedActionException();
    }


    /**
     * @return A list with the actions available in the current state
     */
    public List getActions() {
        return actions;
    }

    @Override
    public Observer getObserver() {
       return this.observers.getFirst();
    }

    @Override
    public void setState(State state) {
        this.state = state;
        this.actions = new LinkedList<>();
        switch (state){
            case ENDMATCHSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                break;
            case ERRORSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                break;
            case HASSETADICESTATE:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                this.actions.addLast(TurnActions.PASSTURN);
                this.actions.addLast(TurnActions.USEALLTOOLCARD);
                this.actions.addLast(TurnActions.GETMAPS);
                break;
            case HASUSEDATOOLCARDACTIONSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                this.actions.addLast(TurnActions.PASSTURN);
                this.actions.addLast(TurnActions.SETDICE);
                break;
            case MATCHNOTSTARTEDYETSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                break;
            case MUSTPASSTURNSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                this.actions.addLast(TurnActions.PASSTURN);
                break;
            case MUSTSSETDILUENTEPERPASTASALDASTATE:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                this.actions.addLast(TurnActions.SETTOOLCARDDICE);
                this.actions.addLast(TurnActions.SETTOOLCARDDICEINTENSITY);
                break;
            case MUSTSETPENNELLOPERPASTASALDASTATE:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                this.actions.addLast(TurnActions.SETTOOLCARDDICE);
                break;
            case NOTYOURTURNSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                this.actions.addLast(TurnActions.GETMAPS);
                this.actions.addLast(TurnActions.PASSTURN);
                break;
            case STARTTURNSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                this.actions.addLast(TurnActions.PASSTURN);
                this.actions.addLast(TurnActions.GETMAPS);
                this.actions.addLast(TurnActions.SETDICE);
                this.actions.addLast(TurnActions.USEALLTOOLCARD);
                break;
            case MUSTSETSCHEMECARD:
                this.actions.addLast(TurnActions.LEAVEMATCH);
                this.actions.addLast(TurnActions.SETSCHEMECARD);
                break;
        }
    }


    /**
     * @param observer Observer that want to be notified about the particular status in which the player is in
     */
    public void addObserver(Observer observer){
        this.observers.addLast(observer);
    }


    /**
     * @param observer Observer that no longer wants to be notified about the particular status in which the player is in
     */
    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }


    /**
     * Notify each observer subscribed to receive updates about any change in state
     * @throws RemoteException Exception thrown in case there is an error in updating the observers
     */
    public void notifyObservers()throws RemoteException {
        for (Observer observer: this.observers){
            observer.update(this, null);
        }
    }


    /**
     * @return The state in which the player is in
     */
    public State getState() {
        return state;
    }

}
