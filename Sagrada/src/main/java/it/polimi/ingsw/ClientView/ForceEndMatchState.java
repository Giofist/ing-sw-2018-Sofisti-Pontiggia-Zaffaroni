package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class ForceEndMatchState implements Runnable {

    private ObserverView view;
    private String yourName;
    public ForceEndMatchState(ObserverView view, String yourName) {
        this.view = view;
        this.yourName = yourName;
    }

    @Override
    public void run(){
        System.out.println("Tutti gli altri giocatori hanno lasciato la partita, hai vinto a tavolino");
        synchronized (this.view){
            this.view.notify();
        }
    }

}
