package it.polimi.ingsw.ClientController;

import java.io.IOException;
import java.net.Socket;

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
        System.out.println("Connessione stabilita\n");
        try {
            new SocketClientController(socket).run();
        } catch (IOException e) {
            System.out.println("Connessione chiusa\n");
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
        String ip = "10.169.214.40" ;
        //new Client(ip , 1337).startClient();

        //RMI part
        Registry registry = LocateRegistry.getRegistry(ip);
        // gets a reference for the remote controller
        RemoteClientHandler controller = (RemoteClientHandler) registry.lookup("ClientHandler");
        // creates and launches the clientcontroller
        System.out.println("Looking for connection ...\n");
            new RMIClientController(controller).run();
        }*/
    }


