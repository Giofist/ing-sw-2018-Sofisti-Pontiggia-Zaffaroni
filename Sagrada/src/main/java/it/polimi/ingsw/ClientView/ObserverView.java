package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Exceptions.NumberOfPlayersNotAllowedException;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesList;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import static java.lang.System.*;


//implemented by pon
//non implementa runnable
public class ObserverView extends UnicastRemoteObject implements ObserverViewInterface, FeedObserverView {
    private ClientHandlerInterface servercontroller;
    private final Scanner in;
    private final PrintWriter out;
    private String username;

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
        loadingInt();
        choseInt();
        loginInt();
        menuInt();
    }

    private void choseInt() throws RemoteException {
        boolean successo = false;
        String input;
        while (!successo) {
            System.out.println("Hai già un account? [S/N]\n");
            input = in.nextLine();
            if ( input.equals("S") || input.equals("s")) {
                signInInt();
                successo = true;
            }
            else if (input.equals("N") || input.equals("n")){
                loginInt();
                successo = true;
            }
            else
                System.out.println("Hai sbagliato a digitare.");
        }
    }

    private void loadingInt() {
        System.out.println("Benvenuto in...\n");
        System.out.print("\n" +
                " ▄▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄    ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░▌  ▐░░░░░░░░░░░▌\n" +
                "▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌\n" +
                "▐░▌                 ▐░▌            ▐░▌▐░▌                 ▐░▌            ▐░▌▐░▌            ▐░▌▐░▌             ▐░▌▐░▌            ▐░▌\n" +
                "▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░▌  ▄▄▄▄▄▄▄▄  ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░▌            ▐░▌▐░█▄▄▄▄▄▄▄█░▌\n" +
                "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌ ▐░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌            ▐░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀█░▌ ▐░█▀▀▀▀▀▀▀█░▌▐░▌  ▀▀▀▀▀▀█░▌ ▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌            ▐░▌▐░█▀▀▀▀▀▀▀█░▌\n" +
                "                ▐░▌ ▐░▌            ▐░▌▐░▌            ▐░▌ ▐░▌        ▐░▌     ▐░▌            ▐░▌▐░▌            ▐░▌▐░▌            ▐░▌\n" +
                " ▄▄▄▄▄▄▄▄▄█░▌ ▐░▌            ▐░▌▐░█▄▄▄▄▄▄▄█░▌ ▐░▌        ▐░▌     ▐░▌            ▐░▌▐░█▄▄▄▄▄▄▄█░▌▐░▌           ▐░▌\n" +
                "▐░░░░░░░░░░░▌▐░▌            ▐░▌▐░░░░░░░░░░░▌ ▐░▌        ▐░▌     ▐░▌            ▐░▌▐░░░░░░░░░░▌  ▐░▌           ▐░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀▀   ▀               ▀  ▀▀▀▀▀▀▀▀▀▀▀   ▀           ▀       ▀               ▀  ▀▀▀▀▀▀▀▀▀▀     ▀             ▀ \n" +
                "                                                                                           \n");



    }

    private void loginInt() throws RemoteException {
        String username;
        String password;

        boolean successo = false;

        while (successo == false) {
            try {
                System.out.println("Inserisci un nuovo Username:\n");
                username = in.nextLine();
                out.println("Inserisci una password:\n");
                password = in.nextLine();
                try {
                    servercontroller.register(username, password);
                } catch (RemoteException e) {
                    System.out.println(e.getMessage());
                }
                out.println("Esegui il LogIn con l'account appena creato:");
                signInInt();
                successo = true;
            }catch (RemoteException e) {
                out.println(e.getMessage());
                out.close();
                in.close();
            }
        }
    }




    private void signInInt() throws RemoteException {
        String username;
        String password;
        boolean successo = true;
        do {
            successo = true;

            out.println("Inserisci username:\n");
            username = in.nextLine();
            out.println("Inserisci password:\n");
            password = in.nextLine();
            try {
                servercontroller.login(username, password);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
                successo = false;
            }
        } while (successo == false);
        }


    private void menuInt() throws RemoteException {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("Benvenuto nel menù principale di Sagrada!");
        out.println("- Multi Giocatore (M)");
        out.println("- Giocatore Singolo (Coming soon!)");
        //out.println("- Impostazioni (I)");
        if (in.next() == "M" || in.next() == "m") {
            multiInt();
        }
    }

    private void multiInt() throws RemoteException { //da implemnetare
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("/n/nVuoi creare o partecipare ad una partita? [C/P]");
        out.println("\n\n< Torna al menu. (B)");
        if (in.next() == "B" || in.next() == "b") {
            menuInt();
        } else if (in.next() == "C" || in.next() == "c") {
            creaInt();
        } else partecipaInt();
    }

    private void partecipaInt() throws RemoteException {
        String gamename = null;
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("Scegli la partita a cui vuoi partecipare dalla lista:");
        for (Game game : GamesList.singleton().getgames()) {
            // Correzione per pushare codice non rotto (ho tenuta la vecchia riga verifica che la nuova vada bene)
            //out.println(game.getName() + "; Giocatori che stanno partecipando: " + game.getActualNumberOfPlayers() + "Giocatori necessari alla partita: " + game.getMaxNumberPlayers() + "\n");
            out.println(game.getName() + "; Giocatori che stanno partecipando: " + game.getNumberOfPlayers() + "Giocatori necessari alla partita: 4\n");
        }
        boolean chosen = false;
        while (!chosen) {
            out.println("Vuoi ancora partecipare ad una partita? [S/N]\n");
            if (in.nextLine() == "S" || in.nextLine() == "s") {
                while (!servercontroller.isMatchInList(gamename)) {
                    out.println("Digita il il nome della partita cui vuoi partecipare:\n");
                    gamename = in.nextLine();
                    try {
                        servercontroller.joinaGame(username, gamename);
                    } catch (RemoteException e) {
                        out.println(e.getMessage());
                    }
                }
                waitingInt();
                //timer return and show
                startGameInt();
                chosen = true;

            } else {
                multiInt();
            }
        }
    }

    private void startGameInt() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("Il gioco inizia ora!");

        //to be implemented.
    }

    private void waitingInt() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("Attendi che altri giocatori entrino in partita...\n");
        ;
    }


    private void creaInt() {
        boolean success = false;
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("Stai per creare una partita di sagrada./n" +
                "Inserisci il nome della partita:\n");
        while (!success) {
            try {
                String name = in.nextLine();
                out.println("Scegli il numero di giocatori necessario alla partita (incluso te stesso) [0/4]\n");
                int max = in.nextInt();
                if (max < 2 || max > 4) {
                    throw new NumberOfPlayersNotAllowedException();
                }
                servercontroller.createGame(username, this, this, name);   //mancano observer e forse anche num players
                out.println("Attendi che alttri giocatori partecipino alla partita.\n Divertiti!\n");
                success = true;
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        }
    }

    //metodi per il pattern observer
    @Override
    public void notifyGameisStarting(String gamename) throws RemoteException{
        System.out.println("Game" + gamename + "is starting");
    };



    @Override
    public void update() {

    }

    @Override
    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showSchemeCards(SchemeCard schemeCard1, SchemeCard schemeCard2) {
        System.out.println("Seleziona la carta schema che desideri tra le seguenti indicando il numero relativo");
        System.out.println(schemeCard1.displayScheme());
        System.out.println(schemeCard1.getTwinCard().displayScheme());
        System.out.println(schemeCard2.displayScheme());
        System.out.println(schemeCard1.getTwinCard().displayScheme());
    }

    @Override
    public void notifyaDraw() {
        try{
            System.out.println("Hai pareggiato:"+"Il tuo punteggio è" + servercontroller.getmyPoints(this.username));
    }catch (RemoteException e ){
        System.out.println(e.getMessage());
    };
    }

    @Override
    public void notifyaLose() {
        try{
            System.out.println("Mi spiace, hai perso"+ "Il tuo punteggio è" + servercontroller.getmyPoints(this.username));
        }catch (RemoteException e ){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void notifyaWin() {
        try{
            System.out.println("Congratulazioni, hai vinto"+ "Il tuo punteggio è" + servercontroller.getmyPoints(this.username));
        }catch (RemoteException e ){
            System.out.println(e.getMessage());
        }
    }


}