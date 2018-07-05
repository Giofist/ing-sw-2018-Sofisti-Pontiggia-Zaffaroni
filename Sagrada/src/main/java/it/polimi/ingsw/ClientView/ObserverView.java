package it.polimi.ingsw.ClientView;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.Observable;
import it.polimi.ingsw.model.State;

import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;


/**
 * This class is the main client CLI view responsible for displaying the main menù and allowing the player to create
 * new matches. This main view will also spawn in a new thread the correct view to display to the user based on updates
 * received from the server about changes in the state.
 */
public class ObserverView extends UnicastRemoteObject implements Observer {
    private transient ClientHandlerInterface serverController;
    private transient final Scanner in;
    private transient String yourName;
    private transient Thread thread;
    private transient boolean leaveMatch;
    private transient boolean leave;
    private transient boolean leaveSagrada;


    /**
     * Constructor for the observer view in socket connection
     * @throws RemoteException Exception thrown if an error occurs in creating an input stream for the user
     */
    public ObserverView() throws RemoteException {
        this.in = new Scanner(System.in);
        leaveMatch = false;
        leave =false;
        leaveSagrada = false;
        thread = null;
    }


    /**
     * Constructor for the observer view in RMI connection
     * @param controller The interface with all the methods exposed by the server
     * @throws RemoteException Exception thrown if an error occurs in creating an input stream for the user
     */
    public ObserverView(ClientHandlerInterface controller) throws RemoteException {
        this.in = new Scanner(System.in);
        this.serverController = controller;
    }


    /**
     * @param serverController The interface with all the methods exposed by the server
     */
    public void setServerController(ClientHandlerInterface serverController) {
        this.serverController = serverController;
    }



    /**
     * Main method that calls submethods to guide the user through the registration or the log in and the main menù
     * @throws RemoteException
     */
    public synchronized void run() throws RemoteException {
        loadingInterface();
        boolean wasInGame = false;
        try {
            wait(10000);
            //To wait in case wa are already in a match
        } catch (InterruptedException e){
                ;
        }
        if(this.thread != null){
            wasInGame = true;
        }else{
            menuInt();
        }
        while (!leaveSagrada){
            if(!leaveSagrada){
                while (!leaveMatch){
                    try {
                        if(wasInGame == false){
                            wait();
                        }
                        this.thread.start();
                        wasInGame = false;
                        if(leave){
                            leaveMatch = true;
                            wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    /**
     * Method responsible for asking the user if he already has an account or if he wants to create a new one
     */
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


    /**
     * Method responsible for guiding the user through the creation of a new account
     */
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
                System.out.println(Client.translator.translateException(e.getMessage()));
                remoteException = true;
            }
            if (remoteException == false){
                System.out.println("Esegui il LogIn con l'account appena creato!");
                logInInt();
                successo = true;
            }
        }
    }


    /**
     * Method responsible for guiding the user through the login of an existent account
     */
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
                System.out.println(Client.translator.translateException(e.getMessage()));
                success = false;
            }
            this.yourName = username;
        } while (!success);
    }


    /**
     * Method responsible for displaying the main menù and letting the user choose which action he want to perform
     * @throws RemoteException
     */
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
                serverController.logout(this.yourName, this);
                leaveSagrada = false;
                success = true;
            }
            else {
                System.out.println("Hai sbagliato a digitare.");
            }
            }while(!success);
        }

    /**
     * Method responsible for guiding the user through the creation of a new match or letting him choose which match to join
     * @throws RemoteException
     */
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

    /**
     * Method responsible for showing to the user the available matches and for asking it which one he would like to join
     */
    private void joinInt(){
        String gamename;
        Scanner in = new Scanner(System.in);
        System.out.println("Ecco la lista delle partite attualmente attive:");
        boolean remoteException = false;
        try{
            List<Match> list= serverController.getActiveMatchesList();
            for( Match match: list){
                System.out.println(Client.translator.translateMatch(match));
            }
            System.out.println();
        }catch (RemoteException e){
            System.out.println(Client.translator.translateException(e.getMessage()));
            remoteException = true;
        }
        if(!remoteException){
            boolean chosen = false;
            while (!chosen) {
                System.out.println("Digita il il nome della partita cui vuoi partecipare:");
                gamename = in.nextLine();
                try {
                    serverController.joinaMatch(yourName, this, gamename);
                    chosen= true;
                    System.out.println("Sei entrato nella partita!");
                } catch (RemoteException e) {
                    System.out.println(Client.translator.translateException(e.getMessage()));
                }
            }
        }
    }


    /**
     *  Method responsible for the actual creation of a new match
     */
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

    /**
     * This method is responsible for invoking the correct view to display based on the state of the user notified by
     * the server through an update
     * @param o
     * @param arg
     * @throws RemoteException
     */
    @Override
    public synchronized void update(Observable o, Object arg) throws RemoteException{
        if(this.thread !=null){
            this.thread.interrupt();
            this.thread = null;
        }
        State state =  o.getState();
        switch (state){
            case ERRORSTATE: {
                leave = true;
                this.thread = new Thread(new ErrorStateView(serverController, yourName, this));
                break;
            }
            case HASSETADICESTATE: {
                this.thread = new Thread(new HasSetDicesStateView(serverController, yourName, this));
                break;
            }
            case HASUSEDATOOLCARDACTIONSTATE: {
                this.thread = new Thread(new HasUsedAToolcardActionStateView(serverController, yourName, this));
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
                this.thread = new Thread(new NotYourTurnStateView(serverController, yourName, this));
                break;
            }
            case STARTTURNSTATE: {
                this.thread = new Thread(new StartTurnView(serverController, yourName, this));
                break;
            }
            case ENDMATCHSTATE: {
                leave = true;
                this.thread = new Thread(new EndMatchStateView(serverController, yourName, this));
                break;
            }
            case MUSTSETSCHEMECARD: {
                this.thread = new Thread(new MustSetSchemeCardStateView(serverController, yourName));
                break;
            }
            case FORCEENDMATCH:{
                leave = true;
                this.thread = new Thread(new ForceEndMatchState(this, yourName, serverController));
                break;
            }
            default:System.out.println("Ho ricevuto un'update dal server ma non riesco a interpretarla");
        }
        this.notifyAll();
    }

    @Override
    public void ping() throws RemoteException{
        return;
    }
}
