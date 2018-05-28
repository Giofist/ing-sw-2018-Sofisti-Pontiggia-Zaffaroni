package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.FeedObserverView;
import it.polimi.ingsw.ClientView.ObserverViewInterface;
import it.polimi.ingsw.model.Exceptions.PrivateGoalCardException;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.rmi.RemoteException;
import java.util.LinkedList;

public class Observable {
    //per il pattern observer
    protected LinkedList<ObserverViewInterface> observerViewInterfaces;
    protected LinkedList<FeedObserverView> feedObserverViews;
    //metodo per l'oberserver design pattern
    public void feedObserverViews(FeedObserverView client) {
        this.feedObserverViews.add(client);
    }
    public void observerViews(ObserverViewInterface client){
        this.observerViewInterfaces.add(client);
    }
    //tutti questi metodi chiamano qualcosa della view tramite gli observer pattern
    public void notifyError(String message)  throws RemoteException {
        for(ObserverViewInterface observerViewInterface : this.observerViewInterfaces){
            observerViewInterface.showErrorMessage(message);
        }
    }
    public void startGame()throws RemoteException{
        for(ObserverViewInterface observerViewInterface : this.observerViewInterfaces){
            observerViewInterface.notifyGameisStarting();
        }
    }
    public  void notifyEndMatch(){
        try{
            for(ObserverViewInterface observerViewInterface: this.observerViewInterfaces){
                observerViewInterface.notifyendGame();
            }
        }catch(RemoteException e){
            //do nothing
        }
    }

    public void notifyIsYourTurn() {
        try {
            for(ObserverViewInterface observerViewInterface : this.observerViewInterfaces){
                observerViewInterface.notifyIsYourTurn("E' il tuo turno!");
            }
        } catch(RemoteException e){

        }
    }
}
