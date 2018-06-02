package it.polimi.ingsw.ServerController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class SocketClientHandler implements Runnable, it.polimi.ingsw.ClientView.Observer {
    private Socket socket;
    private ClientHandler controller;
    Scanner in;
    PrintWriter out;
    private ObjectInputStream is;
    private ObjectOutputStream os;

    public SocketClientHandler(Socket socket, ClientHandler controller) throws IOException {
        this.socket = socket;
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream());
        this.controller = controller;
        //this.is = new ObjectInputStream(socket.getInputStream());
        //this.os = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Connessione stabilita!\n");

    }

    @Override
    public void run() {
        int i = 0;
        while (i == 0) {
            if (in.hasNext()){
                String command = in.next();
                System.out.println("the command I've received: " + command);
                switch (command) {
                    case "register":
                        try {
                            String username = in.next();
                            String password = in.next();
                            controller.register(username, password);
                            out.println(1);
                            out.flush();
                        } catch (RemoteException e) {
                            out.println(0 + " " + e.getMessage());
                            out.flush();
                        }
                        break;
                    case "login":
                        try {
                            String username = in.next();
                            System.out.println(username);
                            String password = in.next();
                            System.out.println(password);
                            controller.login(username, password);
                            out.println(1);
                            out.flush();
                            System.out.println("ho fatto  flush");
                        } catch (RemoteException e) {
                            out.println(0 + " " + e.getMessage());
                            out.flush();
                        }
                        break;
                    case "createGame":
                        try {
                            String username = in.next();
                            String gamename = in.next();
                            controller.createGame(username, this, gamename);
                            out.println(1);
                            out.flush();
                        } catch (RemoteException e) {
                            out.println(0 + " " + e.getMessage());
                            out.flush();
                        }
                        break;
                    case "getActiveMatchList":
                        try {
                            String list = controller.getActiveMatchesList();
                            out.println(33 + " " + list);
                            out.flush();
                        } catch (RemoteException e) {
                            out.println(0 + " " + e.getMessage());
                            out.flush();
                        }
                        break;
                    case "setSchemeCard":
                        try {
                            String username = in.next();
                            int cardid = in.nextInt();
                            controller.setSchemeCard(username, cardid);
                            out.println(1);
                            out.flush();
                        } catch (RemoteException e) {
                            out.println(0 + " " + e.getMessage());
                            out.flush();
                        }
                        break;
                    case "joinaGame":
                        try {
                            String username = in.next();
                            String gamename = in.next();
                            controller.joinaGame(username, this, gamename);
                            out.println(1);
                            out.flush();
                        } catch (RemoteException e) {
                            out.println(0 + " " + e.getMessage());
                            out.flush();
                        }
                }
            }
            }


    }


    @Override
    public void update(it.polimi.ingsw.model.Observable o, Object arg) {

    }
}
