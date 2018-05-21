package it.polimi.ingsw.ClientView;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesList;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


//implemented by pon
//non implementa runnable
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
        loadingInt();
        choseInt();
        //loginInt();
        menuInt();
    }

    private void choseInt() throws RemoteException {
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
                System.out.println("Inserisci un nuovo Username:");
                username = in.nextLine();
                this.yourName = username;
                System.out.println("Inserisci una password:");
                password = in.nextLine();
                try {
                    servercontroller.register(username, password);
                } catch (RemoteException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Esegui il LogIn con l'account appena creato!");
                signInInt();
                successo = true;
            }catch (RemoteException e) {
                System.out.println(e.getMessage());
                out.close();
                in.close();
            }
        }
    }




    private void signInInt() throws RemoteException {
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
        } while (successo == false);
        }


    private void menuInt() throws RemoteException {
        String input;
        Scanner in = new Scanner(System.in);

        System.out.println("Benvenuto nel menù principale di Sagrada!");
        System.out.println("- Multi Giocatore (M)");
        System.out.println("- Giocatore Singolo (Coming soon!)");
        input = in.nextLine();
        //System.out.println("- Impostazioni (I)");
        if (input.equals("M") || input.equals("m")) {
            multiInt();
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
            creaInt();
        } else if (input.equals("P") || input.equals("p")) {
            partecipaInt();
        }
        else System.out.println("Hai sbagliato a digitare!");
    }

    private void partecipaInt() throws RemoteException {
        String gamename = null;
        Scanner in = new Scanner(System.in);


        System.out.println("Scegli la partita a cui vuoi partecipare dalla lista:");
        for (Game game : GamesList.singleton().getgames()) {
            // Correzione per pushare codice non rotto (ho tenuta la vecchia riga verifica che la nuova vada bene)
            //out.println(game.getName() + "; Giocatori che stanno partecipando: " + game.getActualNumberOfPlayers() + "Giocatori necessari alla partita: " + game.getMaxNumberPlayers() + "\n");
            System.out.println(game.getName() + "; Giocatori che stanno partecipando: " + game.getNumberOfPlayers() + "Giocatori necessari alla partita: 4\n");
        }
        boolean chosen = false;
        while (!chosen) {
            System.out.println("Vuoi ancora partecipare ad una partita? [S/N]");
            if (in.nextLine() == "S" || in.nextLine() == "s") {
                while (!servercontroller.isMatchInList(gamename)) {
                    System.out.println("Digita il il nome della partita cui vuoi partecipare:");
                    gamename = in.nextLine();
                    try {
                        servercontroller.joinaGame(yourName, gamename);
                    } catch (RemoteException e) {
                        System.out.println(e.getMessage());
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
        System.out.println("Il gioco inizia ora!");

        //to be implemented.
    }

    private void waitingInt() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        System.out.println("Attendi che altri giocatori entrino in partita...");
        //timer di attesa poi
        startGameInt();

    }


    private void creaInt() {
        boolean success = false;
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        System.out.println("Stai per creare una partita di Sagrada." +
                "Inserisci il nome della partita:");
        while (!success) {
            try {
                String gamename = in.nextLine();
               /* System.out.println("Scegli il numero di giocatori necessario alla partita (incluso te stesso) [0/4]");
                int max = in.nextInt();
                if (max < 2 || max > 4) {
                    throw new NumberOfPlayersNotAllowedException();
                }*/

                servercontroller.createGame(yourName, this, this, gamename);
                System.out.println("Attendi che altri giocatori partecipino alla partita.\n Divertiti!");
                waitingInt();
                success = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
        System.out.println("Seleziona la carta schema che desideri tra le seguenti indicando il numero relativo.");
        System.out.println(schemeCard1.displayScheme());
        System.out.println(schemeCard1.getTwinCard().displayScheme());
        System.out.println(schemeCard2.displayScheme());
        System.out.println(schemeCard1.getTwinCard().displayScheme());
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