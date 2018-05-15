package it.polimi.ingsw.ClientController;

import it.polimi.ingsw.ServerController.ClientHandlerInterface;
import it.polimi.ingsw.model.Exceptions.NumberOfPlayersNotAllowedException;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GamesList;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


//implemented by pon
//non implemeta runnable
public class ObserverView extends UnicastRemoteObject implements ObserverViewInterface, FeedObserverView{
    private ClientHandlerInterface servercontroller;
    private final Scanner in;
    private final PrintWriter out;
    private String username;

    //constructor1
    public ObserverView() throws RemoteException{
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
    }
    //constructor2
    public ObserverView(ClientHandlerInterface controller) throws RemoteException {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
        this.servercontroller = controller;
    }

    public void setServercontroller(ClientHandlerInterface servercontroller){
        this.servercontroller = servercontroller;
    }

    public void run() throws RemoteException {
        String stringa = servercontroller.rmiTest("RMI");
        System.out.println("La connessione è in modalità: " + stringa);
        loadingInt();
        loginInt();
        menuInt();



        //NB: mentre per il server usiamo una sola classe che gestisce sia la connessione socket che RMI (di fatto la connessione socket invoca localmente
        //i metodi che invece tramite RMI vengono invocati da remoto, invece sul client ci servono due clientController, appunto perchè uno chiederà via socket
        //l'esecuzione di determinati metodi, l'altro invece li chiamerà direttamente perchè userà l'implementazione RMI

        //questo purtroppo crea asimmetria, sad story
    }

    private void loadingInt() {
        System.out.print("\n" +
                "   ▄████████    ▄████████    ▄██████▄     ▄████████    ▄████████ ████████▄     ▄████████ \n" +
                "  ███    ███   ███    ███   ███    ███   ███    ███   ███    ███ ███   ▀███   ███    ███ \n" +
                "  ███    █▀    ███    ███   ███    █▀    ███    ███   ███    ███ ███    ███   ███    ███ \n" +
                "  ███          ███    ███  ▄███         ▄███▄▄▄▄██▀   ███    ███ ███    ███   ███    ███ \n" +
                "▀███████████ ▀███████████ ▀▀███ ████▄  ▀▀███▀▀▀▀▀   ▀███████████ ███    ███ ▀███████████ \n" +
                "         ███   ███    ███   ███    ███ ▀███████████   ███    ███ ███    ███   ███    ███ \n" +
                "   ▄█    ███   ███    ███   ███    ███   ███    ███   ███    ███ ███   ▄███   ███    ███ \n" +
                " ▄████████▀    ███    █▀    ████████▀    ███    ███   ███    █▀  ████████▀    ███    █▀  \n" +
                "                                         ███    ███                                      \n\n");
        System.out.println("Benvenuto in Sagrada!");
    }

    private void loginInt() throws RemoteException {
        String password;
        boolean successo = true;
        boolean back = false;

        do{
            successo = true;
            out.println("\n\n< Torna al menu. (B)\n");
            if (in.next() == "B" || in.next() == "b") {
                back = true;
            }
        try {
            out.println("Hai già un account? [S/N]\n");
            if (in.next() == "S" || in.next() == "s") {
                logInt();
            } else {
                        out.println("Inserisci un nuovo Username:\n");
                        username = in.nextLine();
                        out.println("Inserisci una password:\n");
                        password= in.nextLine();
                        try {
                            servercontroller.register(username, password);
                        }
                        catch(RemoteException e){
                            successo = false;
                        }
                out.println("Esegui il LogIn con l'account appena creato:");
                logInt();
            }
        } catch (RemoteException e) {
            out.println("Qualcosa è andato storto con il LogIn!\n");
            out.close();
            in.close();
        }
    }while (successo == false || back == false);
        if(back == true) menuInt();
    }


    private void logInt() throws RemoteException {
        String username;
        String password;
        boolean successo = true;
        boolean back = false;

        do {
            successo = true;
            out.println("\n\n< Torna al menu. (B)\n");
            if (in.next() == "B" || in.next() == "b") {
                back=true;
            }
            out.println("Inserisci username:\n");
            username = in.nextLine();
            out.println("Inserisci password:\n");
            password = in.nextLine();
            try{
                servercontroller.login(username, password);
            }
            catch(RemoteException e){
                successo = false;
            }
        } while (successo == false|| !back);
        if(back){
            menuInt();   //usato per uscire dal ciclo e tronare al log in in caso di misstype.
        }
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
        }
        else if (in.next() == "C" || in.next() == "c") {
            creaInt();
        }
        else partecipaInt();
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
            if (in.nextLine() == "S"||in.nextLine()=="s") {
                while(!servercontroller.isMatchInList(gamename)) {
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

    private void startGameInt(){
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
                servercontroller.createGame(username,this,this, name);   //mancano observer e forse anche num players
                out.println("Attendi che alttri giocatori partecipino alla partita.\n Divertiti!\n");
                success = true;
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showSchemeCards(SchemeCard scheme, SchemeCard card) {

    }

    @Override
    public void notifyaDraw() {

    }

    @Override
    public void notifyaLose() {

    }

    @Override
    public void notifyaWin() {

    }


    //rimosse impostazioni non più necessarie in quanto la scelta verrà fatta all'atto dell login.
/*
    private void optionInt(){
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("/n/nSei nelle impostazioni del gioco! (per selezionare inserisci il numero a lato)");
        out.println("1) Impostazioni di connessione.");
        out.println("2) Impostazioni grafiche client.");
        out.println("\n\n< Torna al menu. (B)");
        if (in.next() == "B" || in.next() == "b") {
            menuInt();
        }
        else if(in.next()=="1"){
           connectionInt();
        }
        else{
            graficInt();
        }
    }

    private void connectionInt() { //da implemntare parte di switch tra RMI SOCKET
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("/n/nSei nelle impostazioni della connessione.");
        out.println("Attualmente la connessione è in modalità: RMI");
        out.println("Vuoi passare alla connessione tramite Socket? [S/N]");
        out.println("\n\n< Torna al menu. (B)");
        if (in.next() == "B" || in.next() == "b") {
            optionInt();
        }
        else if(in.next() == "S" || in.next() == "s"){
            out.println("Hai deciso di passare alla connessione Socket, il client si riavvierà in tale modalità tra qualche secondo.");
            //implementare switch
        }
        else{
            out.println("Rimarrai in modalità connessione RMI.");
        }
    }


    private void graficInt() { //da implementare lo switch
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("/n/nSei nelle impostazioni relative alla grafica.");
        out.println("Attualmente il client è in modalità: CLI");
        out.println("Vuoi passare al client Grafico? [S/N]");
        out.println("\n\n< Torna al menu. (B)");
        if (in.next() == "B" || in.next() == "b") {
            optionInt();
        }
        else if(in.next() == "S" || in.next() == "s"){
            out.println("Hai deciso di passare al client grafico, tra pochi secondi il client si chiuderà e dovrai riaprirlo usando ClientGrafico.");
            //implementare switch
        }
        else{
            out.println("Rimarrai in modalità client CLI.");
        }
    }
    */
}

