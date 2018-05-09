package it.polimi.ingsw.ClientController;

import it.polimi.ingsw.ServerController.MultiplePlayerGameHandler;
import it.polimi.ingsw.ServerController.RemoteClientHandler;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.NumberOfPlayersNotAllowedException;
import it.polimi.ingsw.model.MultipleUserGameList;
import it.polimi.ingsw.model.User;
import it.polimi.ingsw.model.UsersList;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

//implemented by pon
//non implemeta runnable
public class RMIClientController extends UnicastRemoteObject {
    private RemoteClientHandler servercontroller;

    //constructor
    public RMIClientController(RemoteClientHandler controller) throws RemoteException {
        this.servercontroller = controller;
    }

    public void run() throws RemoteException {
        User you;
        String stringa = servercontroller.rmiTest("RMI");
        System.out.println("La connessione è in modalità: " + stringa);
        loadingInt();
        you=loginInt();
        menuInt(you);
        //qui si dovrà scrivere il clientcontroller per RMI
        //che giorgio dovrebbe avere già scritto


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

    private User loginInt() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        User you = null;
        try {
            out.println("Hai già un account? [S/N]\n");
            you = null;
            if (in.next() == "S" || in.next() == "s") {
                out.println("Inserisci username:\n");
                String username = in.nextLine();
                out.println("Inserisci password:\n");
                String password = in.nextLine();
                while (!UsersList.Singleton().check(username, password)) {
                    out.println("Username e/o password sbagliati!\n");
                    username = in.nextLine();
                    password = in.nextLine();
                }
                try {
                    you = UsersList.Singleton().getUser(username, password);
                } catch (Exception e) {
                    out.println("Qualcosa è andato storto con il nostro dtabase: zorry mate!\n");
                    out.close();
                    in.close();
                    return you;
                }
            } else {
                out.println("Inserisci un nuovo Username:\n");
                boolean successo = false;
                while (!successo) {
                    try {
                        String username = in.nextLine();
                        UsersList.Singleton().checkHomonymy(username);//
                        out.println("Inserisci una password:\n");
                        you = UsersList.Singleton().register(username, in.nextLine());
                        successo = true;
                    } catch (HomonymyException e) {
                        out.println(e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            out.println("Qualcosa è andato storto con il LogIn!\n");
            out.close();
            in.close();
            return you;
        }
        return you;
    }

    private void menuInt(User you) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("Benvenuto nel menù principale di Sagrada!");
        out.println("- Multi Giocatore (M)");
        out.println("- Giocatore Singolo (Coming soon!)");
        out.println("- Impostazioni (I)");
        if (in.next() == "I" || in.next() == "i") {
            optionInt(you);
        }
        else{
            multiInt(you);
        }
    }

    private void multiInt(User you) { //da implemnetare
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("/n/nVuoi creare o partecipare ad una partita? [C/P]");
        out.println("\n\n< Torna al menu. (B)");
        if (in.next() == "B" || in.next() == "b") {
            menuInt(you);
        }
        else if (in.next() == "C" || in.next() == "c") {
            creaInt(you);
        }
        else partecipaInt(you);
    }

    private void partecipaInt(User you) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("Scegli la partita a cui vuoi partecipare dalla lista:");
        for (MultiplePlayerGameHandler game : MultipleUserGameList.singleton().getgames()) {
            out.println(game.getName() + "; Giocatori che stanno partecipando: " + game.getActualNumberOfPlayers() + "Giocatori necessari alla partita: " + game.getMaxNumberPlayers() + "\n");
        }
        boolean chosen = false;
        while (!chosen) {
            out.println("Vuoi ancora partecipare ad una partita? [S/N]\n");
            if (in.nextLine() == "S"||in.nextLine()=="s") {
                out.println("Scegli la partita in cui entrare.\n");
                for (MultiplePlayerGameHandler game : MultipleUserGameList.singleton().getgames()) {
                    if (game.getName().equals(in.nextLine())) {
                        try {
                            game.join(you);
                        } catch (NullPointerException e) {
                            e.getCause();
                        }
                    }
                }
                waitingInt(you);
                chosen = true;
            } else {
                multiInt(you);
            }
        }
    }

    private void waitingInt(User you) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("Attendi che altri giocatori entrino in partita...\n");
        MultipleUserGameList.singleton().checkIsReady();
    }


    private void creaInt(User you) {
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
                MultipleUserGameList.singleton().create(you, name, max);
                out.println("Attendi che alttri giocatori partecipino alla partita.\n Divertiti!\n");
                success = true;
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        }
    }

    private void optionInt(User you){
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("/n/nSei nelle impostazioni del gioco! (per selezionare inserisci il numero a lato)");
        out.println("1) Impostazioni di connessione.");
        out.println("2) Impostazioni grafiche client.");
        out.println("\n\n< Torna al menu. (B)");
        if (in.next() == "B" || in.next() == "b") {
            menuInt(you);
        }
        else if(in.next()=="1"){
           connectionInt(you);
        }
        else{
            graficInt(you);
        }
    }

    private void connectionInt(User you) { //da implemntare parte di switch tra RMI SOCKET
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("/n/nSei nelle impostazioni della connessione.");
        out.println("Attualmente la connessione è in modalità: RMI");
        out.println("Vuoi passare alla connessione tramite Socket? [S/N]");
        out.println("\n\n< Torna al menu. (B)");
        if (in.next() == "B" || in.next() == "b") {
            optionInt(you);
        }
        else if(in.next() == "S" || in.next() == "s"){
            out.println("Hai deciso di passare alla connessione Socket, il client si riavvierà in tale modalità tra qualche secondo.");
            //implementare switch
        }
        else{
            out.println("Rimarrai in modalità connessione RMI.");
        }
    }

    private void graficInt(User you) { //da implementare lo switch
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        out.println("/n/nSei nelle impostazioni relative alla grafica.");
        out.println("Attualmente il client è in modalità: CLI");
        out.println("Vuoi passare al client Grafico? [S/N]");
        out.println("\n\n< Torna al menu. (B)");
        if (in.next() == "B" || in.next() == "b") {
            optionInt(you);
        }
        else if(in.next() == "S" || in.next() == "s"){
            out.println("Hai deciso di passare al client grafico, tra pochi secondi il client si chiuderà e dovrai riaprirlo usando ClientGrafico.");
            //implementare switch
        }
        else{
            out.println("Rimarrai in modalità client CLI.");
        }
    }
}

