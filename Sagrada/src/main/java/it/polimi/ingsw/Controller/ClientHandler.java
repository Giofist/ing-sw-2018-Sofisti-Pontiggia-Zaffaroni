package it.polimi.ingsw.Controller;

import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.User;
import it.polimi.ingsw.model.UsersList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
            User you;
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
                } catch (Exception e) {
                    out.println("Something about out database went wrong: zorry mate!\n");
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
        }  catch (IOException e){
            System.err.println(e.getMessage());
        }
        //not implemented yet
    }
}
