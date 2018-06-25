package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.PlayerPackage.Player;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class EndMatchStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;
    private ObserverView view;

    public EndMatchStateView(ClientHandlerInterface serverController, String yourName, ObserverView view) {
        this.serverController = serverController;
        this.yourName = yourName;
        this.view = view;
    }


    @Override
    public void run() {
        try {
            System.out.println("La partita è finita: hai totalizzato" + serverController.getmyPoints(yourName) + "punti");
            System.out.println("Ecco la classifica finale: ");
            List<Player> list = serverController.getRanking(yourName);
            for (Player player: list){
                System.out.println(Client.translator.translatePlayer(player)+ " || "+ player.getPoints() + " punti");
            }
        } catch (RemoteException e) {
            System.out.println(Client.translator.translateException(e.getMessage()));
        }
        String input;
        boolean success = false;
        Scanner in = new Scanner(System.in);

        while (!success) {
            System.out.println("Vuoi lasciare la partita corrente? [S/N]");
            input = in.nextLine();
            if (input.equals("S") || input.equals("s")) {
                try{
                    serverController.leavethematchatthend(yourName);
                }catch(RemoteException e){
                    e.printStackTrace();
                }
                synchronized (this.view){
                    this.view.notify();
                }
                success = true;
            } else if (input.equals("N") || input.equals("n")) {
                try{
                    wait(10000);
                }catch(InterruptedException e){
                    //do nothing
                }
            } else System.out.println("Hai sbagliato a digitare!");
        }
    }

}
