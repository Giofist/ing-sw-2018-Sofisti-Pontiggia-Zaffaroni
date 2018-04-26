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
                    out.println("Something went wrong: zorry mate!\n");
                }
            }
            else{
                out.println("Insert new Username\n");
                String name = in.nextLine();
                out.println("Insert Password\n");
                try{
                    UsersList.Singleton().register(name, in.nextLine(),this.socket);
                }catch (HomonymyException)

                }


        }  catch (IOException e){
            System.err.println(e.getMessage());
        }
        //not implemented yet
    }
}
