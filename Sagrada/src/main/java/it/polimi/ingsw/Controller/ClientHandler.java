package it.polimi.ingsw.Controller;

import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.NumberOfPlayersNotAllowedException;
import it.polimi.ingsw.model.MultipleUserGameList;
import it.polimi.ingsw.model.User;
import it.polimi.ingsw.model.UsersList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


//raga ho fatto una prima implementazione del client handler
public class ClientHandler implements Runnable {
    Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println ("Already have an account [Y/N]?\n");
            User you = null;
            if (in.next() == "Y") {
                out.println("Insert username and password\n");
                String username = in.nextLine();
                String password = in.nextLine();
                while (!UsersList.Singleton().check(username, password)) {
                    out.println("Wrong Username and/or password\n");
                    username = in.nextLine();
                    password = in.nextLine();
                }
                try {
                    you = UsersList.Singleton().getUser(username, password);
                    you.setSocket(this.socket);
                } catch (Exception e) {
                    out.println("Something about out database went wrong: zorry mate!\n");
                    out.close();
                    in.close();
                    return;
                }
            }
            else {
                out.println("Insert new Username\n");
                boolean successo = false;
                while (!successo) {
                    try {
                        String username = in.nextLine();
                        UsersList.Singleton().checkHomonymy(username);
                        out.println("Insert password\n");
                        you = UsersList.Singleton().register(username, in.nextLine(), this.socket);
                        successo = true;
                    } catch (HomonymyException e) {
                        out.println(e.getMessage());
                    }
                }
            }
            out.println("Welcome to the sala d'attesa. here the games");
            for (MultiplePlayerGameHandler game: MultipleUserGameList.singleton().getgames()) {
                out.println(game.getName() + "; Players who are partecipating: "+ game.getActualNumberOfPlayers() + "Players needed to start the game: " + game.getMaxNumberPlayers() + "\n");
            }
            boolean chosen = false;
            while(!chosen){
                out.println("Wanna join an existant game? [Y/N]\n");
                if (in.nextLine()== "Y"){
                    out.println("Select the game you wanna join\n");
                    for (MultiplePlayerGameHandler game : MultipleUserGameList.singleton().getgames()){
                        if (game.getName().equals(in.nextLine())){
                            try{
                                game.join(you);
                            }catch (NullPointerException e){
                                e.getCause();
                            }
                        }

                    }
                    out.println("Waiting for reaching the required number of player\n");
                    MultipleUserGameList.singleton().checkIsReady();
                    chosen = true;

                }
                else if(in.nextLine()=="N") {
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
                }
                else{
                    out.println("You didn't type corretly\n");
                }
            }
        }  catch (IOException e){
            System.err.println(e.getMessage());
        }

    }
}
