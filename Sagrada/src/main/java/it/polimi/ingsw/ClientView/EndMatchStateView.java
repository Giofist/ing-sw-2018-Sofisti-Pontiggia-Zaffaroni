package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Player;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;


/**
 *  This view for the CLI is displayed when the match is over. The final scoreboard will be displayed to the user
 */
public class EndMatchStateView implements Runnable {
    private ClientHandlerInterface serverController;
    private String yourName;
    private ObserverView view;


    /**
     * @param serverController The reference to the server's controller for invoking its methods
     * @param yourName The username of the account performing the requests
     * @param view The client's main view
     */
    public EndMatchStateView(ClientHandlerInterface serverController, String yourName, ObserverView view) {
        this.serverController = serverController;
        this.yourName = yourName;
        this.view = view;
    }


    /**
     * This is the method that will display the final results. The player will also be asked if he wants to leave the current
     * terminated match.
     */
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
                    serverController.leavethematch(yourName,view);
                }catch(RemoteException e){
                    e.printStackTrace();
                }
                synchronized (this.view){
                    this.view.notifyAll();
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
