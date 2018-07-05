package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.rmi.RemoteException;

/**
 * This view for the CLI is displayed when all the players except you have left the match. This means that you automatically
 * won the match.
 */
public class ForceEndMatchState implements Runnable {

    private ObserverView view;
    private String yourName;
    private ClientHandlerInterface clientHandlerInterface;


    /**
     * @param view The client's main view
     * @param yourName The username of the account performing the requests
     */
    public ForceEndMatchState(ObserverView view, String yourName, ClientHandlerInterface clientHandlerInterface) {
        this.view = view;
        this.yourName = yourName;
        this.clientHandlerInterface = clientHandlerInterface;
    }

    /**
     * Method notifying the victory to the remaining player
     */
    @Override
    public void run(){
        System.out.println("Tutti gli altri giocatori hanno lasciato la partita, hai vinto a tavolino");
        try{
            clientHandlerInterface.leavethematch(yourName,view);
        }catch(RemoteException e){
            e.printStackTrace();
        }
        synchronized (this.view){
            this.view.notifyAll();
        }
    }

}
