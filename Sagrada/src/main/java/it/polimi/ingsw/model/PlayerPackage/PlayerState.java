package it.polimi.ingsw.model.PlayerPackage;

import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Exceptions.NoActionAllowedException;
import it.polimi.ingsw.model.Exceptions.NotAllowedActionException;
import it.polimi.ingsw.model.Observable;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class PlayerState implements Observable, Serializable{
    private LinkedList<TurnActions> actions;
    private transient LinkedList<Observer> observers;
    private State state;


    public PlayerState(){
        this.actions = new LinkedList<>();
        this.observers = new LinkedList<>();
    }

    public void checkAction(TurnActions action) throws NotAllowedActionException {
        for (TurnActions allowedactions: this.actions){
            if (allowedactions.equals(action))
                return;
        }
        throw new NotAllowedActionException();
    }


    public List getActions() {
        return actions;
    }

    public void updateState(State state) {
        this.state = state;
        this.actions = new LinkedList<>();
        switch (state){
            case ENDMATCHSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCHATTHEEND);
                break;
            case ERRORSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCHATTHEEND);
                break;
            case HASSETADICESTATE:
                this.actions.addLast(TurnActions.PASSTURN);
                this.actions.addLast(TurnActions.USEALLTOOLCARD);
                this.actions.addLast(TurnActions.GETMAPS);
                break;
            case HASUSEDATOOLCARDACTIONSTATE:
                this.actions.addLast(TurnActions.PASSTURN);
                this.actions.addLast(TurnActions.SETDICE);
                break;
            case MATCHNOTSTARTEDYETSTATE:
                this.actions.addLast(TurnActions.LEAVEMATCHBEFORESTARTING);
                break;
            case MUSTPASSTURNSTATE:
                this.actions.addLast(TurnActions.PASSTURN);
                break;
            case MUSTSSETDILUENTEPERPASTASALDASTATE:
                this.actions.addLast(TurnActions.SETTOOLCARDDICE);
                this.actions.addLast(TurnActions.SETTOOLCARDDICEINTENSITY);
                break;
            case MUSTSETPENNELLOPERPASTASALDASTATE:
                this.actions.addLast(TurnActions.SETTOOLCARDDICE);
                break;
            case NOTYOURTURNSTATE:
                this.actions.addLast(TurnActions.GETMAPS);
                this.actions.addLast(TurnActions.PASSTURN);
                break;
            case STARTTURNSTATE:
                this.actions.addLast(TurnActions.PASSTURN);
                this.actions.addLast(TurnActions.GETMAPS);
                this.actions.addLast(TurnActions.SETDICE);
                this.actions.addLast(TurnActions.USEALLTOOLCARD);
                break;
            case MUSTSETSCHEMECARD:
                this.actions.addLast(TurnActions.SETSCHEMECARD);
                break;
        }
    }
    public void addObserver(Observer observer){
        this.observers.addLast(observer);
    }
    public void notifyObservers()throws RemoteException {
        for (Observer observer: this.observers){
            observer.update(this, null);
        }
    }
    public State getState() {
        return state;
    }

}
