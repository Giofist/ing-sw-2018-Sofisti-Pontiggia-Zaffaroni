package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Observable;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

//implemented by pon
public class ObserverView extends UnicastRemoteObject implements Observer {
    private ClientHandlerInterface serverController;
    private final Scanner in;
    private final PrintWriter out;
    private String yourName;
    private Thread thread;
    private boolean leaveSagrada;
    private boolean leaveMatch;
    private  boolean leave;

    //constructor1
    public ObserverView() throws RemoteException {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
        leaveSagrada = false;
        leaveMatch = false;
        leave =false;
    }

    //constructor2
    public ObserverView(ClientHandlerInterface controller) throws RemoteException {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
        this.serverController = controller;

    }

    public void setServerController(ClientHandlerInterface serverController) {
        this.serverController = serverController;
    }

    public void setLeaveSagrada(boolean value){
        this.leaveSagrada = value;
    }

    public synchronized void run() throws RemoteException {
        loadingInterface();
        while (!leaveSagrada){
            leaveMatch = false;
            menuInt();
            while (!leaveMatch){
                try {
                    wait();
                    this.thread.start();
                    if(leave){
                        leaveMatch= true;
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadingInterface() {
        System.out.println("Benvenuto in...\n");
        System.out.print("\n" +
                " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌\n" +
                "▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌\n" +
                "▐░▌          ▐░▌       ▐░▌▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░▌       ▐░▌\n" +
                "▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░▌ ▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄█░▌\n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌▐░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░▌ ▀▀▀▀▀▀█░▌▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀█░▌\n" +
                "          ▐░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░▌     ▐░▌  ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌       ▐░▌\n" +
                " ▄▄▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄█░▌▐░▌      ▐░▌ ▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌\n" +
                "▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░▌ ▐░▌       ▐░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀   ▀         ▀ \n" +
                "                                                                                           \n");

        boolean successo = false;
        String input;
        while (!successo) {
            System.out.println("Hai già un account? [S/N]");
            input = in.nextLine();
            if (input.equals("S") || input.equals("s")) {
                logInInt();
                successo = true;
            }
            else if (input.equals("N") || input.equals("n")){
                signInInt();
                successo = true;
            }
            else
                System.out.println("Hai sbagliato a digitare.");
        }
    }

    private void signInInt(){
        String username;
        String password;
        boolean successo = false;
        boolean remoteException = false;
        while (!successo) {
            remoteException=false;
            System.out.println("Inserisci un nuovo Username:");
            username = in.nextLine();
            this.yourName = username;
            System.out.println("Inserisci una password:");
            password = in.nextLine();
            try {
                serverController.register(username, password);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                remoteException = true;
            }
            if (remoteException == false){
                System.out.println("Esegui il LogIn con l'account appena creato!");
                logInInt();
                successo = true;
            }
        }
    }

    private void logInInt(){
        Scanner in = new Scanner(System.in);
        String username;
        String password;
        boolean success;
        do {
            success = true;
            System.out.println("Inserisci username:");
            username = in.nextLine();
            System.out.println("Inserisci password:");
            password = in.nextLine();
            System.out.println("Sei hai una partita in corso verrai subito reindirizzato a quella.");
            try {
                serverController.login(username, password, this);
                System.out.println();
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                success = false;
            }
            this.yourName = username;
        } while (!success);
    }


    private void menuInt() throws RemoteException {
        Scanner in = new Scanner(System.in);
        String input;
        boolean success = false;
        do{
            System.out.println("Benvenuto nel menù principale di Sagrada!");
            System.out.println("- Multi Giocatore (M)");
            System.out.println("- Giocatore Singolo (Coming soon!)");
            System.out.println("- LogOut (L)");
            input = in.nextLine();

            if (input.equals("M") || input.equals("m")) {
                multiInt();
                success = true;
            }
            else if (input.equals("L") || input.equals("l")) {
                serverController.logout(this.yourName);
                success = true;
            }
            else {
                System.out.println("Hai sbagliato a digitare.");
            }
            }while(!success);
        }

    private void multiInt() throws RemoteException {
        String input;
        boolean success = false;
        Scanner in = new Scanner(System.in);

        while (!success) {
            System.out.println("< Torna al menu. (B)");
            System.out.println("Vuoi creare o partecipare ad una partita? [C/P]");
            input = in.nextLine();
            if (input.equals("B") || input.equals("b")) {
                menuInt();
                success = true;
            } else if (input.equals("C") || input.equals("c")) {
                createInt();
                success = true;
            } else if (input.equals("P") || input.equals("p")) {
                joinInt();
                success = true;
            } else System.out.println("Hai sbagliato a digitare!");
        }
    }

    private void joinInt(){
        String gamename;
        Scanner in = new Scanner(System.in);
        System.out.println("Ecco la lista delle partite attualmente attive:");
        boolean remoteException = true;
        try{
            System.out.println(serverController.getActiveMatchesList());
        }catch (RemoteException e){
            System.out.println(e.getMessage());
            remoteException = false;
        }
        boolean chosen = false;
        while (!chosen && remoteException) {
            System.out.println("Digita il il nome della partita cui vuoi partecipare:");
            gamename = in.nextLine();
            try {
                serverController.joinaGame(yourName, this, gamename);
                chosen= true;
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Sei entrato nella partita!");
    }

    private void createInt() {
        boolean success = false;
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (!success) {
            try {
                System.out.println("Stai per creare una partita di Sagrada." +
                        "Inserisci il nome della partita:");
                String gamename = in.nextLine();
                serverController.createGame(yourName, this, gamename);
                success = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println("La tua partita è stata creata correttamente!");
    }

    @Override
    public synchronized void update(Observable o, Object arg) throws RemoteException{

        if(this.thread !=null){
            this.thread.interrupt();
            this.thread = null;
        }
        State state =  o.getState();
        System.out.println("State: " +state.toString());
        switch (state){
            case ERRORSTATE: {
                this.thread = new Thread(new ErrorStateView(serverController, yourName));
                break;
            }
            case HASSETADICESTATE: {
                this.thread = new Thread(new HasSetDicesStateView(serverController, yourName));
                break;
            }
            case HASUSEDATOOLCARDACTIONSTATE: {
                this.thread = new Thread(new HasUsedAToolcardActionStateView(serverController, yourName));
                break;
            }
            case MATCHNOTSTARTEDYETSTATE: {
                this.thread = new Thread(new MatchNotStartedYetStateView(serverController, yourName));
                break;
            }
            case MUSTPASSTURNSTATE: {
                this.thread = new Thread(new MustPassTurnStateView(serverController, yourName));
                break;
            }
            case MUSTSSETDILUENTEPERPASTASALDASTATE: {
                this.thread = new Thread(new MustSetDiluentePerPastaSaldanteStateView(serverController, yourName));
                break;
            }
            case MUSTSETPENNELLOPERPASTASALDASTATE: {
                this.thread = new Thread(new MustSetPennelloPerPastaSaldanteStateView(serverController, yourName));
                break;
            }
            case NOTYOURTURNSTATE: {
                this.thread = new Thread(new NotYourTurnStateView(serverController, yourName));
                break;
            }
            case STARTTURNSTATE: {

                this.thread = new Thread(new StartTurnView(serverController, yourName));
                break;
            }
            case ENDMATCHSTATE: {
                leave = true;
                System.out.println("forza chievo");
                this.thread = new Thread(new EndMatchStateView(serverController, yourName, this));

                break;
            }
            case MUSTSETSCHEMECARD: {
                this.thread = new Thread(new MustSetSchemeCardStateView(serverController, yourName));
                break;
            }
        }
        this.notifyAll();
    }
}
