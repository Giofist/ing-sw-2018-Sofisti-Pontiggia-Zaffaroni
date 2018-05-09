package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.MultiplePlayerGameHandler;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.NumberOfPlayersNotAllowedException;
import it.polimi.ingsw.model.MultipleUserGameList;
import it.polimi.ingsw.model.User;
import it.polimi.ingsw.model.UsersList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientView {
    public ClientView() {
        try {
            Scanner in = new Scanner(System.in);
            PrintWriter out = new PrintWriter(System.out);
            out.println("Hai già un account? [S/N]\n");
            User you = null;
            if (in.next() == "S" || in.next() == "s") {
                out.println("Inserisci username:\n");
                String username = in.nextLine();
                out.println("Inserisci password:\n");
                String password = in.nextLine();
                while (!UsersList.Singleton().check(username, password)) {//da rivedere controllare con soket
                    out.println("Username e/o password sbagliati!\n");
                    username = in.nextLine();
                    password = in.nextLine();
                }
                try {
                    // you = UsersList.Singleton().getUser(username, password);// va rivisto con soket
                    // you.setSocket(this.socket);
                } catch (Exception e) {
                    out.println("Qualcosa è andato storto con il nostro dtabase: zorry mate!\n");
                    out.close();
                    in.close();
                    return;
                }
            } else {
                out.println("Inserisci un nuovo Username:\n");
                boolean successo = false;
                while (!successo) {
                    try {
                        String username = in.nextLine();
                        UsersList.Singleton().checkHomonymy(username);//
                        out.println("Inserisci una password:\n");
                        //you = UsersList.Singleton().register(username, in.nextLine(), this.socket);   da rivedere con soket
                        successo = true;
                    } catch (HomonymyException e) {
                        out.println(e.getMessage());
                    }
                }
            }
            out.println("Benvenuto nel menù principale di Sagrada!");
            out.println("Multi Giocatore (M)");
            out.println("Giocatore Singolo (Coming soon!)");
            out.println("Impostazioni (I)");
            if (in.next() == "I" || in.next() == "i") {
                out.println("/n/nSei nelle impostazioni del gioco! (per selezionare inserisci il numero a lato)");
                out.println("1) Impostazioni di connessione.");
                out.println("2) Impostazioni grafiche client.");
                out.println("< Torna al menu. (B)");
                //implemento il back
            } else {
                out.println("Vuoi creare un apartita (C) o partecipare (P)?");
                if (in.next() == "C" || in.next() == "c") {
                    out.println("Sei nel menù di creazione della partita!");
                } else {


                    for (MultiplePlayerGameHandler game : MultipleUserGameList.singleton().getgames()) {
                        out.println(game.getName() + "; Players who are partecipating: " + game.getActualNumberOfPlayers() + "Players needed to start the game: " + game.getMaxNumberPlayers() + "\n");
                    }
                    boolean chosen = false;
                    while (!chosen) {
                        out.println("Wanna join an existant game? [Y/N]\n");
                        if (in.nextLine() == "Y") {
                            out.println("Select the game you wanna join\n");
                            for (MultiplePlayerGameHandler game : MultipleUserGameList.singleton().getgames()) {
                                if (game.getName().equals(in.nextLine())) {
                                    try {
                                        game.join(you);
                                    } catch (NullPointerException e) {
                                        e.getCause();
                                    }
                                }

                            }
                            out.println("Waiting for reaching the required number of player\n");
                            MultipleUserGameList.singleton().checkIsReady();
                            chosen = true;

                        } else if (in.nextLine() == "N") {
                            boolean success = false;
                            out.println("Create a new game: set the name\n");
                            while (!success) {
                                try {
                                    String name = in.nextLine();
                                    out.println("Set the number of players you want to partecipate the game (included yourself) [0/4]\n");
                                    int max = in.nextInt();
                                    if (max < 2 || max > 4) {
                                        throw new NumberOfPlayersNotAllowedException();
                                    }

                                    MultipleUserGameList.singleton().create(you, name, max);
                                    out.println("Wait for other players to join\n. Have fun from the client handler\n");
                                    success = true;
                                } catch (Exception e) {
                                    out.println(e.getMessage());
                                }
                            }
                            chosen = true;
                        } else {
                            out.println("You didn't type corretly\n");
                        }
                    }
                } catch(IOException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
