package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

public class StartTurnView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;

    public StartTurnView(ClientHandlerInterface serverController, String yourName) {
        this.serverController = serverController;
        this.yourName = yourName;
    }

    @Override
    public void run() {
        //try{
            Printer.Singleton().printRoundTrack(serverController, yourName);
            Printer.Singleton().printRoundDicePool(serverController, yourName);
            Setter.Singleton().selectAction(serverController, yourName);
        //}catch(InterruptedException e){
            //do something
        //}

    }
}
