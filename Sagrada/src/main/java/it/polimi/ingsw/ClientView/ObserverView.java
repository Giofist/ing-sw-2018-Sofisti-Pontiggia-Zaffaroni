package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

//implemented by pon
public class ObserverView extends UnicastRemoteObject implements ObserverViewInterface, FeedObserverView {
    private ClientHandlerInterface servercontroller;
    private final Scanner in;
    private final PrintWriter out;
    private String yourName;
    private boolean matchisEnded;
    private char[] yourMap;
    private int[] yourMapDiceIntensity;
    private int yourMapMaxRow = 0;
    private int yourMapMaxColumn = 0;
    private int round=10;
    private char[] roundTrackDiceColour; // = {'g','y','b','y','b','r','r','b','r','g','y','_','g','b','_','_','_','_','_','_','_','_','r','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'};  //Test values
    private int[] roundTrackDiceIntensity; //= {1,3,5,6,0,0,0,0,0,0,3,0,2,1,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //test values
    private String roundDicepool; // = "6RED-5YELLOW-2RED-2GREEN-3BLUE-";

    //constructor1
    public ObserverView() throws RemoteException {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
        matchisEnded = false;
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

    public void run() throws RemoteException, SchemeCardNotExistantException {
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
            try {
                servercontroller.login(username, password);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                success = false;
            }
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
                servercontroller.logout(this.yourName);
                System.exit(0);
                success = true;
            }
            else {
                System.out.println("Hai sbagliato a digitare.");
            }
            }while(!success);
        }

    private void multiInt() throws RemoteException {
        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("< Torna al menu. (B)");
        System.out.println("Vuoi creare o partecipare ad una partita? [C/P]");
        input = in.nextLine();
        if (input.equals("B") || input.equals("b")) {
            menuInt();
        }
        else if (input.equals("C") || input.equals("c")) {
            createInt();
        }
        else if (input.equals("P") || input.equals("p")) {
            joinInt();
        }
        else System.out.println("Hai sbagliato a digitare!");
    }

    private void joinInt(){
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

    private synchronized void startGameInt() throws RemoteException, SchemeCardNotExistantException {
        Scanner in = new Scanner(System.in);
        String schemeCards;
        boolean correct = false;
        int index;

        try {
            System.out.println("sto aspettando una notifica\n");
            wait();
            //aspetto una notify dell'inizio della partita, per ora è solo un test connection
        } catch (InterruptedException e) {
            //do nothing
        }

            AnsiConsole.out.println("Success in testing wait and notify!");

            schemeCards = servercontroller.getSchemeCards(this.yourName);
            String[] schemeCardsArray = schemeCards.split("!"); //creo un array con le sngole mappe

            for (index = 0 ; index < schemeCardsArray.length ; index++){
                String[] schemeCardAttribute = schemeCardsArray[index].split("-");
                //System.setProperty("jansi.passthrough", "true");
                //AnsiConsole.systemInstall();
                System.out.println(ansi().eraseScreen().fg(GREEN).a(schemeCardAttribute[0]).reset());
                //AnsiConsole.systemUninstall();
                System.out.println("Difficoltà della mappa: " + schemeCardAttribute[1]);
                char[] constrain = schemeCardAttribute[4].toCharArray();
                printMap(constrain,yourMapDiceIntensity,Integer.parseInt(schemeCardAttribute[2]),Integer.parseInt(schemeCardAttribute[3]));
                System.out.print("\n");
            }

            while(!correct){
                System.out.println("Seleziona la carta schema che desideri tra le seguenti indicando il numero relativo.");
                int selectedCard = in.nextInt();
                try {
                    servercontroller.setSchemeCard(this.yourName, selectedCard);
                    correct = true;
                } catch (RemoteException e){
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("La carta schema da te scelta è: ");
            String[] yourSchemeCardAttribute = servercontroller.getMymapString(yourName).split("-");
            this.yourMapMaxRow = Integer.parseInt(yourSchemeCardAttribute[0]);
            this.yourMapMaxColumn = Integer.parseInt(yourSchemeCardAttribute[1]);
            this.yourMap = yourSchemeCardAttribute[2].toCharArray();
            //System.setProperty("jansi.passthrough", "true");
            //AnsiConsole.systemInstall();
            printMap(yourMap ,yourMapDiceIntensity, yourMapMaxRow , yourMapMaxColumn);
            //AnsiConsole.systemUninstall();
            System.out.print("\n");
            System.out.println("Attendi che anche gli altri giocatori abbiano scelto la loro mappa.");

        while (!matchisEnded) {
            try{
                wait();
                this.roundDicepool = servercontroller.getRoundDicepool(yourName);
                System.out.println(this.roundDicepool);
                printRoundDicePool(roundDicepool);
            }catch(InterruptedException e){
                // do nothing
            }
            System.out.println("ho superato una wait");
        }
        try {
            System.out.println("la partita è finita: hai totalizzato" + servercontroller.getmyPoints(yourName) + "punti");
            System.out.println("Ecco la classifica finale: " + servercontroller.getRanking(yourName));
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        //to be implemented.
        try {
            System.out.println();
            menuInt();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //metodo che stampa la mappa a schermo
    public void printMap(char[] map, int[] yourMapDiceIntensity, int maxRow, int maxColumn){
        for(int row = 0 ; row < maxRow ; row++){
            for(int column = 0 ; column < maxColumn ; column++){
                switch (map[row * maxColumn + column]) {
                    case 'Y':
                        System.out.print( ansi().eraseScreen().bg(YELLOW).a("   ").reset());
                        break;
                    case 'B':
                        System.out.print( ansi().eraseScreen().bg(BLUE).a("   ").reset());
                        break;
                    case 'R':
                        System.out.print( ansi().eraseScreen().bg(RED).a("   ").reset());
                        break;
                    case 'V':
                        System.out.print( ansi().eraseScreen().bg(MAGENTA).a("   ").reset());
                        break;
                    case 'G':
                        System.out.print( ansi().eraseScreen().bg(GREEN).a("   ").reset());
                        break;
                    case '1':
                        System.out.print( ansi().eraseScreen().bg(WHITE).fg(BLACK).a(" 1 ").reset());
                        break;
                    case '2':
                        System.out.print( ansi().eraseScreen().bg(WHITE).fg(BLACK).a(" 2 ").reset());
                        break;
                    case '3':
                        System.out.print( ansi().eraseScreen().bg(WHITE).fg(BLACK).a(" 3 ").reset());
                        break;
                    case '4':
                        System.out.print( ansi().eraseScreen().bg(WHITE).fg(BLACK).a(" 4 ").reset());
                        break;
                    case '5':
                        System.out.print( ansi().eraseScreen().bg(WHITE).fg(BLACK).a(" 5 ").reset());
                        break;
                    case '6':
                        System.out.print( ansi().eraseScreen().bg(WHITE).fg(BLACK).a(" 6 ").reset());
                        break;
                    case '_':
                        System.out.print( ansi().eraseScreen().bg(WHITE).fg(BLACK).a("   ").reset());
                        break;
                    case 'y':
                        System.out.print( ansi().eraseScreen().bg(YELLOW).fg(WHITE).a(" " + yourMapDiceIntensity[row * maxColumn+ column] + " ").reset());
                        break;
                    case 'b':
                        System.out.print( ansi().eraseScreen().bg(BLUE).fg(WHITE).a(" " + yourMapDiceIntensity[row * maxColumn+ column] + " ").reset());
                        break;
                    case 'r':
                        System.out.print( ansi().eraseScreen().bg(RED).fg(WHITE).a(" " + yourMapDiceIntensity[row * maxColumn+ column] + " ").reset());
                        break;
                    case 'v':
                        System.out.print( ansi().eraseScreen().bg(MAGENTA).fg(WHITE).a(" " + yourMapDiceIntensity[row * maxColumn+ column] + " ").reset());
                        break;
                    case 'g':
                        System.out.print( ansi().eraseScreen().bg(GREEN).fg(WHITE).a(" " + yourMapDiceIntensity[row * maxColumn+ column] + " ").reset());
                        break;
                }
            }
            System.out.print("\n");
        }
    }

    public void printRoundTrack(char[] dicecolour, int[] diceIntensity, int round) {
        for (int i = 1; i < round + 1; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < round; column++) {
                switch (dicecolour[row * round + column]) {
                    case '_':
                        System.out.print(ansi().eraseScreen().fg(BLACK).a("   ").reset());
                        break;
                    case 'y':
                        System.out.print(ansi().eraseScreen().bg(YELLOW).fg(BLACK).a(" " + diceIntensity[row * 10 + column] + " ").reset());
                        break;
                    case 'b':
                        System.out.print(ansi().eraseScreen().bg(BLUE).fg(BLACK).a(" " + diceIntensity[row * 10 + column] + " ").reset());
                        break;
                    case 'r':
                        System.out.print(ansi().eraseScreen().bg(RED).fg(BLACK).a(" " + diceIntensity[row * 10 + column] + " ").reset());
                        break;
                    case 'v':
                        System.out.print(ansi().eraseScreen().bg(MAGENTA).fg(BLACK).a(" " + diceIntensity[row * 10 + column] + " ").reset());
                        break;
                    case 'g':
                        System.out.print(ansi().eraseScreen().bg(GREEN).fg(BLACK).a(" " + diceIntensity[row * 10 + column] + " ").reset());
                        break;
                }
            }
            System.out.println();
        }
    }

    public void printRoundDicePool(String roundDicePool){
        int index = 0;
        char[] charDice;
        String[] dices = roundDicePool.split("-");
        System.out.println("Ecco i dadi disponibili in questo round:");
        for (String dice : dices) {
            charDice = dice.toCharArray();
            switch(charDice[1]){
            case 'Y':
                System.out.print( ansi().eraseScreen().bg(YELLOW).fg(BLACK).a(" " + charDice[0] + " ").reset());
                break;
            case 'B':
                System.out.print( ansi().eraseScreen().bg(BLUE).fg(BLACK).a(" " + charDice[0] + " ").reset());
                break;
            case 'R':
                System.out.print( ansi().eraseScreen().bg(RED).fg(BLACK).a(" " + charDice[0] + " ").reset());
                break;
            case 'V':
                System.out.print( ansi().eraseScreen().bg(MAGENTA).fg(BLACK).a(" " + charDice[0] + " ").reset());
                break;
            case 'G':
                System.out.print( ansi().eraseScreen().bg(GREEN).fg(BLACK).a(" " + charDice[0] + " ").reset());
                break;
            }
            System.out.print(" ");
        }
        System.out.println();
        for (String dice : dices) {
            System.out.print(" " + index + "  ");
            index++;
        }
    }


    //metodi per il pattern observer
    @Override
    public synchronized void notifyGameisStarting() throws RemoteException{
        notifyAll();
    }



    @Override
    public void update() {
        notifyAll();
        }

    @Override
    public synchronized void showErrorMessage(String message) {
        System.out.println(message);
        notifyAll();
    }


    @Override
    public synchronized void notifyendGame() throws RemoteException {
        this.matchisEnded = true;
        notifyAll();
    }


    @Override
    public synchronized void notifyIsYourTurn(String message) throws RemoteException{
        System.out.println(message);
        // Fai partire la procedura di gestione del turno
    }


}
