package it.polimi.ingsw.ClientController;

import it.polimi.ingsw.ServerController.RMIRemoteServerClientHandler;

//implemented by pon
public class RMIClientController {
    private RMIRemoteServerClientHandler servercontroller;
    //constructor
    public RMIClientController(RMIRemoteServerClientHandler controller){
        this.servercontroller = controller;
    }

    public void run(){
        //qui si dovrà scrivere il clientcontroller per RMI
        //che giorgio dovrebbe avere già scritto


        //NB: mentre per il server usiamo una sola classe che gestisce sia la connessione socket che RMI (di fatto la connessione socket invoca localmente
        //i metodi che invece tramite RMI vengono invocati da remoto, invece sul client ci servono due clientController, appunto perchè uno chiederà via socket
        //l'esecuzione di determinati metodi, l'altro invece li chiamerà direttamente perchè userà l'implementazione RMI

        //questo purtroppo crea asimmetria, sad story
    }
}
