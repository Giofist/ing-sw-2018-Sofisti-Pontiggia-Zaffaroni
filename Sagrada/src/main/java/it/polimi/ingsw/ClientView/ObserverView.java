package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

//implemented by pon
public class ObserverView extends UnicastRemoteObject implements ObserverViewInterface, FeedObserverView {
    private ClientHandlerInterface servercontroller;
    private final Scanner in;
    private final PrintWriter out;
    private String yourName;

    //constructor1
    public ObserverView() throws RemoteException {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
    }

    //constructor2
    public ObserverView(ClientHandlerInterface controller) throws RemoteException {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
        this.servercontroller = controller;
    }

    public void setServercontroller(ClientHandlerInterface servercontroller) {
        this.servercontroller = servercontroller;
    }

    public void run() throws RemoteException {
        String stringa = servercontroller.rmiTest("RMI");
        System.out.println("La connessione è in modalità: " + stringa);
        loadingInterface();
        menuInt();
        waitingInt();
        //timer return and show
        startGameInt();
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
            if ( input.equals("S") || input.equals("s")) {
                signInInt();
                successo = true;
            }
            else if (input.equals("N") || input.equals("n")){
                logInInt();
                successo = true;
            }
            else
                System.out.println("Hai sbagliato a digitare.");
        }
    }
    private void logInInt(){
        String username;
        String password;
        boolean successo = false;
        boolean remoteException = false;
        while (!successo) {
            System.out.println("Inserisci un nuovo Username:");
            username = in.nextLine();
            this.yourName = username;
            System.out.println("Inserisci una password:");
            password = in.nextLine();
            try {
                servercontroller.register(username, password);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                remoteException = true;
            }
            if (!remoteException){
                System.out.println("Esegui il LogIn con l'account appena creato!");
                signInInt();
                successo = true;
            }
        }
    }


    private void signInInt(){
        Scanner in = new Scanner(System.in);
        String username;
        String password;
        boolean successo;
        do {
            successo = true;
            System.out.println("Inserisci username:");
            username = in.nextLine();
            System.out.println("Inserisci password:");
            password = in.nextLine();
            try {
                servercontroller.login(username, password);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                successo = false;
            }
        } while (!successo);
        }


    private void menuInt() throws RemoteException {
        String input;
        boolean success = false;
        while(!success){
            Scanner in = new Scanner(System.in);
            System.out.println("Benvenuto nel menù principale di Sagrada!");
            System.out.println("- Multi Giocatore (M)");
            System.out.println("- Giocatore Singolo (Coming soon!)");
            input = in.nextLine();
            if (input.equals("M") || input.equals("m")) {
                multiInt();
                success = true;
            }
        }
    }

    private void multiInt() throws RemoteException {
        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("< Torna al menu. (B)");
        System.out.println("Vuoi creare o partecipare ad una partita? [C/P]");
        input = in.nextLine();
        if (input.equals("B") || input.equals("b")) {
            menuInt();
        } else if (input.equals("C") || input.equals("c")) {
            createInt();
        } else if (input.equals("P") || input.equals("p")) {
            joinGameInt();
        }
        else System.out.println("Hai sbagliato a digitare!");
    }

    private void joinGameInt(){
        String gamename;
        Scanner in = new Scanner(System.in);
        System.out.println("Ecco la lista delle partite attualmente attive:");
        boolean remoteException = true;
        try{
            System.out.println(servercontroller.getActiveMatchList());
        }catch (RemoteException e){
            System.out.println(e.getMessage());
            remoteException = false;
        }
        boolean chosen = false;
        while (!chosen && remoteException) {
            System.out.println("Digita il il nome della partita cui vuoi partecipare:");
            gamename = in.nextLine();
            try {
                servercontroller.joinaGame(yourName, this, this, gamename);
                chosen= true;
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }

        }
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
                servercontroller.createGame(yourName, this, this, gamename);
                success = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void waitingInt() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        System.out.println("Attendi che altri giocatori entrino in partita...");
        //timer di attesa poi appena arriva notify parte il gioco
    }

    private synchronized void startGameInt() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        try{
            wait();
            //aspetto una notify dell'inizio della partita, per ora è solo un test connection
        }catch(InterruptedException e){
                //do nothing
        }
        System.out.println("Success in testing wait and notify!");

        //to be implemented.
    }


    //metodi per il pattern observer
    @Override
    public void notifyGameisStarting(String gamename) throws RemoteException{
        System.out.println("Il match" + gamename + "sta iniziando!");
        //TODO implement here the gameInt call
        //showSchemeCards();
    }


    @Override
    public synchronized void testConnection(boolean value) throws RemoteException{
        System.out.println("Test connection");
        notifyAll();
    }

    @Override
    public void update() {
        }

    @Override
    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showSchemeCards(String schemeCard1, String schemeCard2, String schemeCard3, String schemeCard4) throws RemoteException {
        Scanner in = new Scanner(System.in);
        int response;
        String input;
        boolean successo = false;
//TODO cambiare stampaggio celle a schermo
        System.out.println("|Mappa 1|");
        System.out.println(schemeCard1);
        System.out.println("|Mappa 2|");
        System.out.println(schemeCard2);
        System.out.println("|Mappa 3|");
        System.out.println(schemeCard3);
        System.out.println("|Mappa 4|");
        System.out.println(schemeCard4);

        while (!successo){
            System.out.println("Seleziona la carta schema che desideri tra le seguenti indicando il numero relativo. [1/4]");
            response = in.nextInt();
            System.out.println("Hai scelto la mappa " + response + ", è corretto? [S/N]");
            input = in.nextLine();
                if ( input.equals("S") || input.equals("s")) {
                    servercontroller.setSchemeCard(yourName, response);
                    successo = true;
                }
                else if (input.equals("N") || input.equals("n")){}
                else
                    System.out.println("Hai sbagliato a digitare.");
        }
    }

    @Override
    public void notifyaDraw() {
        try{
            System.out.println("Hai pareggiato:"+"Il tuo punteggio è" + servercontroller.getmyPoints(this.yourName));
    }catch (RemoteException e ){
        System.out.println(e.getMessage());
    };
    }

    @Override
    public void notifyaLose() {
        try{
            System.out.println("Mi spiace, hai perso"+ "Il tuo punteggio è" + servercontroller.getmyPoints(this.yourName));
        }catch (RemoteException e ){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void notifyaWin() {
        try{
            System.out.println("Congratulazioni, hai vinto"+ "Il tuo punteggio è" + servercontroller.getmyPoints(this.yourName));
        }catch (RemoteException e ){
            System.out.println(e.getMessage());
        }
    }


}