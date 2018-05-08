package it.polimi.ingsw.ClientController;

import it.polimi.ingsw.ServerController.RMIRemoteServerClientHandler;

import java.io.IOException;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private String ip;
    private int port;
    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    //per il socket
    public void startClient() throws IOException {
        Socket socket = new Socket(this.ip, this.port);
        System.out.println("Connection established\n");
        try {
            new SocketClientController(socket).run();
        } catch (IOException e) {
            System.out.println("Connection closed");
        }
    }

    // nelle specifiche si dice di presupporre che il giocatore conosca l'indirizzo IP del server
    // io ho pensato che lo scriva come argomento quando chiama il programma da linea di comando
    //nelle implementazioni senza linea di comando, come funziona?
    public static void main(String[] args) throws Exception {

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
        RMIRemoteServerClientHandler controller = (RMIRemoteServerClientHandler) registry.lookup("RMIServerClientHandler");
        // creates and launches the clientcontroller
            new RMIClientController(controller).run();
        }
    }


