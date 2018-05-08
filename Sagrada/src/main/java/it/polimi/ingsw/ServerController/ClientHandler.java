package it.polimi.ingsw.ServerController;

import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//all'inizio avevo pensato di mettere il client server per rmi in una classe a parte
//poi ho pensato: ma devo proprio? beh per ora mi sembra di no
//l'unica cosa è l'esposizione del metodo run, che beh nel caso dirmi non deve essere invocato, perchè serve invece per gestire le connessioni socket
// il fatto che implementi runnable e quindi sia codice per thread non mi pare crei problemi
//se avete notizie avvisatemi
//client handler
public class ClientHandler extends UnicastRemoteObject implements Runnable, RMIRemoteServerClientHandler {
    Socket socket;
    public ClientHandler ()throws RemoteException {};
    public ClientHandler(Socket socket) throws RemoteException{
        this.socket = socket;
    }

    @Override
    public void run(){
        //questo per gestire i socket
        //qui metteremo lo switchone
    }

    @Override
    public String forzaChievo(String stringa) {
        return "Chievo" + stringa;
    }

    //here we'll put the implementation of all the methods in the interface RMIRemoteServerClientHandler

}
