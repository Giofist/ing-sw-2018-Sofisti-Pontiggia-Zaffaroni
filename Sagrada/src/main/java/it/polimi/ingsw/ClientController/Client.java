package it.polimi.ingsw.ClientController;


import it.polimi.ingsw.ServerController.ClientHandlerInterface;

import java.io.IOException;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) throws Exception {
        // Passing server IP as command line argument
        String ipAddr = args[1];
        boolean correct = false;
        Scanner in = new Scanner(System.in);



        //avvio una view in client per poi chiamare alternativamnete in base alla scelta utente RMI o Socket
        System.out.println("Benvenuto nel SetUP partita di Sagrada!\nQui puoi selezionare se giocare usando la connessione di tipo RMI (R) oppure Socket (S).\n Seleziona ora la tua scelta digitando R per RMI o S per Socket:");
        while (!correct) {
            if (in.next() == "R" || in.next() == "r") {
                // Locating rmi register on the server
                // looking for the controller on the registry
                Registry rmiRegistry = LocateRegistry.getRegistry(ipAddr);
                ClientHandlerInterface controller = (ClientHandlerInterface) rmiRegistry.lookup("ClientHandler");
                new ObserverView(controller).run();
                correct = true;
            } else if (in.next() == "S" || in.next() == "s") {
                Socket socket = new Socket(ipAddr, 1337);
                System.out.println("Connessione stabilita\n");
                ObserverView observerView = new ObserverView();
                SocketObserverView socketObserverView = new SocketObserverView(socket, observerView);
                observerView.setServercontroller(socketObserverView);
                observerView.run();
                correct = true;
            } else {
                System.out.println("Hai sbagliato a digitare.");
            }
        }
    }

    // nelle specifiche si dice di presupporre che il giocatore conosca l'indirizzo IP del server
    // io ho pensato che lo scriva come argomento quando chiama il programma da linea di comando
    //nelle implementazioni senza linea di comando, come funziona?




    /*public static void main(String[] args) throws Exception {

        //manca una parte in cui il client decide se utilizzare RMI o socket, anche questo da riga di comando?
        //notate che da specifiche si chiede che il client possa decidere se usare RMI o socket SOLO A INIZIO PARTITA, e non si chiede che si possa cambiare tipo
        //di connessione dinamicamente


        // il problema grosso che ho rilevato è la gestione di più giocatori nella stessa partita con connessioni diverse
        //in realtà, la gestione della partita è pensata solo per i socket per me, mentre la parte di log in iniziale è pensata sia per RMI che socket
        //tutto questo crea delle crepe nell'architettura che abbiamo individuato finora... voi che ne dite?

        //socket part
        String ip = "127.0.0.1" ;
        //new Client(ip , 1337).startClient();

        //RMI part
        Registry registry = LocateRegistry.getRegistry(ip);
        // gets a reference for the remote controller
        ClientHandlerInterface controller = (ClientHandlerInterface) registry.lookup("ClientHandler");
        // creates and launches the clientcontroller
            new ObserverView(controller).run();
        }*/
    }


