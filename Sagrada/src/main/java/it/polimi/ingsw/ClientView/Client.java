package it.polimi.ingsw.ClientView;



import it.polimi.ingsw.NetworkClient.SocketClientListener;
import it.polimi.ingsw.NetworkClient.SocketController;
import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.io.IOException;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * This class is for the client CLI. There is one single main method and when we run it the user will be able to choose which
 * type of connectivity he prefers (Socket/RMI) and the connection will be established with the remote server.
 * User can also manually change ip for RMI and ip/port for socket to match with his private network configuration
 */
public class Client {

    /**
     * This object is used for getting the names and the descriptions of the cards in the game like public, private and tool cards
     */
    public static Translator translator;


    public static void main(String[] args){
        boolean correct = false;
        boolean good = false;
        Scanner in = new Scanner(System.in);
        translator = new ItalianTranslator();
        int port = 1337;
        String ipAddr = "127.0.0.1";

        System.out.println("Benvenuto nel SetUP partita di Sagrada!\n" +
                "Qui puoi selezionare se giocare usando la connessione di tipo RMI (R) oppure Socket (S).\n" +
                "Seleziona ora la tua scelta digitando R per RMI o S per Socket:");
        while (!correct) {
            String input = in.nextLine();
            if (input.equals("R") || input.equals("r")) {
                System.out.println("L'ip attualmente selezionato è: " + ipAddr + "\nVuoi cambiarlo? [S/N]");
                good = false;
                while (!good) {
                    String change = in.nextLine();
                    if (change.equals("S") || change.equals("s")) {
                        System.out.println("Inserisci un nuovo ip: [255.255.255.255]");
                        ipAddr = in.nextLine();
                        if (ipAddr == null || ipAddr.isEmpty()) {
                            good = false;
                            System.out.println("L'ip non può essere una stringa vuota!");
                        }
                        String[] subFields = ipAddr.split("\\.");
                        if (subFields.length != 4) {
                            good = false;
                            System.out.println("L'ip deve essere composto da 4 nuneri.");

                        }
                        for (String s : subFields) {
                            int i = Integer.parseInt(s);
                            if ((i < 0) || (i > 255)) {
                                good = false;
                                System.out.println("I quattro numeri devono essere compresi tra 0 e 255!");
                            }
                        }
                        if (ipAddr.endsWith(".")) {
                            good = false;
                            System.out.println("Non si possono lasciare campi vuoti!");
                        } else good = true;
                    } else if (change.equals("N") || change.equals("n")) {
                        System.out.println("L'ip rimane quello standard!");
                        good = true;
                    } else System.out.println("Hai sbagliato a digitare!");
                }
                    try {
                        Registry rmiRegistry = LocateRegistry.getRegistry(ipAddr);
                        ClientHandlerInterface controller = (ClientHandlerInterface) rmiRegistry.lookup("ClientHandler");
                        new ObserverView(controller).run();
                        correct = true;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        System.out.println("Errore nello stabilire la connessione, oppure bug nel gioco,  il gioco non può iniziare");
                    }

            } else if (input.equals("S") || input.equals("s")) {
                System.out.println("L'ip attualmente selezionato è: " + ipAddr + "\nVuoi cambiarlo? [S/N]");
                good = false;
                while (!good) {
                    String change = in.nextLine();
                    if (change.equals("S") || change.equals("s")) {
                        System.out.println("Inserisci un nuovo ip: [255.255.255.255]");
                        ipAddr = in.nextLine();
                        if (ipAddr == null || ipAddr.isEmpty()) {
                            good = false;
                            System.out.println("L'ip non può essere una stringa vuota!");
                        }
                        String[] subFields = ipAddr.split("\\.");
                        if (subFields.length != 4) {
                            good = false;
                            System.out.println("L'ip deve essere composto da 4 nuneri.");

                        }
                        for (String s : subFields) {
                            int i = Integer.parseInt(s);
                            if ((i < 0) || (i > 255)) {
                                good = false;
                                System.out.println("I quattro numeri devono essere compresi tra 0 e 255!");
                            }
                        }
                        if (ipAddr.endsWith(".")) {
                            good = false;
                            System.out.println("Non si possono lasciare campi vuoti!");
                        } else good = true;
                    } else if (change.equals("N") || change.equals("n")) {
                        System.out.println("L'ip è quello standard");
                        good = true;
                    } else System.out.println("Hai sbagliato a digitare!");
                }
                System.out.println("La porta attualmente impostata è: " + port + "\nVuoi cambiarla? [S/N]");
                good = false;
                while (!good) {
                    String change = in.nextLine();
                    if (change.equals("S") || change.equals("s")) {
                        System.out.println("Inserisci una nuova porta:");
                        port = in.nextInt();
                        if (port < 0 || port >65535) {
                            good = false;
                            System.out.println("La porta non è corretta!");
                        } else good = true;
                    } else if (change.equals("N") || change.equals("n")) {
                        System.out.println("La porta rimane quella standard!");
                        good = true;
                    } else System.out.println("Hai sbagliato a digitare!");
                }
                try{
                    ObserverView view = new ObserverView();
                    Socket socket = new Socket(ipAddr, port);
                    SocketClientListener listener = new SocketClientListener(socket);
                    SocketController socketController = new SocketController( listener);
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


