package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class StartTurnView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;
    private ObserverView view;

    public StartTurnView(ClientHandlerInterface serverController, String yourName, ObserverView view) {
        this.serverController = serverController;
        this.yourName = yourName;
        this.view =view;
    }

    @Override
    public void run() {
        //try{
            Printer.Singleton().printRoundTrack(serverController, yourName);
            Printer.Singleton().printGoalCards(serverController, yourName);
            Printer.Singleton().printRoundDicePool(serverController, yourName);
            Setter.Singleton().selectAction(serverController, yourName, view);
        //}catch(InterruptedException e){
            //do something
        //}

    }
}
