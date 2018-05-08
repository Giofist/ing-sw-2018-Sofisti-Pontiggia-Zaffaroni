package it.polimi.ingsw.ClientController;

import it.polimi.ingsw.ServerController.RMIRemoteServerClientHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//implemented by pon
//non implemeta runnable
public class RMIClientController extends UnicastRemoteObject {
    private RMIRemoteServerClientHandler servercontroller;
    //constructor
    public RMIClientController(RMIRemoteServerClientHandler controller) throws RemoteException {
        this.servercontroller = controller;
    }

    public void run() throws RemoteException{
        String stringa = servercontroller.forzaChievo("Verona");
        System.out.println(stringa);
        //qui si dovrà scrivere il clientcontroller per RMI
        //che giorgio dovrebbe avere già scritto


        //NB: mentre per il server usiamo una sola classe che gestisce sia la connessione socket che RMI (di fatto la connessione socket invoca localmente
        //i metodi che invece tramite RMI vengono invocati da remoto, invece sul client ci servono due clientController, appunto perchè uno chiederà via socket
        //l'esecuzione di determinati metodi, l'altro invece li chiamerà direttamente perchè userà l'implementazione RMI

        //questo purtroppo crea asimmetria, sad story
    }
}
