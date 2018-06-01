package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.UserNotExistentException;
import org.fusesource.jansi.AnsiConsole;

import static it.polimi.ingsw.model.DiceColor.*;
import static java.awt.Color.BLACK;
import static java.awt.Color.MAGENTA;
import static java.awt.Color.WHITE;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

//implemented by pon
public class ObserverView extends UnicastRemoteObject implements ObserverViewInterface, FeedObserverView{
    private ClientHandlerInterface servercontroller;
    private final Scanner in;
    private final PrintWriter out;
    private String yourName;
    private boolean matchisEnded;
    private int numOfDice;

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
            System.out.println(servercontroller.getActiveMatchesList());
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

    private synchronized void startGameInt() throws RemoteException {
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
            String[] schemeCardsArray = schemeCards.split("'"); //creo un array con le sngole mappe

            for (index = 0 ; index < schemeCardsArray.length ; index++){
                printMap(schemeCardsArray[index]);
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
            printMap(servercontroller.getSchemeCard(yourName));
            printGoalCards();
            //AnsiConsole.systemUninstall();
            System.out.print("\n");
            System.out.println("Attendi che anche gli altri giocatori abbiano scelto la loro mappa.\n");

        while (!matchisEnded) {
            try{
                wait();
                printRoundTrack();
                printRoundDicePool();
                System.out.println();
                printToolcard();
                placeDice();
                printMap(servercontroller.getSchemeCard(yourName));

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
            System.out.println(e.getMessage());
        }
    }

    //metodo che permette di piazzare un dado
    public void placeDice(){
        Scanner in = new Scanner(System.in);
        int diceIndex = numOfDice + 2;
        int row = 100;
        int column = 100;
        boolean success = false;
        try {
            printMap(servercontroller.getSchemeCard(yourName));
        } catch (RemoteException e) {
           System.out.println(e.getMessage());
        }
        while (!success) {
            success =true;
            System.out.println("Seleziona il dado da piazzare usando l'indice numerico sotto riportato:");
            printRoundDicePool();
            diceIndex = in.nextInt();
            System.out.println("Seleziona la riga in cui posizionare il dado: [0/3]");
            row = in.nextInt();
            System.out.println("Seleziona la riga in cui posizionare il dado: [0/4]");
            column = in.nextInt();
            try {
                servercontroller.setDice(yourName, diceIndex, row, column);
            } catch (UserNotExistentException e) {
                System.out.println(e.getMessage());
                success = false;
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                success = false;
            } catch (SchemeCardNotExistantException e) {
                System.out.println(e.getMessage());
                success = false;
            }
        }
    }

    public void passYourTurn(){
        try {
            servercontroller.passTurn(yourName);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void useToolcard(){
        Scanner in = new Scanner(System.in);
        String input;
        //printToolcard();
        System.out.println("Quale carta utensile vuoi usare?");
        input = in.nextLine();
        //servercontroller.useaToolCard(yourName, input);
    }

    public void printMap(String map){
        char[] charTile;
        String[] element = map.split("%");
        System.out.println(element[0]);
        System.out.println(element[1]);
        String[] tiles = element[2].split("!");
       for (String rowTile: tiles){
           String[] column = rowTile.split("-");
           for (String el : column) {
               charTile = el.toCharArray();
               switch (charTile[1]) {
                   case 'Y':
                       System.out.print(ansi().eraseScreen().bg(YELLOW).a("   ").reset());
                       break;
                   case 'B':
                       System.out.print(ansi().eraseScreen().bg(BLUE).a("   ").reset());
                       break;
                   case 'R':
                       System.out.print(ansi().eraseScreen().bg(RED).a("   ").reset());
                       break;
                   case 'V':
                       System.out.print(ansi().eraseScreen().bg(MAGENTA).a("   ").reset());
                       break;
                   case 'G':
                       System.out.print(ansi().eraseScreen().bg(GREEN).a("   ").reset());
                       break;
                   case '*':
                       System.out.print(ansi().eraseScreen().bg(WHITE).fg(BLACK).a(" " + charTile[0] + " ").reset());
                       break;
                   case '_':
                       System.out.print(ansi().eraseScreen().bg(WHITE).fg(BLACK).a("   ").reset());
                       break;
                   case 'y':
                       System.out.print(ansi().eraseScreen().bg(YELLOW).fg(WHITE).a(" " + charTile[0] + " ").reset());
                       break;
                   case 'b':
                       System.out.print(ansi().eraseScreen().bg(BLUE).fg(WHITE).a(" " + charTile[0] + " ").reset());
                       break;
                   case 'r':
                       System.out.print(ansi().eraseScreen().bg(RED).fg(WHITE).a(" " + charTile[0] + " ").reset());
                       break;
                   case 'v':
                       System.out.print(ansi().eraseScreen().bg(MAGENTA).fg(WHITE).a(" " + charTile[0] + " ").reset());
                       break;
                   case 'g':
                       System.out.print(ansi().eraseScreen().bg(GREEN).fg(WHITE).a(" " + charTile[0] + " ").reset());
                       break;
               }
           }
           System.out.print("\n");
       }

   }

    //metodo che stampa a shcermo il roundTrack
    public void printRoundTrack(){
        System.out.println("Round track:");
        int round =10;
        int i=1;
        char[] charDice;

        String[] dices = new String[0];
        try {
            dices = this.servercontroller.getRoundTrack(yourName).split("!");
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        for (String diceList : dices){
            if(i<10) {
                System.out.print(ansi().eraseScreen().bg(WHITE).fg(BLACK).a("  " + i + " ").reset());
            }
            else System.out.print(ansi().eraseScreen().bg(WHITE).fg(BLACK).a(" " + i + " ").reset());
            String[] singleDice = diceList.split("-");
            for(String stringDice :singleDice) {
                charDice = stringDice.toCharArray();
                    switch (charDice[1]) {
                        case 'Y':
                            System.out.print(ansi().eraseScreen().bg(YELLOW).fg(WHITE).a(" " + charDice[0] + " ").reset());
                            break;
                        case 'B':
                            System.out.print(ansi().eraseScreen().bg(BLUE).fg(WHITE).a(" " + charDice[0] + " ").reset());
                            break;
                        case 'R':
                            System.out.print(ansi().eraseScreen().bg(RED).fg(WHITE).a(" " + charDice[0] + " ").reset());
                            break;
                        case 'V':
                            System.out.print(ansi().eraseScreen().bg(MAGENTA).fg(WHITE).a(" " + charDice[0] + " ").reset());
                            break;
                        case 'G':
                            System.out.print(ansi().eraseScreen().bg(GREEN).fg(WHITE).a(" " + charDice[0] + " ").reset());
                            break;
                    }
                }
                System.out.println();
                i++;
            }
            this.numOfDice = i;
        System.out.println();
        }

    //metodo che stampa i dadi estratti
    public void printRoundDicePool() {
        int index = 0;
        char[] charDice;
        String[] dices = new String[0];

        try {
            dices = servercontroller.getRoundDicepool(yourName).split("-");
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Ecco i dadi disponibili in questo round:");
        for (String dice : dices) {
            charDice = dice.toCharArray();
            switch(charDice[1]){
            case 'Y':
                System.out.print( ansi().eraseScreen().bg(YELLOW).fg(WHITE).a(" " + charDice[0] + " ").reset());
                break;
            case 'B':
                System.out.print( ansi().eraseScreen().bg(BLUE).fg(WHITE).a(" " + charDice[0] + " ").reset());
                break;
            case 'R':
                System.out.print( ansi().eraseScreen().bg(RED).fg(WHITE).a(" " + charDice[0] + " ").reset());
                break;
            case 'V':
                System.out.print( ansi().eraseScreen().bg(MAGENTA).fg(WHITE).a(" " + charDice[0] + " ").reset());
                break;
            case 'G':
                System.out.print( ansi().eraseScreen().bg(GREEN).fg(WHITE).a(" " + charDice[0] + " ").reset());
                break;
            }
            System.out.print(" ");
        }
        System.out.println();
        for (String dice : dices) {
            System.out.print(" " + index + "  ");
            index++;
        }
        System.out.println();
    }

    public void printGoalCards(){
        String[] cardName = new String[0];
        String[] description = new String[0];
        System.out.println("\n-Ecco gli obbiettivi di questa partita-");
        System.out.println("\nObbiettivo privato:");
        try {
            System.out.println(servercontroller.getPrivateGoalCardname(yourName));
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(servercontroller.getPrivateGoalCarddescription(yourName));
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nObbiettivo pubblico:");


        try {
            cardName = servercontroller.getPublicGoalCardnames(yourName).split("!");
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        try {
            description = servercontroller.getPublicGoalCarddescriptions(yourName).split("!");
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }

        for (int i=0; i < cardName.length; i++) {
            System.out.println(cardName[i] + "\n" + description[i]);
        }
    }

    //metodo che stampa le toolcard
    public void printToolcard(){
        int index =0;
        System.out.println("Queste sono le carte utensile disponibili:");
        try {
            String[] toolCardID = servercontroller.getToolCardsIDs(yourName).split("!");
            String[] toolCardName = servercontroller.getToolCardsNames(yourName).split("!");
            String[] toolCardCost = servercontroller.getToolCardsCosts(yourName).split("!");
            String[] toolCardDescription = servercontroller.getToolCardsDescriptions(yourName).split("!");
            for (String element: toolCardID) {
                System.out.println(toolCardID[index] +". " + toolCardName[index] + "\nIl costo della carta utensile è: " + toolCardCost[index] + "\nDescrizione:\n" + toolCardDescription[index]);
                index++;
            }
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    //metodo che serve per selozioanre possibili azioni
    public void selectAction() {
        Scanner in = new Scanner(System.in);
        String input;
        boolean success = false;

        while (!success) {
            System.out.println("Scegli una delle seguenti azioni:");
            try {
                System.out.println(servercontroller.getPossibleActions(yourName)); //todo altra possibilità sarebbe dividere questa stringa e vedere se l'azione selezionata è contenuta tra quelle fattibili.
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
            input = in.nextLine().toLowerCase();

            switch (input) {
                case "setdice": {
                    placeDice();
                    success = true;
                    break;
                }
                case "getmaps": {
                    System.out.println("Not yet implemented!");
                    break;
                }
                case "usetoolcard": {
                    useToolcard();
                    break;
                }
                case "passturn": {
                    passYourTurn();
                    success = true;
                    break;
                }
                default:{
                    System.out.println("Hai sbagliato a digitare!");
                    break;
                }
            }
        }
    }


    //metodi per il pattern observer
    @Override
    public synchronized void notifyGameisStarting() throws RemoteException{
        notifyAll();
    }

    @Override
    public void update() throws RemoteException{
        notifyAll();
        }

    @Override
    public synchronized void showErrorMessage(String message) throws RemoteException {
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
