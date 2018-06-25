package it.polimi.ingsw.ClientView;



import it.polimi.ingsw.NetworkClient.SocketClientListener;
import it.polimi.ingsw.NetworkClient.SocketController;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.io.IOException;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static Translator translator;


    public static void main(String[] args){
        // Passing server IP as command line argument
        //String ipAddr = args[1];
        int port = 1337;
        String ipAddr = "127.0.0.1";

        boolean correct = false;
        Scanner in = new Scanner(System.in);
        translator = new ItalianTranslator();

        System.out.println("Benvenuto nel SetUP partita di Sagrada!\n" +
                "Qui puoi selezionare se giocare usando la connessione di tipo RMI (R) oppure Socket (S).\n" +
                "Seleziona ora la tua scelta digitando R per RMI o S per Socket:");
        while (!correct) {
            String input = in.nextLine();
            if (input.equals("R") || input.equals("r")) {
                try{
                    Registry rmiRegistry = LocateRegistry.getRegistry(ipAddr);
                    ClientHandlerInterface controller = (ClientHandlerInterface) rmiRegistry.lookup("ClientHandler");
                    new ObserverView(controller).run();
                    correct = true;
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    System.out.println("Errore nello stabilire la connessione, oppure bug nel gioco,  il gioco non può iniziare");
                }

            } else if (input.equals("S") || input.equals("s")) {
                try{
                    ObserverView view = new ObserverView();
                    Socket socket = new Socket(ipAddr, port);
                    SocketClientListener listener = new SocketClientListener(socket);
                    ClientHandlerInterface socketController = new SocketController( listener);
                    listener.setController(socketController, view);
                    view.setServerController(socketController);
                    new Thread(listener).start();
                    view.run();
                    correct = true;
                    socket.close();
                }catch(IOException e){
                    System.out.println("Errore nello stabilire la connessione, il gioco non può iniziare");
                }finally {
                }
            } else {
                System.out.println("Hai sbagliato a digitare. Riprova");
            }
        }
    }

}

